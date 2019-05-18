package by.training.beans.tare.cargo;

/**
 * Class describing the cargo, which is designed for the transport 
 * of tara in the class.
 * @author Baranau Viktar
 */
public class Cargo {

    /**
     * Weight this cargo.
     */
    private final long weight;

    /**
     * Material density this cargo.
     */
    private final double density;

    /**
     *  Name of cargo.
     */
    private final String name;

    /**
     * Constructor cargo intended for container and cistern. (with density)
     * @param name  Name of cargo.
     * @param density Material density this cargo.
     */
    public Cargo(String name, double density) {
        weight = 0;
        this.density = density;
        this.name = name;
    }

    /**
     * Constructor cargo platform is designed. (with weights)
     * @param name Name of cargo.
     * @param weight Weight this cargo.
     */
    public Cargo(String name, long weight) {
        density = 0;
        this.weight = weight;
        this.name = name;
    }

    // getters

    public double getDensity() {
        return density;
    }

    public String getName() {
        return name;
    }

    public long getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return name + ';' + density + ';' + weight + ';';
    }
}
