import by.training.beans.BusinessTrip;

public class Runner {

    public static void main(String[] args) {

        // Subtask 1:
        BusinessTrip[]businessTrips = new BusinessTrip[] {
    		new BusinessTrip ("Anton Kozodoev", 50_000, 4),
    		new BusinessTrip ("Vladimir Papanov", 85_000, 8),
    		null,
    		new BusinessTrip ("Ivan Ivanov", 40_000, 2),
    		new BusinessTrip ()
		};

        // Subtask 2:
        for (BusinessTrip businessTrip : businessTrips) {
            if (businessTrip != null) {
                businessTrip.show();
            } else {
                System.out.println("Object is missing..");
            }
            System.out.println();
        }

        // Subtask 3:
        int lastIndex = businessTrips.length-1;
        businessTrips[lastIndex].setTransportExpenses(100_000);

        // Subtask 4:
        int totalDuration = (businessTrips[0].getNumberDays() +
                             businessTrips[1].getNumberDays());
        System.out.println("Duration = " + totalDuration);

        // Subtask 5:
        System.out.println();
        for (BusinessTrip businessTrip : businessTrips) {
                System.out.println(businessTrip);
        }
    }
}
