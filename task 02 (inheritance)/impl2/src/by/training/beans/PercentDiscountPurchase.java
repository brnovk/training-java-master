package by.training.beans;

import by.training.ifaces.AbstractPurchase;

/**
 * @author Baranau Viktar
 */
public class PercentDiscountPurchase extends AbstractPurchase {

    /**
     * The constant minimum amount of goods for the discount.
     */
    private static final int LIMIT_DISCOUNT = 10;

    /**
     * The percentage discount.
     */
    private double percent;

    public PercentDiscountPurchase() {
    }

    public PercentDiscountPurchase(int numberUnits, Commodity commodity, 
    		double percent) {
        super(numberUnits, commodity);
        this.percent = (numberUnits > LIMIT_DISCOUNT) ? percent : 0;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = (super.getNumberUnits() > LIMIT_DISCOUNT) ? percent : 0;
    }

    @Override
    public String toString() {
        return (super.toString() + percent + ';');
    }

    @Override
    public long getCost() {
        return (long) (getCommodity().getPrice() 
        		* getNumberUnits() * (1 - percent/100));
    }
}
