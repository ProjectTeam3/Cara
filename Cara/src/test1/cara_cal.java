package cara_calculcate;

public class cara_cal {

	private static double ans;
	static double r1;
	static double r2;
	
	public static double calculcate(int flag, double frequency1, double frequency2, int timestart, int timeend) {
		r1 = cara_get_r.calculcateR(flag, frequency1);
		r2 = cara_get_r.calculcateR(flag, frequency2);
		ans = cara_get_v.calculcateV(r1, r2, timestart, timeend);
		return ans;
	}
	
	/* test */
	/*public static void main(String[] args)
	{
		System.out.println(calculcate(1, 1437.5, 1627.3, 1005, 1023));
	}*/
}
