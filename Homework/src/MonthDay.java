
public class MonthDay {
	private int m;
	private int d; 
	public MonthDay (int month, int day) {
		this.m = month;
		this.d = day;
		
		if (month > 12 && month < 1) {
			throw new IllegalArgumentException("Month must be 1 to 12.");
		}
		
		if (day > 31) {
			throw new IllegalArgumentException("Day must not be more than 31.");
		}
	}
	
	public String toString() {
		return "" + d + " " + m;
	}
}
