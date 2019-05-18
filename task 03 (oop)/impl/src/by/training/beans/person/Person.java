package by.training.beans.person;

import by.training.ifaces.ITransportable;

/**
 * A class describes the passenger.
 * @author Baranau Viktar
 */
public class Person implements ITransportable {

    private final long weight;
    private final String name;

    public Person() {
        weight = 100;    // weight of a person by default (100 kg).
        name = null;
    }

    public Person(long weight, String name) {
        this.weight = weight;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * The method returns a person's weight.
     */
    @Override
    public long computeWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Passenger: " + name + ';' + weight + ';';
    }
}
