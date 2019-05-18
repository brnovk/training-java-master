package by.training.beans;

/**
 * Purchase with percent discount.
 * @author Baranov Victor
 */
public class BuyDiscountPercent extends Buy {

    /**
     * Ordinal number of the class to sorting.
     */
    private final static int ORDER = 1;
    private final float precent;

    public BuyDiscountPercent(long count, GoodsPiece item, int year, int month,
    		int day, float precent) {
        super(count, item, year, month, day);
        this.precent = precent;
    }

    @Override
    double calculateCost() {
        double tmp;
        tmp = goodsPiece.getCostBatch() * (1 - precent / 100);
        return (tmp / goodsPiece.getNumberInBatch()) * count;
    }

    /**
     * Ordinal number of the class.
     */
    @Override
    public int getOrder() {
        return ORDER;
    }

    @Override
	public double getCostShipping() {
        return 0;
    }

    @Override
	public double getCostBonus() {
        return 0;
    }

    @Override
	public float getPercent() {
        return precent;
    }
}
