package com.alex.image_processing;

import java.lang.Math;

public class cara_get_r {

	final static double MIN=0.0;
	final static double MAX=999999.0;

	private double r;
	private double tempr;
	private double n;
	double temp;
	double max;
	double min;
	
	public double calculcateR(int flag, double frequency) {
		min=MIN;
		max=MAX;
		switch(flag) {
		case 1:
			/* first model here */
			n = frequency * frequency / 81000.0;
			tempr = ( MIN + MAX ) / 2;
			temp = (1.36 * Math.pow(tempr, (-2.1)) + 168 * Math.pow(tempr, (-6.13))) * 1000.0;
			while( Math.abs( n-temp ) >= 0.0001 ) {
				if( n > temp ) {
					max = tempr;
					tempr = ( min + max ) / 2.0;
				}
				else {
					min = tempr;
					tempr = ( min + max ) / 2.0;
				}
				temp = (1.36 * Math.pow(tempr, (-2.1)) + 168.0 * Math.pow(tempr, (-6.13))) * 1000.0;
			}

		    r = tempr * 6.955 * Math.pow(10,8);
			break;
		case 2:
			/* second model here */
			break;
		case 3:
			/* third model here */
			break;
		default:
			System.out.println("WRONG MODEL CHOOSE");
		}
		return r;
	}
	
}
