package lec07;

public class ListDemo {
	public static void print(StringListInterface sli) {
		for (int i = 0; i < sli.size(); i++) {
			System.out.print(sli.get(i) + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
	    StringListInterface l = new StringArrayList();

	    l.add("Marc");
	    l.add("not");
	    l.add("so good.");
	    print(l);

	    l.add(1, "is");
	    print(l);

	    l.remove(2);
	    print(l);
	}
}
