package by.training.comparators;

import java.util.Comparator;
import by.training.beans.*;

/**
 * A comparator for comparing the superclass Purchase and/or its subclasses. 
 * The first version of the Comparator - without using Reflection. 
 * Use the operation "instanceof".
 * @see by.training.purchases.Purchase
 * @see by.training.purchases.PriceDiscountPurchase
 * @see by.training.PurchaseList
 * @see by.training.comparators.PurchaseComparatorV2
 * @author BaranauViktar
 */
public class PurchaseComparatorV1 implements Comparator<Purchase> {

	@Override
	public int compare(Purchase o1, Purchase o2) {
        String name1 = o1.getName();
        String name2 = o2.getName();
        if (name1.equals(name2)) {
    		if ((isSubClass(o1))&&(!isSubClass(o2))) {
    			return 1;
    		} else if ((!isSubClass(o1))&&(isSubClass(o2))) {
    			return -1;
    		} else {
    			return o1.getCost() - o2.getCost();
    		}
        }
        return name1.compareTo(name2);
	}
	
	/**
	 * Helper method for determining the membership of the 
	 * object subclass "PriceDiscountPurchase"
	 */
	private boolean isSubClass(Purchase obj) {
		return obj instanceof PriceDiscountPurchase;
	}
}
