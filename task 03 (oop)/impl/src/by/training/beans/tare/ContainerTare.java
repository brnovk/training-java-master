package by.training.beans.tare;

import by.training.beans.tare.cargo.Cargo;
import by.training.ifaces.AbstractTare;

/**
 * Describes transport container of homogeneous solid cargoes.
 * @author Baranau Viktar
 */
public class ContainerTare extends AbstractTare {

    /**
     *  Width the container.
     */
    private final int width;

    /**
     * Height the container.
     */
    private final int height;

    /**
     * Depth the container.
     */
    private final int depth;

    /**
     * Constructor of general purpose.
     * @param width Width the container.
     * @param height Height the container.
     * @param depth Depth the container.
     * @param weight Weight the container.
     * @param cargo Cargo from a uniform solid material.
     */
    public ContainerTare(int width, int height, int depth, long weight, Cargo cargo) {
        super(weight, (width * height * depth)/1000, cargo);
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    // getters

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDepth() {
        return depth;
    }

    /**
     * The calculation of weight of the container with a cargo.
     * @return Weight of the container with a cargo.
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
        return "Container: " + width + ';' + height + ';' + depth + ';' 
        		+ super.toString() + computeWeight();
    }
}
