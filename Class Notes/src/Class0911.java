
public class Class0911 {
	void doAll(int[] a) {
		for (int e: a) {
			System.out.println(e);
		}
	}
	
	// checking that all element obey some property
	boolean allEven(int[] a) {
		for (int e: a) {
			if (e % 2 != 0) {
				return false;
			}
		}
		return true;
	}
	
	// looking for a single thing in a iterable
	boolean contains(int[] a, int value) {
		for(int e: a) {
			if (e == value) {
				return true;
			}
		}
		return false;
	}
}
