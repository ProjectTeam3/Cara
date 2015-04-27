package calc;

import java.io.*;
import java.awt.image.*;
import java.lang.*;

import javax.imageio.ImageIO;

public class ResponsivePoint {
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
	public ResponsivePoint(String address) throws IOException{
		original = ImageIO.read(new File(address));
		width = original.getWidth();//endpixelx-startpixelx;
		height = original.getHeight();//endpixely-startpixely;
		resultHz = new double[2];
		resultx = new int[2];
		timestart=0;
		timeend=43200*(width/(1730-170));
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

			if(x1>x2){
				int t=x1;
				x1=x2;
				x2=t;
				t=y1;
				y1=y2;
				y2=t;
			}
			System.out.println("x1:"+x1+" x2:"+x2+" y1:"+y1+" y2:"+y2+" height:"+height+" width:"+width);
			resultHz[0]=getHz(height-y1);
			resultHz[1]=getHz(height-y2);
			resultx[0]=0;
			resultx[1]=(43200*width/1560);
			System.out.println(resultHz[0]);
			System.out.println(resultHz[1]);
			System.out.println(resultx[0]);
			System.out.println(resultx[1]);
			System.out.println("aaaaaaaaaaaaaaa");
			cc = new cara_cal(resultHz[0], resultHz[1], resultx[0], resultx[1]);
			speed=cc.calculcate(1);
			return speed;
	}
	

}
