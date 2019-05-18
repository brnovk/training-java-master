import by.training.beans.*;
import by.training.ifaces.AbstractPurchase;

public class Runner {

    private static void printArray(AbstractPurchase[] purchases) {
        for (AbstractPurchase purchase : purchases) {
            System.out.println(purchase);
        }
        System.out.println();
    }

    public static void main(String[] args) {

        // Subtask 1.
        Commodity commodity = new Commodity("Milk", 7_500L);

        // Subtask 2.
        AbstractPurchase[] purchases = new AbstractPurchase[] {
            new PriceDiscountPurchase(5, commodity, 200L),
            new PriceDiscountPurchase(30, commodity, 450L),
            new PercentDiscountPurchase(6, commodity, 0.8D),
            new PercentDiscountPurchase(12, commodity, 1.6D),
            new AddedShippingCostPurchase(7, commodity, 1_000L),
            new AddedShippingCostPurchase(3, commodity, 800L)
        };

        // Subtask 3.
        printArray(purchases);

        // Subtask 4.
        java.util.Arrays.sort(purchases);

        // Subtask 5.
        printArray(purchases);

        // Subtask 6.
        int lastItem = purchases.length-1;
        long minimumCost = purchases[lastItem].getCost();
        System.out.println("Minimum cost = " + minimumCost);
    }
}
