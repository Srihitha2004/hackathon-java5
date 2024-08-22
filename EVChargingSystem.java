import java.util.Scanner;
import java.util.ArrayList;

class chargestation {
    String stationId;
    String stationLocation;
    int slotsAvailable;

    chargestation(String stationId, String stationLocation, int slotsAvailable) {
        this.stationId = stationId;
        this.stationLocation = stationLocation;
        this.slotsAvailable = slotsAvailable;
    }

    void reserveSlot() {
        if (slotsAvailable > 0) {
            slotsAvailable--;
            System.out.println("Slot successfully reserved at " + stationLocation);
        } else {
            System.out.println("No slots available at this station.");
        }
    }

    @Override
    public String toString() {
        return "Station ID: " + stationId + ", Location: " + stationLocation + ", Slots Available: " + slotsAvailable;
    }
}

public class EVChargingSystem {
    public static void main(String[] args) {
        ArrayList<chargestation> stationsArray = new ArrayList<>();
        Scanner userInput = new Scanner(System.in);

        System.out.println("Enter the number of charging stations you want to add:");
        int numberOfStations = userInput.nextInt();
        userInput.nextLine(); // Consume newline

        // Loop to allow manual entry of charging station details
        for (int i = 0; i < numberOfStations; i++) {
            System.out.println("Enter details for Charging Station " + (i + 1) + ":");
            System.out.print("Station ID: ");
            String stationId = userInput.nextLine();
            System.out.print("Location: ");
            String stationLocation = userInput.nextLine();
            System.out.print("Available Slots: ");
            int slotsAvailable = userInput.nextInt();
            userInput.nextLine(); // Consume newline

            stationsArray.add(new chargestation(stationId, stationLocation, slotsAvailable));
        }

        while (true) {
            System.out.println("1. Search Charging Stations");
            System.out.println("2. Reserve a Charging Slot");
            System.out.println("3. Exit");
            System.out.print("Please select an option: ");
            int userChoice = userInput.nextInt();
            userInput.nextLine(); // Consume newline

            if (userChoice == 1) {
                System.out.print("Enter the location to search: ");
                String searchLocation = userInput.nextLine();

                boolean stationFound = false;
                for (chargestation station : stationsArray) {
                    if (station.stationLocation.equalsIgnoreCase(searchLocation)) {
                        System.out.println(station);
                        stationFound = true;
                    }
                }
                if (!stationFound) {
                    System.out.println("No charging stations found in " + searchLocation);
                    System.out.println("Here are all available charging stations:");
                    for (chargestation station : stationsArray) {
                        System.out.println(station);
                    }
                }
            } else if (userChoice == 2) {
                System.out.print("Enter the Station ID to reserve a slot: ");
                String stationIdInput = userInput.nextLine();

                boolean stationFound = false;
                for (chargestation station : stationsArray) {
                    if (station.stationId.equalsIgnoreCase(stationIdInput)) {
                        station.reserveSlot();
                        stationFound = true;
                        break;
                    }
                }
                if (!stationFound) {
                    System.out.println("No station found with ID: " + stationIdInput);
                }
            } else if (userChoice == 3) {
                System.out.println("Thank you for using the EV Charging Slot Reservation System!");
                break;
            } else {
                System.out.println("Invalid selection. Please try again.");
            }
        }
    }
}
