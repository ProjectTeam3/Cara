package com.alex.image_processing;

import java.io.*;
import java.awt.image.*;
import java.lang.*;

import javax.imageio.ImageIO;

public class Point {
	BufferedImage original;
	cara_cal cc;
	int width;
	int height;
	double[] resultHz;
	int[] resultx;
	int timestart;
	int timeend;
	int startpixelx;
	int startpixely;
	int endpixelx;
	int endpixely;
	double speed;
	Point(String address) throws IOException{
		original = ImageIO.read(new File(address));
		startpixelx=170;
		startpixely=50;
		endpixelx=1730;
		endpixely=550;
		width = endpixelx-startpixelx;
		height = endpixely-startpixely;
		resultHz = new double[2];
		resultx = new int[2];
		timestart=28800;
		timeend=75600;
	}
	
	private double getHz(int y){
		if(y<=125){
			return ((double)y/125.0)*39+18;
		}
		else if(y<=250){
			return ((double)(y-125)/125.0)*123+57;
		}
		else if(y<=375){
			return (((double)(y-250)/125.0)*390+180);
		}
		else
			return ((double)(y-375)/125.0)*1230+570;
			
	}
	public double handler(int x1, int x2,int y1,int y2){
			if(x1<startpixelx || x2<startpixelx)
				return -1;
			if(x1>=endpixelx || x2>=endpixelx)
				return -1;
			if(y1<startpixely || y2<startpixely)
				return -1;
			if(y1>=endpixely || y2>=endpixely)
				return -1;
			if(x1>x2){
				int t=x1;
				x1=x2;
				x2=t;
				t=y1;
				y1=y2;
				y2=t;
			}
			resultHz[0]=getHz(endpixely-y1);
			resultHz[1]=getHz(endpixely-y2);
			resultx[0]=(int)(((double)((double)(x1-startpixelx))/(double)endpixelx)*(double)(timeend-timestart)+timestart);
			resultx[1]=(int)(((double)((double)(x2-startpixelx))/(double)endpixelx)*(double)(timeend-timestart)+timestart);
			System.out.println(resultHz[0]);
			System.out.println(resultHz[1]);
			System.out.println(resultx[0]);
			System.out.println(resultx[1]);
			cc = new cara_cal(resultHz[0], resultHz[1], resultx[0], resultx[1]);
			speed=cc.calculcate(1);
			return speed;
	}
	

}
