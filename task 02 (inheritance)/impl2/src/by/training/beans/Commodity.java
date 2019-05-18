package by.training.beans;

/**
 * Class describing the commodity.
 * @author Baranau Viktar
 */
public class Commodity {

    private String name;
    private long price;

    public Commodity() {
    }

    public Commodity(String name, long price) {
        this.name = name;
        this.price = price;
    }

    // getters
    
    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }
    
    // setters

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name + ';' + price + ';';
    }
}
