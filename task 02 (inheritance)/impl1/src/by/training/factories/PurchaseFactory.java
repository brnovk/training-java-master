package by.training.factories;

import java.util.Scanner;

import by.training.beans.PercentDiscountPurchase;
import by.training.beans.PriceDiscountPurchase;
import by.training.beans.Purchase;

/**
 * @author Baranau Viktar
 */
public class PurchaseFactory {

    private static enum PurchaseType {
        GENERAL_PURCHASE, 
        PRICE_DISCOUNT_PURCHASE,
        PERCENT_DISCOUNT_PURCHASE
    }

    public static Purchase getClassFromFactory(Scanner scanner) {
        String className = scanner.next();
        
        String productName = scanner.next();
        long price = scanner.nextLong();
        int count = scanner.nextInt();
        
        PurchaseType type = PurchaseType.valueOf(className);
        switch (type) {
            
            case GENERAL_PURCHASE:
                return new Purchase(productName, 
                                    price, 
                                    count);
            case PRICE_DISCOUNT_PURCHASE:
                return new PriceDiscountPurchase(productName, 
                                                 price, 
                                                 count, 
                                                 scanner.nextLong());
            case PERCENT_DISCOUNT_PURCHASE:
                return new PercentDiscountPurchase(productName, 
                                                   price, 
                                                   count, 
                                                   scanner.nextDouble());
            default:
                throw new IllegalArgumentException();
        }
    }
}
