import java.util.ArrayList;
import java.util.Scanner;

class Workout {
    String exerciseType;
    int duration; // in minutes
    int caloriesBurned;

    // Constructor for workout
    public Workout(String exerciseType, int duration, int caloriesBurned) {
        this.exerciseType = exerciseType;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
    }

    @Override
    public String toString() {
        return "Exercise: " + exerciseType + ", Duration: " + duration + " mins, Calories Burned: " + caloriesBurned;
    }
}

public class FitnessTracker {

    private static ArrayList<Workout> workoutHistory = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Fitness Tracker ---");
            System.out.println("1. Add Workout");
            System.out.println("2. View Workout History");
            System.out.println("3. Calculate Total Calories Burned");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    addWorkout();
                    break;
                case 2:
                    viewWorkoutHistory();
                    break;
                case 3:
                    calculateTotalCaloriesBurned();
                    break;
                case 4:
                    System.out.println("Exiting the Fitness Tracker...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to add a workout
    private static void addWorkout() {
        System.out.print("Enter the exercise type (e.g., Running, Cycling): ");
        String exerciseType = scanner.nextLine();

        System.out.print("Enter the duration of the exercise (in minutes): ");
        int duration = scanner.nextInt();

        System.out.print("Enter the calories burned during this exercise: ");
        int caloriesBurned = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        // Create a new workout object and add it to the history
        Workout workout = new Workout(exerciseType, duration, caloriesBurned);
        workoutHistory.add(workout);

        System.out.println("Workout added successfully!");
    }

    // Method to view all workouts in history
    private static void viewWorkoutHistory() {
        if (workoutHistory.isEmpty()) {
            System.out.println("No workouts recorded yet.");
        } else {
            System.out.println("\n--- Workout History ---");
            for (Workout workout : workoutHistory) {
                System.out.println(workout);
            }
        }
    }

    // Method to calculate the total calories burned
    private static void calculateTotalCaloriesBurned() {
        int totalCalories = 0;

        // Sum up the calories burned from all workouts
        for (Workout workout : workoutHistory) {
            totalCalories += workout.caloriesBurned;
        }

        System.out.println("\nTotal Calories Burned: " + totalCalories);
    }
}
