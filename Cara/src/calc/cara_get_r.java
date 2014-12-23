package calc;
import java.lang.Math;

public class cara_get_r {

	final double MIN = 0.0;
	final double MAX = 100000000.0;

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
			n = frequency * frequency / (81.0 * 4.0);
			tempr = ( MIN + MAX ) / 2.0;
			temp = (1.36 * Math.pow(tempr, (-2.1)) + 168.0 * Math.pow(tempr, (-6.13))) * Math.pow(10, 6);
			while( Math.abs( n-temp ) >= 0.0001 ) {
				if( n > temp ) {
					max = tempr;
					tempr = ( min + max ) / 2.0;
				}
				else {
					min = tempr;
					tempr = ( min + max ) / 2.0;
				}
				temp = 1.36 * Math.pow(tempr, (-2.1)) + 168.0 * Math.pow(tempr, (-6.13)) * Math.pow(10, 6);
			}

		    r = tempr * 6.955 * Math.pow(10,5);
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
