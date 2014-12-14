package com.alex.image_processing;

public class cara_cal {
	     double ans;
		 double r1;
		 double r2;
		cara_get_r cr;
		cara_get_v cv;
		double frequency1;
		double frequency2;
		int timestart;
		int timeend;
		
		public cara_cal(double frequency1, double frequency2, int timestart, int timeend){
			cr = new cara_get_r();
			cv = new cara_get_v();
			this.frequency1=frequency1;
			this.frequency2=frequency2;
			this.timestart=timestart;
			this.timeend=timeend;
		}
		public void change(double frequency1, double frequency2, int timestart, int timeend){
			this.frequency1=frequency1;
			this.frequency2=frequency2;
			this.timestart=timestart;
			this.timeend=timeend;
		}
		public double calculcate(int flag) {
			r1 = cr.calculcateR(flag, frequency1);
			r2 = cr.calculcateR(flag, frequency2);
			ans = cv.calculcateV(r1, r2, timestart, timeend);
			return ans;
		}
		

}

