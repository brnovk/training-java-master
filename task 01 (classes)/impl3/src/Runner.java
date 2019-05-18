
import java.util.Arrays;

import by.training.beans.DaysWeek;
import by.training.beans.Purchase;

public class Runner {
    
    private static void showList(Purchase[] sourceList) {
        
        String commodity = Purchase.NAME;
        long price = Purchase.PRICE;
        System.out.print(commodity + ";" + price + ";"); //constants one time at first
        
        for (Purchase purchase : sourceList) {
            System.out.println(purchase);
        }
        System.out.println();
    }

    public static void main(String[] args) {

        // Subtask 1
        Purchase[] purchases = new Purchase[] {
    		new Purchase( 5, DaysWeek.FRIDAY),
    		new Purchase(10, DaysWeek.MONDAY),
    		new Purchase( 8, DaysWeek.TUESDAY),
    		new Purchase( 2, DaysWeek.MONDAY),
    		new Purchase(16, DaysWeek.SATURDAY),
    		new Purchase( 1, DaysWeek.WEDNESDAY),
    		new Purchase(23, DaysWeek.SUNDAY),
    		new Purchase(11, DaysWeek.THURSDAY),
    		new Purchase( 9, DaysWeek.SUNDAY)
        };

        // Subtask 2
        showList(purchases);

        // Subtask 3
        int length = purchases.length;
        long averagePrice = 0;            // Mean price
        long amountPurchasesMonday = 0;   // Total cost in Monday's
        long valueMax = Long.MIN_VALUE;   
        DaysWeek purchaseMax = null;      // Day of maximum purchase

        for (Purchase purchase : purchases) {
            long currentCost = purchase.getCost(); // Price in the current iteration
            averagePrice += currentCost;
            if (purchase.getDay() == DaysWeek.MONDAY) {
                amountPurchasesMonday += currentCost;
            }
            if (currentCost > valueMax) {         
                valueMax = currentCost;
                purchaseMax = purchase.getDay();
            }
        }

        System.out.println("Average price = " + averagePrice/length);
        System.out.println("Amount of purchases on Monday's = " + amountPurchasesMonday);
        System.out.println("The day with the maximum cost purchase = " + purchaseMax);

        // Subtask 4
        Arrays.sort(purchases);

        // Subtask 5
        System.out.println();
        showList(purchases);
    }
}
