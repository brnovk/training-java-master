package by.training.beans;

/**
 * Purchase with discounted of shipping product.
 * @author Baranov Victor
 */
public class BuyDiscountTransport extends Buy {

    /**
     * Ordinal number of the class to sorting.
     */
    private final static int ORDER = 2;

    /**
     * Cost of shipping.
     */
    private final double transportExpenses;

    public BuyDiscountTransport(long count, GoodsPiece item, int year,
    		int month, int day, double transportExpenses) {
        super(count, item, year, month, day);
        this.transportExpenses = transportExpenses;
    }

    @Override
    public int getOrder() {
        return ORDER;
    }

    @Override
    double calculateCost() {
        double tmp;
        tmp = (goodsPiece.getCostBatch() / goodsPiece.getNumberInBatch()) * count;
        return tmp - transportExpenses;
    }

    @Override
	public float getPercent() {
        return 0;
    }

    @Override
	public double getCostShipping() {
        return transportExpenses;
    }

    @Override
	public double getCostBonus() {
        return 0;
    }
}
