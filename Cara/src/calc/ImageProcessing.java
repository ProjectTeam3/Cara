package calc;

import java.io.*;
import java.awt.image.*;
import java.lang.*;

import javax.imageio.ImageIO;

//import auto_find.HoughLineFilter;

//import calc.cara_cal;

public class ImageProcessing {

	BufferedImage original;
	//cara_cal cc;
	int threshold;
	int width;
	int height;
	int[][] gray;
	int[][] binary;
	int[][] rode2;
	int[][] rode1;
	int[][] rode;
	int blocksize;
	static int[][] block;
	boolean[][] flag;
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

	public ImageProcessing(BufferedImage in) throws IOException{
		original = in;
		width = original.getWidth();
		height = original.getHeight();
		gray = new int[width][height];
		binary = new int[width][height];
		rode = new int[width][height];
		rode1 = new int[width][height];
		rode2 = new int[width][height];
		block = new int[width*height][2];
		flag= new boolean[width][height];
		threshold = 0;
		realNumber=-1;
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
		for (int i=2;i<width-2;i++){
			for (int j=2;j<height-2;j++){
				rode1[i][j]=1;
				for(int t=i-2;t<i+2;t++){
					for (int k=j-2;k<j+2;k++){
						if (binary[t][k]==0){
							rode1[i][j]=0;
							break;
						}
					}
				}
			}
		}
		for (int i=2;i<width-2;i++){
			for (int j=2;j<height-2;j++){
				rode[i][j]=1;
				for(int t=i-2;t<i+2;t++){
					for (int k=j-2;k<j+2;k++){
						if (rode1[t][k]==0){
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
					
					if(j<=95){
						frequency[i]+=(double) (180.0-((double)j)/((double)95)*105.0);
					}
					else{
						frequency[i]+=(double) (((double)(height-j))/((double)(height-95))*50);
					}
					//frequency[i]+=(double) (180.0-((double)j)/((double)height)*155.0);
					number[i]++;
				}
			}
			if (number[i]!=0){
				realNumber++;
				frequency1[realNumber]=frequency[i]/(double)number[i];
				codx[realNumber]=i;
			}
		}
		resultHz[0]=frequency1[realNumber*5/12];
		resultx[0]=codx[realNumber*5/12];
		resultHz[1]=frequency1[realNumber/2];
		resultx[1]=codx[realNumber/2];
		resultHz[2]=frequency1[realNumber*7/12];
		resultx[2]=codx[realNumber*7/12];
		
	}
	public void explore(int i, int j){
		flag[i][j]=true;
		blocksize++;
		if (blocksize>5000) return;
		block[blocksize][0]=i;
		block[blocksize][1]=j;
		if(i-1>=0 && rode[i-1][j]==1 && flag[i-1][j]==false){
			explore(i-1,j);
		}
		if(i+1<width && rode[i+1][j]==1 && flag[i+1][j]==false){
			explore(i+1,j);
		}
		if(j+1<height && rode[i][j+1]==1 && flag[i][j+1]==false){
			explore(i,j+1);
		}
		if(j-1>=0 && rode[i][j-1]==1 && flag[i][j-1]==false){
			explore(i,j-1);
		}
	}
	public void process() throws IOException{
		getGray();
		ostu();
		getBinary();
		corrode();
		//System.out.println(3);
		for (int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				flag[i][j]=false;
				}
			}
		for (int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				if (flag[i][j]==false && rode[i][j]==1){
				blocksize=0;
				explore(i,j);
				System.out.println(blocksize);
				if(blocksize<=200){
					for(int t=1;t<=blocksize;t++){
						rode[block[t][0]][block[t][1]]=0;
						}
					}
				}
			}
		}
		int i=0,j=0,start,end,number=-1,count,time=0;
		int[] sum=new int[30];
		BufferedImage[] newpic=new BufferedImage[200];
		BufferedImage[] newp=new BufferedImage[200];
		BufferedImage temp=null,tem=null;
		while(i<width){
			j=0;
			while(j<height){
				if(rode[i][j]==1){
					start=i-50;
					if(start<0) start=0;
					end=i+50;
					if(end>=width) end=width-1;
					count=0;
					for(int p=start;p<=end;p++){
						for (int q=0;q<height;q++){
							if(rode[p][q]==1){
								count++;
							}
						}
					}
					System.out.println(count);
					if(count>=4000){
					number++;
					temp=original.getSubimage(start,0,end-start,height);
					newp[number]=original.getSubimage(start,0,end-start,height);
					//ImageIO.write(temp, "gif", new File("D:/newnew"+Integer.toString(number)+".gif"));
					newpic[number]=new BufferedImage(end-start,height,BufferedImage.TYPE_BYTE_BINARY);
					float[] elements = { -1.0f, 0.0f, 1.0f, -2.0f, 0f, 2.0f, -1.0f,  
	                0.0f, 1.0f };  
	    			Kernel kernel = new Kernel(3, 3, elements);  
	    			ConvolveOp cop = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
	    			cop.filter(temp, newpic[number]);
	    			ImageProcessing ip1=new ImageProcessing(newpic[number]){
	    				@Override
	    				public void corrode(){
	    					for (int i=1;i<width-1;i++){
	    						for (int j=1;j<height-1;j++){
	    							rode[i][j]=1;
	    							for(int t=i-1;t<i+1;t++){
	    								for (int k=j-1;k<j+1;k++){
	    									if (binary[t][k]==0){
	    										rode[i][j]=0;
	    										break;
	    									}
	    								}
	    							}
	    						}
	    					}
	    				}
	    			};
	    			ip1.getGray();
	    			ip1.ostu();
	    			ip1.getBinary();
	    			ip1.corrode();
	    			sum[number]=0;
	    			System.out.println();
	    			for(int c=0;c<ip1.width;c++){
	    				for(int d=0;d<ip1.height;d++){
	    					sum[number]=sum[number]+ip1.rode[c][d];
	    				}
	    			}
	    			System.out.println(sum[number]);
	    			
	    			
					//ImageIO.write(newpic[number], "gif", new File("D:/new"+Integer.toString(number)+".gif"));
					//System.out.println(number);
					//System.out.println(count);
					i=end;
					j=height;
	    			}
				}
				j++;
			}
			i++;
		}
		int max,item=0;
		for(int t=0;t<=4;t++){
			max=-1;
			for(i=0;i<=number;i++){
				if(sum[i]>max){
					item=i;
					max=sum[i];
				}
			}	
			if(max==-1){
				break;
			}
			ImageIO.write(newp[item], "gif", new File("new"+Integer.toString(t)+".gif"));
			sum[item]=-1;
		}
	}
}
