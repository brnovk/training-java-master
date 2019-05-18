import by.training.PurchaseComparatorBuilder;
import by.training.PurchaseList;
import by.training.beans.Purchase;

/**
 * Class with a method main(), which implements the business logic of the 
 * application.
 * @author BaranauViktar
 */
public class Runner {

	/**
	 * Completed subtasks:
	 * 1.Create an object of the developed class, loading collections elements 
	 * 	  	from the main file;
	 * 2.Print the collection;
	 * 3.Create another object of developed class, loading collections elements 
	 *    	from the additional file;
	 * 4.Insert the last element of the second collection at the position 0 of 
	 *    	the first collection;
	 * 5.Insert the initial element of the second collection at the position 1000 
	 *    	of the first collection;
	 * 6.Insert the element with index 2 of the second collection at the position 
	 * 		2 of the first collection;
	 * 7.Try to delete elements with indices 3, 10 and 5;
	 * 8.Print the first collection;
	 * 9.Sort the first collection;
	 * 10.Print the first collection;
	 * 11.Find the element with index 1 and the element with index 3 of the second 
	 *      collection in the first collection and print obtained results.
	 *      
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		
		// Check the number of command-line arguments
        final int ARGUMENTS_NUMBER = 3;
        if (args.length != ARGUMENTS_NUMBER) {
                System.err.println("Requires " + ARGUMENTS_NUMBER + 
                  " command-line argument for the correct operation of the program.");
                System.err.println("The program will be completed...");
                System.exit(0);
        }
        
        // Specifying index values of arguments
        final int ARGUMENT_INDEX_MAIN_FILE = 0;
        final int ARGUMENT_INDEX_ADDITIONAL_FILE = 1;
        final int ARGUMENT_INDEX_COMPARATOR_TYPE = 2;
        
        // Retrieve the file names and version comparator
        final String MAIN_FILE = args[ARGUMENT_INDEX_MAIN_FILE];
        final String ADDITIONAL_FILE = args[ARGUMENT_INDEX_ADDITIONAL_FILE];
		final String COMPARATOR_TYPE = args[ARGUMENT_INDEX_COMPARATOR_TYPE];
		
		// Loads comparator
		PurchaseComparatorBuilder.buildPurchaseComparator(COMPARATOR_TYPE);
        
        // Subtask 1
        PurchaseList firstList = new PurchaseList(MAIN_FILE);
        
        // Subtask 2
        firstList.show();
        
        // Subtask 3
        PurchaseList secondList = new PurchaseList(ADDITIONAL_FILE);
        
        // Subtask 4
        int indexLastElementSecondList = secondList.size()-1;
        firstList.add(0, secondList.get(indexLastElementSecondList));
        
        // Subtask 5
        firstList.add(1000, secondList.get(0));
        
        // Subtask 6
        final int VALUE_INDEX_FOR_SHARING = 2;
        firstList.add(VALUE_INDEX_FOR_SHARING, 
        		secondList.get(VALUE_INDEX_FOR_SHARING));
        
        // Subtask 7
        final int[] DELETE_INDEXES = {3, 10, -5};
        for (int index : DELETE_INDEXES) {
        	firstList.remove(index);
        }
        
        // Subtask 8
        firstList.show();
        
        // Subtask 9
        firstList.sort();
        
        // Subtask 10
        firstList.show();
        
        // Subtask 11
        final int[] SEARCH_INDEXES = {1,3};
        for (int currentIndex : SEARCH_INDEXES) {
        	searchPurchase(firstList, secondList, currentIndex);
        }
	}
	
	/**
	 * Helper method to perform subtasks 11.
	 */
    private static void searchPurchase (PurchaseList firstList, 
    									PurchaseList secondList, int index) {
    	StringBuilder output = new StringBuilder("\nElement\n");
    	Purchase searchObject = secondList.get(index);
    	if (searchObject == null) {
    		System.out.println("There is no " + index +" index element"
    							+ " in the second list");
    		return;
    	}
    	output.append(searchObject.toString());
    	int resultIndex = firstList.indexOf(searchObject);
    	output.append( firstList.get(resultIndex)==null ? 
    								"\nis not found\n" : 
    								"\nis found at position " + resultIndex + "\n");
    	System.out.println(output);
    }
}
