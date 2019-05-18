package by.training.beans;

/**
 * Purchase without discount.
 * @author Baranov Victor
 */
public class BuyNoDiscount extends Buy {

    /**
     * Ordinal number of the class to sorting.
     */
    private final static int ORDER = 0;

    public BuyNoDiscount(long count, GoodsPiece goodsPiece, int year, int month, int day) {
        super(count, goodsPiece, year, month, day);
    }

    @Override
    double calculateCost() {
        return (goodsPiece.getCostBatch() / goodsPiece.getNumberInBatch()) * count;
    }

    /**
     * Ordinal number of the class.
     */
    @Override
    public int getOrder() {
        return ORDER;
    }

    @Override
	public float getPercent() {
        return 0;
    }

    @Override
	public double getCostShipping() {
        return 0;
    }

    @Override
	public double getCostBonus() {
        return 0;
    }
}
