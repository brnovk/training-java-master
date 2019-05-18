package by.training.comparators;

import java.util.Comparator;
import by.training.beans.Purchase;

/**
 * A comparator for comparing the superclass Purchase and/or its 
 * subclasses.
 * The second version of the Comparator - using Reflection.
 * Using method of "getClass()".
 * @see by.training.purchases.Purchase
 * @see by.training.purchases.PriceDiscountPurchase
 * @see by.training.PurchaseList
 * @see by.training.comparators.PurchaseComparatorV1
 * @author BaranauViktar
 */
public class PurchaseComparatorV2 implements Comparator<Purchase> {

	@Override
	public int compare(Purchase o1, Purchase o2) {
        String name1 = o1.getName();
        String name2 = o2.getName();
        if (name1.equals(name2)) {
            if (o1.getClass() != o2.getClass()) {
                if (o1.getClass() == Purchase.class) {
                        return -1;
                }
                return 1;
            }
            return o1.getCost() - o2.getCost();
        }
        return name1.compareTo(name2);
	}
}
