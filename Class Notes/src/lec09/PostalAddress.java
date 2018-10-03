package lec09;

import java.util.ArrayList;
import java.util.List;

public class PostalAddress {
	public final int number;
	public final String name;
	
	public PostalAddress(int n, String s) {
	    number = n;
	    name = s;
	}
	
	public PostalAddress(String textAddress) { // "6 Maple St'
	    String[] matches = textAddress.split("\\s+", 2);
	    number = Integer.parseInt(matches[0]);
	    name = matches[1];
	}
	
	public String toString() {
		return name + ", " + number; 
	}
	
	public boolean equals(Object o) {
	    if (!(o instanceof PostalAddress)) return false;
	    PostalAddress p = (PostalAddress)o;
	    return number == p.number && name.equals(p.name);
	}
	
	public static void main(String[] args) {
		List<PostalAddress> l = new ArrayList<>();
		
		for (int i = 1; i <= 10; i++) {
			l.add(new PostalAddress(i, "Maple St"));
		}
		
		for (int i = 10; i > 0; i -= 1) {
			l.add(new PostalAddress(i, "Birch St"));
		}
		
		System.out.println(l);
		
		l.sort(new PostalOrderComparator());
		
		System.out.println(l);
	}
		
	
	public int compareTo(PostalAddress o) {
	    if (name.compareTo(o.name) < 0) return -1;
	    if (name.compareTo(o.name) > 0) return 1;
	    return Integer.compare(number, o.number);
	}
}
