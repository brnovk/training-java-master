package by.training;

import by.training.beans.*;
import by.training.exceptions.*;

/**
 * Pattern to create objects of classes Purchase or PriceDiscountPurchase.
 * getClassFromFactory method accepts a string parameter and returns an 
 * object of the appropriate class. If the input data is incorrect, 
 * an exception is thrown IncorrectLineFileException, which processes the 
 * calling code.
 * @see by.training.purchases.Purchase
 * @see by.training.purchases.PriceDiscountPurchase
 * @see by.training.IncorrectLineFileException
 * @author BaranauViktar
 */
public class PurchaseFactory {

	private static final int NUMBER_ARGUMENTS_PURCHASE_NO_DISCOUNT = 3;
	private static final int NUMBER_ARGUMENTS_PURCHASE_PRICE_DISCOUNT = 4;

	private static final int ORDINAL_ARGUMENT_NAME = 0;
	private static final int ORDINAL_ARGUMENT_PRICE = 1;
	private static final int ORDINAL_ARGUMENT_NUMBER_UNITS = 2;
	private static final int ORDINAL_ARGUMENT_AMOUNT_DISCOUNT = 3;

	private static final String DELIMETER = ";";

	/**
	 * Create object of classes Purchase or PriceDiscountPurchase.
	 * @param sourceLine A string set of parameters for creating the 
	 * class object. Parameters are separated by the ";".
	 * @return object of classes Purchase or PriceDiscountPurchase.
	 */
	public static Purchase getClassFromFactory(String sourceLine) {
		String[] dataset = sourceLine.split(DELIMETER);
		try {
			String name = dataset[ORDINAL_ARGUMENT_NAME];
			
			int price = Integer.parseInt(dataset[ORDINAL_ARGUMENT_PRICE]);
			int numberUnits = Integer.parseInt(dataset[ORDINAL_ARGUMENT_NUMBER_UNITS]);
			switch (dataset.length) {

				case NUMBER_ARGUMENTS_PURCHASE_NO_DISCOUNT:
					return new Purchase(name, price, numberUnits);

				case NUMBER_ARGUMENTS_PURCHASE_PRICE_DISCOUNT:
					int amountDiscount = Integer.parseInt(dataset[ORDINAL_ARGUMENT_AMOUNT_DISCOUNT]);
					if (price*numberUnits < amountDiscount) {
						throw new CsvLineException(sourceLine, "Incorrect value discounts");
					}
					return new PriceDiscountPurchase(name, price, numberUnits, amountDiscount);

				default:
					throw new CsvLineException(sourceLine, "Incorrect number of arguments");
			}
		} catch (NonpositiveArgumentException ex) {
			throw new CsvLineException(sourceLine, ex.getMessage());
		} catch (NumberFormatException www) {
			throw new CsvLineException(sourceLine, "One or more of the arguments are of the wrong type");
		} catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
			throw new CsvLineException(sourceLine, "One or more arguments are missing");		
		}
	}
}
