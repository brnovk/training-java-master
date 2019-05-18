import java.util.Collections;

import by.training.beans.tare.*;
import by.training.beans.Ferryboat;
import by.training.beans.person.Person;
import by.training.ifaces.ITransportable;
import by.training.beans.tare.cargo.Cargo;
import by.training.comparators.SortByType;

public class Runner {
    
    public static void main(String[] args) {

    	// (Subtask 1) Create the required objects.

        // create an object of ferry
        Ferryboat ferry = new Ferryboat(49_000L); 

        // add transported entities
        ferry.addEntity(new Person( 78, "John Smitt"));
        ferry.addEntity(new CisternTare(50, 80, 120, new Cargo("benzine", 0.76D)));
        ferry.addEntity(new CisternTare(50, 80, 120, new Cargo("sulfuric acid", 1.837D)));
        ferry.addEntity(new Person( 68, "Julia Takshina"));
        ferry.addEntity(new ContainerTare(100, 100, 80, 200, new Cargo("sand", 1.7D)));
        ferry.addEntity(new PlatformTare(new Cargo("bricks", 3_000L)));
        ferry.addEntity(new Person( 68, "Artem Dushkin"));

        // (Subtask 2) Display the contents of the total array.
        ferry.showEntities();

        // (Subtask 3) Sort the contents of the array.
        Collections.sort(ferry.getEntities(), new SortByType<ITransportable>());

        // (Subtask 4) Display the contents of the total array.
        ferry.showEntities();

        // (Subtask 5) Verifying the ferry transportation to a nominal weight.
        System.out.println("Verifying the ferry transportation to a nominal weight: " + 
                ferry.isPermissibleCapacity());
    }
}
