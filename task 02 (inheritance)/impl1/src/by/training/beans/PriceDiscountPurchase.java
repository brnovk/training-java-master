package by.training.beans;

/**
 * Class describes the purchase of a product at a discount price.
 * @author Baranau Viktar
 */
public class PriceDiscountPurchase extends Purchase {

    /**
     * The discount amount per unit of product.
     */
    private long amountDiscount;

    public PriceDiscountPurchase() {
    }

    public PriceDiscountPurchase(String name, long price, int numberUnits, 
    		long amountDiscount) {
        super(name, price, numberUnits);
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
        return super.toString() + amountDiscount + ';';
    }

    @Override
    public long getCost() {
         return (getPrice() - amountDiscount) * getNumberUnits();
    } 
}
