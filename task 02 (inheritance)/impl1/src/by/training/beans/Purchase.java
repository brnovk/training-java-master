package by.training.beans;

import java.util.Objects;

/**
 * Class describes the purchase of a product without discount.
 * @author Baranau Viktar
 */
public class Purchase {

    /**
     * Commodity name.
     */    
    private String name;

    /**
     * Price in belarusian rubles.
     */
    private long price;

    /**
     * Number of purchased units.
     */
    private int numberUnits;

    public Purchase() {
    }

    public Purchase(String name, long price, int numberUnits) {
        this.name = name;
        this.price = price;
        this.numberUnits = numberUnits;
    }

    // getters
    
    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public int getNumberUnits() {
        return numberUnits;
    }

    // setters
    
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setNumberUnits(int numberUnits) {
        this.numberUnits = numberUnits;
    }

    @Override
    public String toString() {
        return name + ";" + price + ";" + numberUnits + ";" + getCost() + ';';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Purchase)) {
            return false;
        }
        final Purchase other = (Purchase) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.price != other.price) {
            return false;
        }
        return true;
    }

    public long getCost() {
        return price * numberUnits;
    } 
}
