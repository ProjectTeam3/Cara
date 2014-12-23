package calc;

import java.io.*;
import java.awt.image.*;
import java.lang.*;

import javax.imageio.ImageIO;

public class ImageProcessing {
	BufferedImage original;
	cara_cal cc;
	int threshold;
	int width;
	int height;
	int[][] gray;
	int[][] binary;
	int[][] rode;
	double[] frequency;
	int[] codx;
	double[] frequency1;
	int[] cord;
	int[] number;
	int realNumber;
	int num;
	double[] resultHz;
	int[] resultx;
	int timestart;
	int timeend;
	double speed1;
	double speed2;
	double speed;
	public ImageProcessing(String address, String address2) throws IOException{
		original = ImageIO.read(new File(address));
		width = original.getWidth();
		height = original.getHeight();
		gray = new int[width][height];
		binary = new int[width][height];
		rode = new int[width][height];
		frequency = new double[width];
		frequency1 = new double[width];
		number = new int[width];
		cord = new int[width];
		resultHz = new double[3];
		resultx = new int[3];
		codx = new int[width];
		threshold = 0;
		realNumber=-1;
		num=0;
		FileInputStream fis = new FileInputStream(address2);
		InputStreamReader isr = new InputStreamReader(fis);
		LineNumberReader lnr = new LineNumberReader(isr);
		
		String s =null;
		int i,t=1;
		while ((s=lnr.readLine())!=null && t<=2){
			i=Integer.parseInt(s);
			if (t==1){
				timestart=i;
			}
			else{
				timeend=i;
			}
			t++;
		}
		if (t<=2){
			System.out.println("error: not enough information!");
		}
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
		double total=0,sumB=0,max=0,mB,mF,var;
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
			sumB += (double)(i*hist[i]);
			
			mB=sumB/wB;
			mF=(total - sumB)/wF;
			
			var = (double) wB * (double) wF * (mB-mF) * (mB-mF);
			
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
	}
	
	public void find(){
		for (int i=0;i<width;i++){
			number[i]=0;
			frequency[i]=0;
			for (int j=0;j<(height/2);j++){
				if(rode[i][j]==1){
					
					frequency[i]+=(double) (180.0-((double)j)/((double)height)*155.0);
					number[i]++;
				}
			}
			if (number[i]!=0){
				realNumber++;
				frequency1[realNumber]=frequency[i]/(double)number[i];
				codx[realNumber]=i;
			}
		}
		resultHz[0]=frequency1[realNumber/6];
		resultx[0]=codx[realNumber/6];
		resultHz[1]=frequency1[realNumber/2];
		resultx[1]=codx[realNumber/2];
		resultHz[2]=frequency1[realNumber*5/6];
		resultx[2]=codx[realNumber*5/6];
		
	}
	public void readTime(){
		for (int i=0;i<3;i++){
			resultx[i]=timestart+resultx[i]*(timeend-timestart)/width;
		}
		System.out.println(resultx[0]);
		System.out.println(resultHz[0]);
		System.out.println(resultx[1]);
		System.out.println(resultHz[1]);
		System.out.println(resultx[2]);
		System.out.println(resultHz[2]);
	}
	public void calcu(){
		cc = new cara_cal(resultHz[0], resultHz[1], resultx[0], resultx[1]);
		speed1=cc.calculcate(1);
		System.out.println(speed1);
		cc.change(resultHz[1], resultHz[2], resultx[1], resultx[2]);
		speed2=cc.calculcate(1);
		System.out.println(speed2);
		speed=speed1/2+speed2/2;
	}
	public double process() throws IOException {
		getGray();
		ostu();
		getBinary();
		corrode();
		find();
		readTime();
		calcu();
		return speed;
	}
}
