package by.training.beans;

import java.util.ArrayList;
import java.util.List;

import by.training.ifaces.ITransportable;

/**
 * Description of the class ferryboat that transports goods and people.
 * @author Baranau Viktar
 */
public class Ferryboat {
    
    /**
     * Lifting capacity ferryboat.
     */
    private final long capacity;
    
    /**
     * Total array of goods and people.
     */
    private List<ITransportable> entities = new ArrayList<>();
    
    public Ferryboat() {
        capacity = 50_000L; // default lifting capacity.
    }

    public Ferryboat(long capacity) {
        this.capacity = capacity;
    }

    // getters

    public long getCapacity() {
        return capacity;
    }

    /**
     * The method returns an array of all the transported entities.
     */
    public List<ITransportable> getEntities() {
        return entities;
    }

    /**
     * The method of obtaining transported entity at a specified index.
     */
    public ITransportable getEntity(int index) {
        if ((index >= 0) && (index < entities.size())) {
            return entities.get(index);
        }
        return null;
    }

    /**
     * The method displays the entire array of entities transported.
     */
    public void showEntities() {
        for (ITransportable entity : entities) {
            System.out.println(entity);
        }
        System.out.println();
    }

    /**
     * The method for adding entity transported to the end of the array.
     */
    public void addEntity(ITransportable transportedObjects) {
        this.entities.add(transportedObjects);
    }

    /**
     * Removal method of transported entity at a specified index.
     */
    public void removeEntity(int index) {
        // If there is an entity with a such an index...
        if ((index >= 0) && (index < entities.size())) {
            entities.remove(index);
        }
    }

    /**
     * Verifying the ferry transportation to a nominal weight.
     */
    public boolean isPermissibleCapacity() {
        long currentWeight = 0;
        for (ITransportable entity : entities) {
            currentWeight += entity.computeWeight();
        }
        return (currentWeight < capacity) ? true : false;
    }
}
