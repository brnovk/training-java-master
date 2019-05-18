package by.training.beans;

/**
 * Describes the employee's travel expenses.
 * @author Baranau Viktar
 */
public class BusinessTrip {

    /**
     * Transportation expenses in belarusian rubles.
     */
    private long transportExpenses;
    
    /**
     * Daily allowance rate in belarusian rubles (the constant class).
     */
    private final static long DAILY_RATE = 25_000L;
    
    /**
     * Employee`s account.
     */
    private String accountStaff;
    
    /**
     * Number of days.
     */
    private int numberDays;
    
    /**
     * General-purpose constructor.
     * 
     * @param accountStaff Employee`s account.
     * @param transportExpenses Transportation expenses in belarusian rubles.
     * @param numberDays Number of days.
     */
    public BusinessTrip(String accountStaff, long transportExpenses, int numberDays) {
        this.accountStaff = accountStaff;
        this.transportExpenses = transportExpenses;
        this.numberDays = numberDays;
    }

    /**
     * Default constructor.
     */
    public BusinessTrip() {
    }

    // getters
    
    public String getAccountStaff() {
        return accountStaff;
    }

    public long getTransportExpenses() {
        return transportExpenses;
    }

    public int getNumberDays() {
        return numberDays;
    }

    // setters
    
    public void setAccountStaff(String account) {
        this.accountStaff = account;
    }

    public void setTransportExpenses(long transportExpenses) {
        this.transportExpenses = transportExpenses;
    }

    public void setNumberDays(int numberDays) {
        this.numberDays = numberDays;
    }
    
    @Override
    public String toString() {
        return DAILY_RATE + ";"+
               accountStaff + ";"+
               transportExpenses + ";"+
               numberDays + ";"+
               getTotal();
    }
    
    /**
     * Calculating total business trip expenses.
     * @return total expenses.
     */
    private long getTotal() {
        return transportExpenses + DAILY_RATE * numberDays;
    }
    
    /**
     * Printing all fields to the console.
     */
    public void show() {
        System.out.println("Account employee = " + accountStaff);
        System.out.println("Daily allowance = " + DAILY_RATE);
        System.out.println("Transport expenses = " + transportExpenses);
        System.out.println("Number days = " + numberDays);
        System.out.println("total cost = " +getTotal());
    }
}
