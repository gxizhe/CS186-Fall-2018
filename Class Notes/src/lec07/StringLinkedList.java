package lec07;

public class StringLinkedList implements StringListInterface {
    int size;
    StringNode head;

    public StringLinkedList() {
        size = 0;
        head = null;
    }

    @Override
    public int size() {
        return size;
    }

	@Override
	public void add(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(int i, String s) throws IndexOutOfBoundsException {
	    if (i < 0 || i >= size) {
	        throw new IndexOutOfBoundsException();
	    }

	    size++;
	    StringNode node = new StringNode(s);

	    // adding to the front of the list
	    if (i == 0) {
	        // make the current head follow this node
	         node.setNext(head);
	         // then make this node the new head
	         head = node;
	         return;
	    }


	    // otherwise, traverse the list to find the *node-before* the new node
	    // we want to insert
	    StringNode nodeBefore = head;
	    for (int j = 0; j < i-1; j++) {
	         nodeBefore = nodeBefore.getNext();
	    }

	    // set the next node of the new node to the node-before's next node
	    node.setNext(nodeBefore.getNext());

	    // then set the node-before's node to the new node
	    nodeBefore.setNext(node);
	}

	@Override
	public String remove(int i) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String get(int i) throws IndexOutOfBoundsException {
	    if (i < 0 || i >= size) {
	        throw new IndexOutOfBoundsException();
	    }
	    int j = 0;
	    StringNode current = head;
	    while (current != null) {
	        if (i == j) {
	            return current.getContents();
	        }
	        current = current.getNext();
	        j++;
	    }
		return null;
	}
	
	public int indexOf(String s) {
		StringNode position = head; 
		for(int i = 0; i < size; i ++) {
			if (head.getContents().equals(s)) {
				return i;
			}
			else {
				position = position.getNext();
			}
		}
		return -1;
	}
}