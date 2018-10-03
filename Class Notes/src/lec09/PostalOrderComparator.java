package lec09;

import java.util.Comparator;

public class PostalOrderComparator implements Comparator<PostalAddress> {
	
	@Override
	public int compare(PostalAddress o1, PostalAddress o2) {
	    if (o1.name.compareTo(o2.name) < 0) return -1;
	    if (o1.name.compareTo(o2.name) > 0) return 1;
	    if (o1.number % 2 == 1 && o2.number % 2 == 0) return -1;
	    if (o1.number % 2 == 0 && o2.number % 2 == 1) return 1;
	    if (o1.number % 2 == 1) return Integer.compare(o1.number, o2.number);
	    if (o1.number % 2 == 0) return Integer.compare(o2.number, o1.number);
	    return 0;
	}
}
