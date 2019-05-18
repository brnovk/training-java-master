package by.training.beans.tare;

import by.training.beans.tare.cargo.Cargo;
import by.training.ifaces.AbstractTare;

/**
 * Describes the platform for the transportation of any cargo.
 * @author Baranau Viktar
 */
public class PlatformTare extends AbstractTare {

    /**
     * Constructor of general purpose.
     */
    public PlatformTare(Cargo cargo) {
        super(0, 0, cargo);
    }

    /**
     * Calculating the weight platform with a cargo.
     * @return The weight platform with a cargo.
     */
    @Override
    public long computeWeight() {
        return (cargo==null) ? 0 : cargo.getWeight();
    }

    @Override
    public String toString() {
        return "Platform: " + super.toString() + computeWeight();
    }
}
