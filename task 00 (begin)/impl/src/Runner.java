import java.util.*;
import by.training.beans.*;

public class Runner {

    /**
     * An array of headers.
     * Used for output in the methods showAllCart and showAllCartByTypes.
     */
    static final String[] HEADER = {"  Count","  Date"," Percent","Disc.transp",
                                     "Tran. bonus","Total disc.","Total cost"};

    /**
     * A format string to output the attributes of the purchases and titles 
     * in the methods showAllCart and showAllCartByTypes.
     */
    static final String COL_FORMAT = "%1$-8s|%2$-11s|%3$-9s|%4$-11s|%5$-11s|%6$-11s|%7$-12s|\n";


	public static void main(String[] args) {

		GoodsPiece item = new GoodsPiece(700_000D, 7);
		
		// Subtask 1 - initial shopping list
		List<Buy> cartBuy = initialPurchases(item);
		showAllCart(cartBuy, "The initial set of purchases");
		
		// Subtask 2 - sort list by date of purchase
		Collections.sort(cartBuy, Buy.SORT_BY_DATE);
		
		// Subtask 3 - show list on console in a tabular form
		showAllCart(cartBuy, "Sorted by ascending date of purchase");
		
		// Subtask 4 - checking purchase in tenth day of month
		System.out.print("\n\nPurchase in 10 day of month: ");
		System.out.println((checkBuyOfDay(cartBuy, 10) ? "yes." : "no."));
		
		// Subtask 5 - sort purchases by category and show list
		Collections.sort(cartBuy, Buy.SORT_BY_TYPE);
		showAllCartByTypes(cartBuy, "Sorted by type");
		
		System.out.println("\n\n Program will be completed...");
	}
	
    /**
     * Method initialize the list of purchases of the same product of different kind.
     */
    private static List<Buy> initialPurchases(GoodsPiece item) {
    	List<Buy> cartBuy = new ArrayList<>();
        cartBuy.add(new BuyNoDiscount        (20, item, 2013, 9, 13));
        cartBuy.add(new BuyDiscountTransport (8,  item, 2013, 9, 3, 6_500D));
        cartBuy.add(new BuyDiscountPercent   (6,  item, 2013, 9, 9, 0.5F));
        cartBuy.add(new BuyDiscountBonus     (19, item, 2013, 9, 18, 8_000D));
        cartBuy.add(new BuyDiscountBonus     (4,  item, 2013, 9, 28, 9_150D));
        cartBuy.add(new BuyDiscountBonus     (4,  item, 2013, 9, 30, 12_000D));
        cartBuy.add(new BuyNoDiscount        (12, item, 2013, 9, 2));
        cartBuy.add(new BuyDiscountTransport (40, item, 2013, 9, 11, 7_750D));
        cartBuy.add(new BuyNoDiscount        (11, item, 2013, 9, 22));
        cartBuy.add(new BuyDiscountTransport (12, item, 2013, 9, 19, 3_080D));
        cartBuy.add(new BuyDiscountPercent   (3,  item, 2013, 9, 1, 3F));
        cartBuy.add(new BuyDiscountPercent   (7,  item, 2013, 9, 25, 0.8F));
        return cartBuy;
    }

    /**
     * Prints to console in a tabular form shopping list
     * @param label Signature table.
     */
    private static void showAllCart(List<Buy> cartBuy, String label) {

        System.out.println("\n\n" + label + ":");
        for (int i=0; i<80; i++) {
            System.out.print('_');
        }
        System.out.println();
        
        System.out.format(String.format(COL_FORMAT, (Object[]) HEADER));
        for (int i=0; i<80; i++) {
            System.out.print('-');
        }
        System.out.println();
        
        for (Buy buy : cartBuy) {
            System.out.format(COL_FORMAT, "  " + buy.getCount(),
        		" "  + buy.getDateBuy(),  "  " + buy.getPercent(), 
        		"  " + Math.round(buy.getCostShipping()), 
        		"  " + Math.round(buy.getCostBonus()),
        		"  " + Math.round(buy.getDiscount()),
        		"  " + Math.round(buy.getCost()));
        }
        for (int i=0; i<80; i++) {
            System.out.print('-');
        }
    }
    
    /**
     * Display a sorted list of purchases by category
     * @param label Signature table.
     */
    private static void showAllCartByTypes(List<Buy> cartBuy, String label) {

        String labelType[] = { "No Discount", "Discount Percent",
                               "Discount Transport", "Discount Bonus" };
        System.out.println("\n\n" + label + ":");
        for (int i=0; i<80; i++) {
            System.out.print('_');
        }
        System.out.println();
        
        System.out.format(String.format(COL_FORMAT, (Object[]) HEADER));
        for (int i=0; i<80; i++) {
            System.out.print('-');
        }
        System.out.println();
        int currentID = -1;
        int order_type = 0;
        for (int i=0; i<cartBuy.size(); i++) {
            if (currentID != cartBuy.get(i).getOrder()) {
                if (currentID != -1) {
                    for(int j=0; j<80; j++) {
                        System.out.print('-');
                    }
                    System.out.println();
                }
                System.out.println("\n" + labelType[order_type++] + ':');
                currentID = cartBuy.get(i).getOrder();
                for (int j=0; j<80; j++) {
                    System.out.print('-');
                }
                System.out.println();
            }
            System.out.format(COL_FORMAT, "  " + cartBuy.get(i).getCount(), 
        		" "  + cartBuy.get(i).getDateBuy(), "  " + cartBuy.get(i).getPercent(),
        		"  " + Math.round(cartBuy.get(i).getCostShipping()),
        		"  " + Math.round(cartBuy.get(i).getCostBonus()),
        		"  " + Math.round(cartBuy.get(i).getDiscount()),
        		"  " + Math.round(cartBuy.get(i).getCost()));

        }
        for (int i=0; i<80; i++) {
            System.out.print('-');
        }
        System.out.println();
    }

    /**
     * Checking existence of purchase of the specified day of the month.
     */
    private static boolean checkBuyOfDay(List<Buy> cartBuy, int chekedDay) {
        for (Buy buy : cartBuy) {
            if (buy.getDayBuy() == chekedDay) {
                return true;
            }
        }
        return false;
    }
}
