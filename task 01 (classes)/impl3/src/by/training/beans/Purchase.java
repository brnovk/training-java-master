package by.training.beans;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Wholesale purchase of the same product in the same price in within a week.
 * Implements the Comparable interface is parameterized by the class.
 * @author Baranau Viktar
 */
public class Purchase implements Comparable<Purchase> {
    
    /**
     * Constant filename.
     */
    private static final String fileName = "src.txt";
    public static final String NAME;
    public static final long PRICE;

    static {
        // Get the path to the file
        String path = Purchase.class.getResource(fileName).getPath();
        String tmpName; 
        Long tmpCost;
        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader(path));
            tmpName = in.readLine();                  // Read the first value: tmpName;
            tmpCost = Long.parseLong(in.readLine());  // Read and parse the second value: tmpCost;
            in.close();
        } catch (IOException | NumberFormatException exception) {
            tmpName = "Milk";
            tmpCost = 6_500L;
        }
        NAME = tmpName;
        PRICE = tmpCost;
    }

    /**
     * Number of purchased units.
     */
    private int numberUnits;
    private DaysWeek day;

    public Purchase() {
    }
    
    public Purchase(int numberUnits, DaysWeek day) {
        this.numberUnits = numberUnits;
        this.day = day;
    }

    // getters

    public int getNumberUnits() {
        return numberUnits;
    }

    public DaysWeek getDay() {
        return day;
    }

    // setters
    
    public void setNumberUnits(int numberUnits) {
        this.numberUnits = numberUnits;
    }

    public void setDay(DaysWeek day) {
        this.day = day;
    }

    /**
     * Compares this object with the specified object for order. Returns a 
     * negative integer, zero, or a positive integer as this object is less 
     * than, equal to, or greater than the specified object.
     * (without the use of "if" statement)
     */
    @Override
    public int compareTo(Purchase another) {
      return this.numberUnits - another.numberUnits;
    }

    /**
     * @return Each non constant field and the purchase cost, separated by 
     * the ";" symbol.
     */
    @Override
    public String toString() {
        return numberUnits + ";" + day + ";" + getCost();
    }

    /**
     * Calculating the purchase cost.
     * @return purchase cost
     */
    public long getCost() {
        return Purchase.PRICE * this.numberUnits;
    } 
}
