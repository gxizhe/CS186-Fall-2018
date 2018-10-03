package lec09;

import java.util.ArrayList;
import java.util.List;

public class IceCreamShops {
	/**
	 * a sorted list of store numbers
	 */
	private List<Integer> storeNumbers;
	
	public IceCreamShops() {
		storeNumbers = new ArrayList<>();
	}
	
	public void addStore(int n) {
		if (storeNumbers.isEmpty()) {
			storeNumbers.add(n);
			return;
		}
		
		int i = 0; // the location we'll insert
		for (Integer storeNumber: storeNumbers) {
			if (storeNumber.compareTo(n) >= 0) {
	            storeNumbers.add(i, n);
	            return;
	        }
	        i++;
	    }
	    storeNumbers.add(n);
	}
}