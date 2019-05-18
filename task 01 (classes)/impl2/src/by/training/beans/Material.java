package by.training.beans;

/**
 * Description of a uniform material.
 * @author Baranau Viktar
 */
public class Material {

    private String name;
    private final double density;

    public Material() {
        density = 0;
    }

    public Material(String name, double density) {
        this.name = name;
        this.density = density;
    }

    // getters
    
    public String getName() {
        return name;
    }

    public double getDensity() {
        return density;
    }
    
    @Override
    public String toString() {
        return name + ";" + density;
    }
}
