import java.util.*;

class Train {
    int trainNumber;
    String trainName;
    String source;
    String destination;
    int availableSeats;

    Train(int trainNumber, String trainName, String source, String destination, int availableSeats) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.availableSeats = availableSeats;
    }

    void displayDetails() {
        System.out.println("Train Number: " + trainNumber + ", Name: " + trainName + ", From: " + source + " To: " + destination + ", Available Seats: " + availableSeats);
    }
}

class User {
    int userId;
    String name;
    String email;

    User(int userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }
}

class Booking {
    static int bookingCounter = 1;
    int bookingId;
    User user;
    Train train;
    String date;

    Booking(User user, Train train, String date) {
        this.bookingId = bookingCounter++;
        this.user = user;
        this.train = train;
        this.date = date;
    }

    void printTicket() {
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Passenger: " + user.name);
        System.out.println("Train: " + train.trainName + " (" + train.trainNumber + ")");
        System.out.println("Date: " + date);
    }
}

public class TicketBookingSystem {
    static Scanner sc = new Scanner(System.in);
    static List<Train> trains = new ArrayList<>();
    static List<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {
        // Sample trains
        trains.add(new Train(101, "Express 1", "CityA", "CityB", 5));
        trains.add(new Train(102, "Express 2", "CityB", "CityC", 3));

        System.out.println("Welcome to IRCTC Ticket Booking System\n");

        User user = new User(1, "Aparna", "aparna@example.com");
        boolean running = true;

        while (running) {
            System.out.println("1. View Trains\n2. Book Ticket\n3. View My Bookings\n4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    for (Train t : trains) {
                        t.displayDetails();
                    }
                    break;
                case 2:
                    bookTicket(user);
                    break;
                case 3:
                    viewBookings(user);
                    break;
                case 4:
                    running = false;
                    System.out.println("Thank you for using IRCTC Ticket Booking System!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
            System.out.println();
        }
    }

    static void bookTicket(User user) {
        System.out.print("Enter Train Number to book: ");
        int trainNo = sc.nextInt();
        sc.nextLine();
        Train selectedTrain = null;
        for (Train t : trains) {
            if (t.trainNumber == trainNo && t.availableSeats > 0) {
                selectedTrain = t;
                break;
            }
        }
        if (selectedTrain != null) {
            System.out.print("Enter travel date (DD-MM-YYYY): ");
            String date = sc.nextLine();
            selectedTrain.availableSeats--;
            Booking booking = new Booking(user, selectedTrain, date);
            bookings.add(booking);
            System.out.println("\nTicket booked successfully!\n");
            booking.printTicket();
        } else {
            System.out.println("Train not found or no seats available.");
        }
    }

    static void viewBookings(User user) {
        System.out.println("\nYour Bookings:");
        for (Booking b : bookings) {
            if (b.user.userId == user.userId) {
                b.printTicket();
                System.out.println();
            }
        }
    }
}
