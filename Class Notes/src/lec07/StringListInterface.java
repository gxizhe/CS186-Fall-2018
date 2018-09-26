package lec07;

public interface StringListInterface {
    public void add(String s);
    public void add(int i, String s) throws IndexOutOfBoundsException;
    public String remove(int i) throws IndexOutOfBoundsException;
    public String get(int i) throws IndexOutOfBoundsException;
    public int size();
}