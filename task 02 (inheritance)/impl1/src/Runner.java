import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

import by.training.beans.Purchase;
import by.training.factories.PurchaseFactory;

public class Runner {

    public static void main(String[] args) throws FileNotFoundException {
        
        final String fileName = "in.txt";
        Scanner scanner = new Scanner(new InputStreamReader(
    		Runner.class.getResourceAsStream("/" + fileName)));

        // Subtask 1.
        Purchase[] purchases = new Purchase[6];

        // Reference to an object with the maximum cost
        // (Because it does not need to print the value and the purchase of).
        Purchase maxCostPurchase = null;    
        long maxCostValue = Long.MIN_VALUE;  // Maximum purchase cost.
        boolean isEqual = true;

        int count = purchases.length;
        for (int i = 0; i<count; i++) {

            // Subtask 2.
            purchases[i] = PurchaseFactory.getClassFromFactory(scanner);

            // Subtask 3.
            System.out.println(purchases[i]);

            // Subtask 4.
            long currentCost = purchases[i].getCost();
            if (currentCost > maxCostValue) {
                maxCostValue = currentCost;
                maxCostPurchase = purchases[i];
            }

            // Equality checking of the first purchase with everyone else (Subtask 5).
            if (isEqual) {
                isEqual = purchases[i].equals(purchases[0]);
            }
        }

        System.out.println("Purchase with maximum cost: " + maxCostPurchase);
        System.out.println("All purchases are equal to: " + isEqual);
    }
}
