package by.training.beans;

/**
 * Description of the subject consists of a uniform material.
 * @author Baranau Viktar
 */
public class Subject {

    private String name;
    private double volume;
    private Material material;

    public Subject() {
    }

    public Subject(String name, double volume, Material material) {
        this.name = name;
        this.volume = volume;
        this.material = material;
    }

    // getters
    
    public String getName() {
        return name;
    }

    public double getVolume() {
        return volume;
    }

    public Material getMaterial() {
        return material;
    }

    // setters
    
    public void setName(String name) {
        this.name = name;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return name + ";" + material + ";" + volume + ";" + getMass();
    }

    /**
     * Calculating the subject mass.
     * @return subject mass.
     */
    public double getMass() {
        return this.material.getDensity()*volume;
    }
}
