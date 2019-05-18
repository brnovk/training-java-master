package by.training.beans;

import by.training.exceptions.NonpositiveArgumentException;

/**
 * A subclass of Purchase that describes the purchase with a discount price
 * @see by.training.purchases.Purchase
 * @author BaranauViktar
 */
public class PriceDiscountPurchase extends Purchase {

    /**
     * The discount amount per unit of product.
     */
    private int amountDiscount;

    public PriceDiscountPurchase() {
    }

    public PriceDiscountPurchase(String name, int price, int numberUnits, int amountDiscount) {
        super(name, price, numberUnits);
        this.amountDiscount = amountDiscount;
    }

    public int getAmountDiscount() {
        return amountDiscount;
    }

    public void setAmountDiscount(int amountDiscount) {
    	if (isValidValue(amountDiscount)) {
    		this.amountDiscount = amountDiscount;
    	} else {
    		throw new NonpositiveArgumentException(amountDiscount, "amount discount");
    	}
    }
    
    @Override
    public String toString() {
        return super.toString() + amountDiscount + ';';
    }

    @Override
    public int getCost() {
         return (getPrice() - amountDiscount) * getNumberUnits();
    } 
}
