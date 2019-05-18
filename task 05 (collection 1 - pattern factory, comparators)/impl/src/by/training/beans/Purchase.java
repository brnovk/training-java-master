package by.training.beans;

import java.util.Objects;
import by.training.exceptions.NonpositiveArgumentException;

/**
 * Superclass, describing the commodity purchase Purchase. 
 * A subclass that describes the purchase with a discount price.
 * @see by.training.purchases.PriceDiscountPurchase
 * @author BaranauViktar
 */
public class Purchase {
    
    /**
     * commodity name.
     */    
    private String name;
    
    /**
     * price in belarusian rubles.
     */
    private int price;
    
    /**
     * number of purchased units.
     */
    private int numberUnits;
    
    public Purchase() {
    }
    
    public Purchase(String name, int price, int numberUnits) {
    	setName(name);
    	setPrice(price);
    	setNumberUnits(numberUnits);
    }

    // getters
    
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getNumberUnits() {
        return numberUnits;
    }

    // setters
    
    public void setName(String name) {
    	if ("".equals(name)) {
    		throw new IllegalArgumentException();
    	}
        this.name = name;
    }

    public void setPrice(int price) {
    	if (isValidValue(price)) {
    		this.price = price;
    	} else {
    		throw new NonpositiveArgumentException(price, "price");
    	}
    }

    public void setNumberUnits(int numberUnits) {
    	if (isValidValue(numberUnits)) {
    		this.numberUnits = numberUnits;
    	} else {
    		throw new NonpositiveArgumentException(numberUnits, "number units");
    	}
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
    
    protected boolean isValidValue(int verifiedValue) {
		return verifiedValue > 0;
    }
    
    public int getCost() {
        return price * numberUnits;
    } 
}
