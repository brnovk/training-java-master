package by.training.comparators;

import java.util.Comparator;
import by.training.ifaces.ITransportable;

/**
 * Class to sort by type of the array of entities transported by ferry.
 * @author Baranau Viktar
 */
public class SortByType<T> implements Comparator<ITransportable> {

    enum allTransportation {
        PERSON,
        CONTAINERTARE,
        PLATFORMTARE,
        CISTERNTARE
    }

    @Override
    public int compare(ITransportable obj1, ITransportable obj2) {
        allTransportation value1 = allTransportation.valueOf(
        		obj1.getClass().getSimpleName().toUpperCase());
        allTransportation value2 = allTransportation.valueOf(
        		obj2.getClass().getSimpleName().toUpperCase());
        return value1.compareTo(value2);
    }
}
