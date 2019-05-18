package by.training.beans;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;

/**
 * Parent class for all types of purchases (with discounts and without).
 * @author Baranov Victor
 */
public abstract class Buy {

    public static final Comparator<Buy> SORT_BY_DATE = new Comparator<Buy>() {
        @Override
        public int compare(Buy o1, Buy o2) {
            return o1.dateBuy.compareTo(o2.dateBuy);
        }
    };

   public static final Comparator<Buy> SORT_BY_TYPE = new Comparator<Buy>() {
        @Override
		public int compare(Buy obj1, Buy obj2) {
		    if (obj1.getOrder() > obj2.getOrder()) {
		    	return 1;
		    } else if (obj1.getOrder() < obj2.getOrder()) {
		    	return -1;
		    } else {
		    	return 0;
		    }
		}
    };

    protected final long count;
    protected final GoodsPiece goodsPiece;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private final Calendar dateBuy;

    public Buy(long count, GoodsPiece goodsPiece, int year, int month, int day) {
        this.count = count; 
        this.goodsPiece = goodsPiece;
        this.dateBuy = Calendar.getInstance();
        this.dateBuy.set(year, --month, day);
    }

    /**
     * Getting final purchase price (including possible discounts).
     */
    public double getCost() {
        return calculateCost();
    }

    public double getDiscount() {
        double tmp;
        tmp = (goodsPiece.getCostBatch() / goodsPiece.getNumberInBatch()) * count;
        return tmp-calculateCost();
    }

    public String getDateBuy() {
        return DATE_FORMAT.format(dateBuy.getTime());
    }

    /**
     * Getting the number of purchased items.
     */
    public long getCount() {
        return count;
    }

    public GoodsPiece getItem() {
        return goodsPiece;
    }

    public int getDayBuy() {
        return this.dateBuy.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Getting ordinal number of the class in the overall hierarchy for sorting.
     */
    public abstract int getOrder();

    /**
     * Getting a percentage discount for object class 
     * {@link by.training.beans.BuyDiscountPercent}.<br>
     * For other child classes don't need and returns 0.
     */
    public abstract float getPercent();

    /**
     * Getting a size discounts for delivery for class
     * {@link by.training.beans.BuyDiscountTransport}.<br>
     * For other child classes don't need and returns 0.
     */
    public abstract double getCostShipping();

    /**
     * Getting price bonus goods of class
     * {@link by.training.beans.BuyDiscountBonus}.<br>
     * For other child classes don't need and returns 0.
     */
    public abstract double getCostBonus();

    /**
     * Purchase price with discounts.
     */
    abstract double calculateCost();

}
