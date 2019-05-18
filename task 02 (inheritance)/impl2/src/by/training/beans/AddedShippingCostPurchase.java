package by.training.beans;

import by.training.ifaces.AbstractPurchase;

/**
 * @author Baranau Viktar
 */
public class AddedShippingCostPurchase extends AbstractPurchase {

    /**
     * Transport expenses for the purchase of.
     */
    private long shippingCost;

    public AddedShippingCostPurchase() {
    }

    public AddedShippingCostPurchase(int numberUnits, Commodity commodity, 
    		long shippingCost) {
        super(numberUnits, commodity);
        this.shippingCost = shippingCost;
    }

    public long getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(long shippingCost) {
        this.shippingCost = shippingCost;
    }

    @Override
    public String toString() {
        return (super.toString() + shippingCost + ';');
    }

    @Override
    public long getCost() {
        return getCommodity().getPrice() * getNumberUnits() + shippingCost;
    }
}
