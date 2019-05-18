import by.training.beans.Material;
import by.training.beans.Subject;

public class Runner {

    public static void main(String[] args) {
        
        // const objects corresponding to the specific materials 
        // (steel and copper).
        final Material steel = new Material("Steel", 7850.0);
        final Material copper = new Material("Copper",8500.0);
        
        // Subtask 1
        Subject subject = new Subject("Wire", 0.03, steel);
        
        // Subtask 2
        System.out.println(subject);
        
        // Subtask 3
        subject.setMaterial(copper);
        System.out.println("The " + subject.getName() +
                           " mass is " + subject.getMass());
    }
}
