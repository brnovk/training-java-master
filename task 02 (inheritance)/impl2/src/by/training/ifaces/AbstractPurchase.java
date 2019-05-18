package by.training.ifaces;

import by.training.beans.Commodity;

/**
 * @author Baranau Viktar
 */
public abstract class AbstractPurchase implements Comparable <AbstractPurchase> {

    /**
     * Number of purchased units.
     */
    private int numberUnits;
    
    /**
     * Purchased goods.
     */
    private final Commodity commodity;

    public AbstractPurchase() {
        commodity = null;
    }

    public AbstractPurchase(int numberUnits, Commodity commodity) {
        this.numberUnits = numberUnits;
        this.commodity = commodity;
    }
    
    // getters
    
    public int getNumberUnits() {
        return numberUnits;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    // setters

    public void setNumberUnits(int numberUnits) {
        this.numberUnits = numberUnits;
    }

    @Override
    public String toString() {
        return commodity + "" + numberUnits + ';' + getCost() + ';';
    }

    @Override
    public int compareTo(AbstractPurchase another) {
      return (int) (another.getCost() - getCost());
    }

    public abstract long getCost();

}
