package by.training;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;
import java.util.List;
import java.util.ArrayList;

import by.training.beans.*;
import by.training.exceptions.CsvLineException;

/**
 * Class for a collection of objects of two types: "Purchase" and
 * "PriceDiscountPurchase".
 * @see by.training.purchases.Purchase
 * @see by.training.purchases.PriceDiscountPurchase
 * @author BaranauViktar
 */
public class PurchaseList {

	/**
	 * Static comparator that defines the sort criterion collection items.
	 */
	private static final Comparator<Purchase> comparator = 
			PurchaseComparatorBuilder.getPurchaseComparator();
	
	/**
	 * Collection of purchases. May contain superclasses "Purchase" and/or
	 * subclasses "PriceDiscountPurchase".
	 */
	private List<Purchase> purchases = new ArrayList<>();

	/**
	 * Boolean attribute that is set to true after the sorting and to false
	 * after any operation that could lead to a breach of the state of order.
	 */
	private boolean flagOrdering;
	
	/**
	 * Default constructor for empty collection creation.
	 */
	public PurchaseList() {
	}

	/**
	 * Constructor with the parameter csv-filename, loading elements into the
	 * collection from a csvfile.
	 * 
	 * @param fileName csvfilename.
	 */
	public PurchaseList(String fileName) {
		
		final String DEFAULT_PATH = "src/";
		final String DEFAULT_EXTENSION = ".csv";
		final String FULL_PATH = DEFAULT_PATH + fileName + DEFAULT_EXTENSION;
		
		String currentLine;
		try (BufferedReader in = new BufferedReader(new FileReader(FULL_PATH))) {
			flagOrdering = false;
			while ((currentLine = in.readLine()) != null) {
				try {
					purchases.add(PurchaseFactory.getClassFromFactory(currentLine));
				} catch (CsvLineException e) {
					System.err.println(e.getMessage());
				}
			}
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		if (purchases==null) {
			throw new IllegalArgumentException();
		}
		flagOrdering = false;
		this.purchases = purchases;
	}
	
	/**
	 * Adds an object to the end of the collection.
	 * 
	 * @param purchase object class Purchase or inherited subclass.
	 * @return true - if the object is added, false - otherwise the.
	 */
	public boolean add(Purchase purchase) {
		if (purchase != null){
			flagOrdering = false;
			return purchases.add(purchase);
		}
		return false;
	}
	
	/**
	 * Adds an object to the specified index position. If the index 
	 * is invalid, the object is added to the end of the collection.
	 * 
	 * @param index position for insertion.
	 * @param purchase object class Purchase or inherited subclass.
	 */
	public void add(int index, Purchase purchase) {
		if (purchase != null) {
			flagOrdering = false;
			if (!isValidIndex(index)) {
				index = purchases.size();
			}
			purchases.add(index, purchase);
		}
	}
	
	/**
	 * Returns purchase specified index. If the index is invalid, 
	 * returns null.
	 * 
	 * @param index array index purchases.
	 * @return object of class Purchase - if index is valid. 
	 * Else - null.
	 */
	public Purchase get(int index) {
		if (isValidIndex(index)) {
			return purchases.get(index);
		}
		return null;
	}

	/**
	 * Calculates the sum of all purchases.
	 * @return sum of all purchases.
	 */
	public int getTotalCost() {
		int cost = 0;
		for (Purchase purchase : purchases) {
			if (purchase != null) {
				cost += purchase.getCost();
			}
		}
		return cost;
	}
	
	/**
	 * Delete an item at a specified index.
	 * @param index index of the item to remove.
	 */
	public void remove(int index) {
		if (isValidIndex(index)) {
			flagOrdering = false;
			purchases.remove(index);
		}
	}
	
	/**
	 * Returns the index of the first occurrence of the specified 
	 * element in this list, or a negative value if this list contains 
	 * no elements.
	 * 
	 * @param searchObject object of superclass Purchase or subclass. 
	 * @return index of the first occurrence of the specified element 
	 * in this list, or a negative value if this list contains 
	 * no elements.
	 */
	public int indexOf(Purchase searchObject) {
		if (searchObject==null) {
			return -1;
		}
		if (!flagOrdering) {
			this.sort();
		}
		return Collections.binarySearch(purchases, searchObject, comparator);
	}
	
	/**
	 * The method checks the index for correctness.
	 * @param index verifiable index.
	 * @return true - if the index is valid, false - otherwise.
	 */
	private boolean isValidIndex(int index) {
		return ((index >= 0) && (index < purchases.size()));
	}
	
	/**
	 * Sorting collections on specific criteria. 
	 * (Set static field Comparator).
	 * 
	 * @see by.training.comparators.PurchaseComparatorV1
	 * @see by.training.comparators.PurchaseComparatorV2
	 */
	public void sort() {
		Collections.sort(purchases, comparator);
		flagOrdering = true;
	}
	
	/**
	 * Returns the size of the collection.
	 * @return size of the collection.
	 */
	public int size() {
		return purchases.size();
	}
	
	/**
	 * Returns true if this list contains no elements.
	 * @return true if this list contains no elements, false - otherwise.
	 */
	public boolean isEmpty() {
		return purchases.size() == 0;
	}
	
	/**
	 * Print collection in table form and the total cost of all purchases.
	 * 
	 * @see <a href="http://www.java2s.com/Code/Java/Data-Type/Formatstringsintotable.htm">
	 * http://www.java2s.com/Code/Java/Data-Type/Formatstringsintotable.htm</a>
	 */
	public void show() {
		final String[] TITLE = {"Name","Price","Number","Cost","Discount"}; 
		final String NORMAL_FORMAT = "%15s%15s%15s%15s";
		final String EXTENDED_FORMAT = "%15s";
		try (Formatter formatter = new Formatter()) {
			formatter.format("\n" + NORMAL_FORMAT + EXTENDED_FORMAT + "\n\n", 
															(Object[]) TITLE);

			for (Purchase purchase : purchases) {
				formatter.format(NORMAL_FORMAT, purchase.getName(),
												purchase.getPrice(), 
												purchase.getNumberUnits(),
												purchase.getCost());
				if (purchase instanceof PriceDiscountPurchase) {
					formatter.format(EXTENDED_FORMAT, 
							((PriceDiscountPurchase) purchase).getAmountDiscount());
				}
				formatter.format("\n");
			}
			
			formatter.format("\n" + NORMAL_FORMAT, "Total cost:", 
										getTotalCost(), "", "\n\n");
			System.out.print(formatter);
			formatter.flush();
		}
	}
}
