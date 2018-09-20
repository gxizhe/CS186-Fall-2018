public class Bus {
    private int number;

    public Bus() {
    }
    public Bus(int n) {
        number = n;
    }

    public static void main(String[] args) {
    	Bus busA = new Bus();
    	Bus busB = new Bus();

    	busA.number(44);
    	busB.number(44);

    	System.out.println(busA == busB);
    	System.out.println(busA.equals(busB));

    	Bus busC = busB;
    	busB = busA;

    	busA.number(13);

    	System.out.println(busA == busC);
    	System.out.println(busA.equals(busC));

    	busC = busB;

    	System.out.println(busA == busC);
    	System.out.println(busA.equals(busC));
    }

    public int getNumber() {
        return number;
    }

    public void number(int number) {
        this.number = number;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + number;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Bus other = (Bus) obj;
        if (number != other.number)
            return false;
        return true;
    }
}