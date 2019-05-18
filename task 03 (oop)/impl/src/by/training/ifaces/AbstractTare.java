package by.training.ifaces;

import by.training.beans.tare.cargo.Cargo;

/**
 * Abstract class packaging, which is the parent of Cistern, Container and Platform.
 * @author Baranau Viktar
 */
public abstract class AbstractTare implements ITransportable {

    protected final long weight;
    protected final long volume;
    protected Cargo cargo;

    public AbstractTare() {
        weight = 0;
        volume = 0;
    }

    public AbstractTare(long weight, long volume, Cargo cargo) {
        this.weight = weight;
        this.volume = volume;
        this.cargo = cargo;
    }

    // getters

    public long getWeight() {
        return weight;
    }

    public long getVolume() {
        return volume;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "" + weight + ';' + volume + ";" + cargo;
    }

    /**
     * The method of calculating the tare weight with a cargo 
     * (implemented in subclasses).
     */
    @Override
    public abstract long computeWeight();
}
