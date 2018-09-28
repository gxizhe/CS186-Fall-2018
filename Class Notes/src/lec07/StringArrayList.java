package lec07;

public class StringArrayList implements StringListInterface {
    String[] array;
    int size;
    
    public StringArrayList() {
    	array = new String[10];
    	size = 0;
    }
    
	@Override
	public void add(String s) {
		if (size == array.length) {
			enlarge();
		}
		size++;
		array[size] = s;
	}
	
	void enlarge() {
		String[] larger = new String[array.length * 2];
		for (int i = 0; i < array.length; i++) {
			larger[i] = array[i];
		}
		array = larger;
	}
	
	@Override
	public void add(int i, String s) throws IndexOutOfBoundsException {
		if (i >= size || i < 0) {
			throw new IndexOutOfBoundsException();
		}

		if (size == array.length) {
			enlarge();
		}
		
		for (int j = size; j > i; j--) {
			array[j] = array[j-1];
		}
		
		array[i] = size;
		size++;
	}
	
	@Override
	public String remove(int i) throws IndexOutOfBoundsException {
		final String removed = array[i];
		if (i >= size || i < 0) {
			throw new IndexOutOfBoundsException();
		}

		for (int j = i; j < size - 1 ; j++) {
			array[j] = array[j+1];
		}

		  // optional
		array[size-1] = null;

		size--;
		return removed;
	}
	
	public String get(int i) throws IndexOutOfBoundsException {
		if (i >= size || i < 0) {
			throw new IndexOutOfBoundsException();
		}
		return array[i];
	}
	
	@Override
	public int size() {
		return size;
	}
	
	public void set(int i, String s) {
		if (i >= size || i < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		array[i] = s;
	}
	
	public int indexOf(String s) {
		for (int i = 0; i < size; i++) {
			if (array[i].contains(s) == true) {
				return i;
			}
		}
		return -1;
	}
}

