import java.util.Scanner;

public class Railwayreservation {
    class RailwayReservationSystem {
    static String[][] trains = {
        {"Train A", "100", "500"},
        {"Train B", "100", "500"},
        {"Train C", "100", "500"},
        {"Train D", "100", "500"},
        {"Train E", "100", "500"}
    };
    static String bookedTrain = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nRailway Reservation System - Same Route");
            System.out.println("1. Book a Train");
            System.out.println("2. View Booking");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    bookTrain(scanner);
                    break;
                case 2:
                    viewBooking();
                    break;
                case 3:
                    cancelBooking();
                    break;
                case 4:
                    System.out.println("Exiting. Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static void bookTrain(Scanner scanner) {
        if (bookedTrain != null) {
            System.out.println("You already have a booking in " + bookedTrain + ". Please cancel it first.");
            return;
        }

        System.out.println("Available Trains (All on the same route):");
        for (int i = 0; i < trains.length; i++) {
            System.out.println((i + 1) + ". " + trains[i][0] + " - Seats: " + trains[i][1] + " - Rate: ₹" + trains[i][2]);
        }
        System.out.print("Select a train (1-5): ");
        int trainIndex = scanner.nextInt() - 1;

        if (trainIndex >= 0 && trainIndex < trains.length) {
            int seats = Integer.parseInt(trains[trainIndex][1]);
            if (seats > 0) {
                trains[trainIndex][1] = String.valueOf(seats - 1);
                bookedTrain = trains[trainIndex][0];
                System.out.println("Booking confirmed for " + bookedTrain + " at ₹" + trains[trainIndex][2]);
            } else {
                System.out.println("No seats available in " + trains[trainIndex][0]);
                showAlternativeTrains();
            }
        } else {
            System.out.println("Invalid train selection.");
        }
    }

    static void showAlternativeTrains() {
        System.out.println("Alternative Trains with Available Seats:");
        boolean available = false;
        for (String[] train : trains) {
            int seats = Integer.parseInt(train[1]);
            if (seats > 0) {
                available = true;
                System.out.println(train[0] + " - Seats: " + seats + " - Rate: ₹" + train[2]);
            }
        }
        if (!available) {
            System.out.println("No alternative trains available.");
        }
    }

    static void viewBooking() {
        if (bookedTrain == null) {
            System.out.println("No booking found.");
        } else {
            System.out.println("You have a confirmed booking in " + bookedTrain);
        }
    }

    static void cancelBooking() {
        if (bookedTrain == null) {
            System.out.println("No booking to cancel.");
            return;
        }

        for (String[] train : trains) {
            if (train[0].equals(bookedTrain)) {
                int seats = Integer.parseInt(train[1]);
                train[1] = String.valueOf(seats + 1);
                break;
            }
        }
        System.out.println("Booking in " + bookedTrain + " has been cancelled.");
        bookedTrain = null;
    }
}
}
