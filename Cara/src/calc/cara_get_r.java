package calc;

public class cara_get_r {

	final double MIN = 0.0;
	final double MAX = 100000000.0;

	private double r;
	private double tempr;
	private double n;
	double temp;
	double max;
	double min;
	
	/*get absolute value*/
	private double cal_abs(double a){
		if(a < 0){
			return -a;
		}
		return a;
	}
	
	/*compute square root in [0,2)*/
	private double cal_sqrtin2(double a){
	    double sum,coffe,factorial,xpower,term;
	    int i;
	    sum=0.0;
	    coffe=1.0;
	    factorial=1.0;
	    xpower=1.0;
	    term=1.0;
	    i=0;
	    while(cal_abs(term)>0.000001){
	        sum+=term;
	        coffe*=(0.5-i);
	        factorial*=(i+1);
	        xpower*=(a-1);
	        term=coffe*xpower/factorial;
	        i++;
	    }
	    return sum;
	}

	/*compute square root by change every number into [0,2)*/
	double cal_sqrt(double a){
	    double correction = 1.0;
	    
	    if(a<0){
			System.out.println("WRONG NUMBER");
			return -1;
		}
	    
	    while(a >= 2){
	        a /= 4;
	        correction *= 2.0;
	    }
	    return cal_sqrtin2(a)*correction;
	}
	
	/*compute integer power*/
	private double cal_int_pow(double a, int b){
		double ans=a;
		
		if(b>0){
			while(b>1){
				ans *= a;
				b--;
			}
		}
		else{
			b = -b;
			while(b>1){
				ans *= a;
				b--;
			}
			ans = 1.0/ans;
		}
		return ans;
	}

	/*compute natural logarithm*/
	private double cal_ln(double a){
		double correction = 0.0, ans = 0.0, temp = 0.0;
		
		if(a<0){
			System.out.println("WRONG NUMBER");
			return -1;
		}
		
		if(a>=1){
			while(a >= 10){
				a /= 10;
				correction += 1;
				}
		}
		else{
			while(a < 1){
				a *= 10;
				correction -= 1;
			}
		}
		
		if(a > cal_sqrt(10)){
			a = cal_sqrt(a);
			temp = (a-1)/(a+1);
			ans = 2 * (temp + cal_int_pow(temp,3)/3 + cal_int_pow(temp,5)/5 + cal_int_pow(temp,7)/7);
			ans *= 2;
			ans += 2.3025851*correction;
		}
		else{
			temp = (a-1)/(a+1);
			ans = 2 * (temp + cal_int_pow(temp,3)/3 + cal_int_pow(temp,5)/5 + cal_int_pow(temp,7)/7);
			ans += 2.3025851*correction;
		}
		
		return ans;
	}
	
	/*compute exp*/
	private double cal_exp(double a){
		double ans = 0.0, b = 0.0, correction = 1.0;
		int temp = 0;
		double e = Math.E;
		
		if(a > 0){
			temp = (int )(a + 0.5);
		}
		else{
			temp = (int )(a - 0.5);
		}
		
		b = (double )temp;
		b = a - b;
		
		if(temp > 0){
			while(temp >= 1){
				correction *= e;
				temp--;
			}
		}
		else{
			temp = -temp;
			while(temp >= 1){
				correction *= e;
				temp--;
			}
			correction = 1.0/correction; 
		}
		
		ans = 1 + b + cal_int_pow(b,2)/2 + cal_int_pow(b,3)/6 + cal_int_pow(b,4)/24 + cal_int_pow(b,5)/120;
		ans = ans * correction;
		
		return ans;
	}
	
	/*achieve pow*/
	private double cal_pow(double a,double b){
		return cal_exp(cal_ln(a) * b);
	}
	
	
	public double calculcateR(int flag, double frequency) {
		min=MIN;
		max=MAX;
		switch(flag) {
		case 1:
			/* first model here */
			n = frequency * frequency / (81.0 * 4.0);
			tempr = ( MIN + MAX ) / 2.0;
			temp = (1.36 * cal_pow(tempr, (-2.1)) + 168.0 * cal_pow(tempr, (-6.13))) * cal_pow(10, 6);
			while( cal_abs( n-temp ) >= 0.0001 ) {
				if( n > temp ) {
					max = tempr;
					tempr = ( min + max ) / 2.0;
				}
				else {
					min = tempr;
					tempr = ( min + max ) / 2.0;
				}
				temp = (1.36 * cal_pow(tempr, (-2.1)) + 168.0 * cal_pow(tempr, (-6.13))) * cal_pow(10, 6);
			}

		    r = tempr * 6.955 * cal_pow(10,5); 
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
