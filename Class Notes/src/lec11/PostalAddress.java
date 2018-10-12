package lec11;

import java.time.LocalDateTime;

public class PostalAddress {
    public final String name;
    public final int number;
    public final LocalDateTime created;
    public PostalAddress(String name, int number) {
        this.name = name;
        this.number = number;
        created = LocalDateTime.now();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        PostalAddress other = (PostalAddress) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (number != other.number)
            return false;
        return true;
    }
    
    public static void main(String[] args) {
    	
    }
}
