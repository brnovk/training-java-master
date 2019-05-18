package by.training.beans;

/**
 * Purchase with discount of bonus product.
 * @author Baranov Victor
 */
public class BuyDiscountBonus extends Buy {

    /**
     * Ordinal number of the class to sorting.
     */
    private final static int ORDER = 3;

    /**
     * Price bonus product.
     */
    private final double costBonus;

    public BuyDiscountBonus(long count, GoodsPiece item, int year, int month,
    		int day, double costBonus) {
        super(count, item, year, month, day);
        this.costBonus = costBonus;
    }
    
    @Override
    double calculateCost() {
        double tmp;
        tmp = (goodsPiece.getCostBatch() / goodsPiece.getNumberInBatch()) * count;
        return tmp - costBonus;
    }

    /**
     * Ordinal number of the class.
     */
    @Override
    public int getOrder() {
        return ORDER;
    }

    @Override
    public double getCostBonus() {
        return costBonus;
    }

    @Override
	public float getPercent() {
        return 0;
    }

    @Override
	public double getCostShipping() {
        return 0;
    }
}
