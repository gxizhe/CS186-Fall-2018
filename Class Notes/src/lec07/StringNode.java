package lec07;

public class StringNode {
	private final String contents;
	private StringNode next;
	
	public StringNode(String contents) {
		this.contents = contents;
		next = null;
	}
	
	public String getContents() {
		return contents;
	}
	
	public void setNext(StringNode n) {
		next = n;
	}
	
	public StringNode getNext() {
		return next;
	}
}
