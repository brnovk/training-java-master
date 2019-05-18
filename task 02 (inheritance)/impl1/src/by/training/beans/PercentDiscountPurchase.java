package by.training.beans;

/**
 * Class describes the purchase of a product at a discount percent.
 * @author Baranau Viktar
 */
public class PercentDiscountPurchase extends Purchase {
    
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

    public PercentDiscountPurchase(String name, long price, int numberUnits, 
    		double percent) {
        super(name, price, numberUnits);
        this.percent = percent;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
    
    @Override
    public String toString() {
        return super.toString() + percent + ';';
    }
    
    @Override
    public long getCost() {
        if (getNumberUnits() > LIMIT_DISCOUNT) {
            return (long) (super.getCost() * (1 - percent / 100));
        } else {
            return super.getCost();
        }
    }
}
