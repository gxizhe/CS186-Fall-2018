package lec06;

import java.util.ArrayList;
import java.util.List;

public class CompareContrast {
	public static void main(String args[]) {
		String[] strings;
		strings = new String[50];
		strings[0] = "Hi";
		System.out.println(strings[0]);
		System.out.println(strings.length);
		
		List<String> stringsList;
		stringsList = new ArrayList<>();
		stringsList.add("Hi");
		stringsList.add(0, "!!!");
		stringsList.remove(stringsList.size() - 1);
		System.out.print(stringsList.get(0));	
		stringsList.set(0, "Boo!");
	}
}