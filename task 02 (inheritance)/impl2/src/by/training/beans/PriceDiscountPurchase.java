package by.training.beans;

import by.training.ifaces.AbstractPurchase;

/**
 * @author Baranau Viktar
 */
public class PriceDiscountPurchase extends AbstractPurchase {

    /**
     * The discount amount per unit of product.
     */
    private long amountDiscount;

    public PriceDiscountPurchase() {
    }

    public PriceDiscountPurchase(int numberUnits, Commodity commodity, 
    		long amountDiscount) {
        super(numberUnits, commodity);
        this.amountDiscount = amountDiscount;
    }

    public long getAmountDiscount() {
        return amountDiscount;
    }

    public void setAmountDiscount(long amountDiscount) {
        this.amountDiscount = amountDiscount;
    }

    @Override
    public String toString() {
        return (super.toString() + amountDiscount + ';');
    }

    @Override
    public long getCost() {
        return (getCommodity().getPrice() - amountDiscount) * getNumberUnits();
    }
}
