package calc;

public class cara_get_v {
	private  double v;
	
	public double calculcateV(double r1, double r2, int timestart, int timeend) {
		v = Math.abs(r1 - r2) / Math.abs(timestart - timeend);
		return v;
	}

}