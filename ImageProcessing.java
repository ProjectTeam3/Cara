package com.alex.image_processing;

import java.io.*;
import java.awt.image.*;
import java.lang.*;

import javax.imageio.ImageIO;

public class ImageProcessing {
	BufferedImage original;
	int threshold;
	int width;
	int height;
	int[][] gray;
	int[][] binary;
	int[][] rode;
	float[] frequency;
	float[] frequency1;
	float[] cord;
	int[] number;
	int realNumber;
	int num;
	float[] result;
	ImageProcessing(String address) throws IOException{
		original = ImageIO.read(new File(address));
		width = original.getWidth();
		height = original.getHeight();
		gray = new int[width][height];
		binary = new int[width][height];
		rode = new int[width][height];
		frequency = new float[width];
		frequency1 = new float[width];
		number = new int[width];
		cord = new float[width];
		result = new float[3];
		threshold = 0;
		realNumber=-1;
		num=0;
	}
	public void getGray(){
		int rgb,r,g,b,pixel;
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				rgb = original.getRGB(i,j);
				r = (rgb >>16) & 0xFF;
				g = (rgb >> 8) & 0XFF;
				b = (rgb >> 0 ) & 0xFF;
				pixel = (int)((b*29+g*150+r*77+128)>>8);
				gray[i][j]=pixel;
			}
		}
	}
	public void ostu(){
		int tem;
		int sum=width*height,wB=0,wF=0;
		float total=0,sumB=0,max=0,mB,mF,var;
		int [] hist = new int[width*height];
		for (int i=0;i<width;i++){
			for (int j=0;j<height;j++){
				tem=gray[i][j] & 0xFF;
				hist[tem]++;
			}
		}
		for (int i=0; i<256;i++){
			total+=i*hist[i];
		} 
		for(int i=0;i<256;i++){
			wB+=hist[i];
			if (wB==0){
				continue;
			}
			wF = sum -wB;
			if(wF==0){
				break;
			}
			sumB += (float)(i*hist[i]);
			
			mB=sumB/wB;
			mF=(total - sumB)/wF;
			
			var = (float) wB * (float) wF * (mB-mF) * (mB-mF);
			
			if (var>max){
				max=var;
				threshold=i;
			}
		}
	}
	
	public void getBinary(){
		for (int i=0;i<width;i++){
			for (int j=0;j<height;j++){
				if(gray[i][j]> threshold){
					binary[i][j]=1;
				}
				else{
					binary[i][j]=0;
				}
			}
		}
		
	}
	public void corrode(){
		for (int i=1;i<width-1;i++){
			for (int j=1;j<height-1;j++){
				rode[i][j]=1;
				for(int t=i-1;t<i+2;t++){
					for (int k=j-1;k<j+2;k++){
						if (binary[t][k]==0){
							rode[i][j]=0;
							break;
						}
					}
				}
			}
		}
		for (int i=0;i<height/2;i++){
			for (int j=0;j<width;j++){
				if (rode[j][i]==1)
					System.out.print(" ");
				else
					System.out.print("*");
			}
			System.out.println();
		}
	}
	
	public void find(){
		for (int i=0;i<width;i++){
			number[i]=0;
			frequency[i]=0;
			for (int j=0;j<(height/2);j++){
				if(rode[i][j]==1){
					frequency[i]+=(float) (180.0-((float)j)/((float)height)*155.0);
					number[i]++;
				}
			}
			if (number[i]!=0){
				realNumber++;
				frequency1[i]=frequency[i]/number[i];
			}
		}
		result[0]=frequency1[realNumber/6];
		result[1]=frequency1[realNumber/2];
		result[2]=frequency1[realNumber*5/6];
	}
	public static void main(String[] args) throws IOException {
		ImageProcessing ip = new ImageProcessing("D:\\sample.gif");
		ip.getGray();
		ip.ostu();
		ip.getBinary();
		ip.corrode();
		ip.binary=ip.rode;
		ip.corrode();
		ip.find();
	}

}
