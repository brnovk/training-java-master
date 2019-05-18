package by.training;

import java.util.Comparator;

import by.training.beans.Purchase;
import by.training.comparators.*;

/**
 * The service class is designed to create a static Comparator for 
 * collection PurchaseList.
 * Realized by Singleton pattern.
 * @see by.training.PurchaseList
 * @author BaranauViktar
 */
public class PurchaseComparatorBuilder {
	
	private static final String PACKAGE = "by.training.comparators.";
	private static Comparator<Purchase> purchaseComparator;
	
	private PurchaseComparatorBuilder() {
	}

	public static Comparator<Purchase> getPurchaseComparator() {
        if (!isExists()) {
        	purchaseComparator = new PurchaseComparatorV1();
        }
		return purchaseComparator;
	}

	private static boolean isExists() {
		return purchaseComparator != null;
	}
	
	/**
	 * The method initializes the static field of the comparator. If the 
	 * specified comparator load does not work, then the field is initialized 
	 * to the default comparator "PurchaseComparatorV1".
	 * @param comparatorName name of the comparator.
	 */
	@SuppressWarnings("unchecked")
	public static void buildPurchaseComparator(String comparatorName) {
		if(isExists()) {
			return;
		}
		try {
			Object obj = Class.forName(PACKAGE + comparatorName).newInstance();
			purchaseComparator = (Comparator<Purchase>) obj;
		} catch (ReflectiveOperationException ex) {
			System.err.println("Error loading \"" + ex.getMessage() + "\"");
			System.err.println("Uploaded by default comparator. \n");
			purchaseComparator = new PurchaseComparatorV1();
		}
	}
}
