package by.training.beans.tare;

import static java.lang.Math.PI;
import static java.lang.Math.pow;

import by.training.beans.tare.cargo.Cargo;
import by.training.ifaces.AbstractTare;

/**
 * Describes a cistern for transporting liquids.
 * @author Baranau Viktar
 */
public class CisternTare extends AbstractTare {

    /**
     * The radius of cistern.
     */
    private final int radius;

    /**
     * Length of cistern.
     */
    private final int height;

    /**
     * Constructor of general purpose.
     * @param radius The radius of cistern.
     * @param height Length of cistern.
     * @param weight Weight cistern.
     * @param cargo An object of class Cargo intended for transportation.
     */
    public CisternTare(int radius, int height, long weight, Cargo cargo) {
        super(weight, ((long)(PI * pow(radius/2, 2) * height) / 1000), cargo);
        this.radius = radius;
        this.height = height;
    }

    // getters

    public int getRadius() {
        return radius;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Calculating the weight cistern with a cargo.
     * @return The weight cistern with a cargo.
     */
    @Override
    public long computeWeight() {
        long resWeight = (cargo==null) 
        		? weight 
				: (long)(volume * cargo.getDensity()) + weight;
        return resWeight;
    }

    @Override
    public String toString() {
        return "Cistern: " + radius + ';' + height + ';' 
    		+ super.toString() + computeWeight();
    }
}
