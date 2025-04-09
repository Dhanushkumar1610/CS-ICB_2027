import java.util.ArrayList;
import java.util.Scanner;

public class TodoListApp {

    // List to store tasks
    private static ArrayList<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Main loop for the application
        while (true) {
            System.out.println("\nTo-Do List App");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Remove Task");
            System.out.println("4. Exit");

            // Get user choice
            int choice = sc.nextInt();
            sc.nextLine();  // Consume the newline character after nextInt()

            switch (choice) {
                case 1:  // Add Task
                    System.out.println("Enter task to add:");
                    String task = sc.nextLine();
                    tasks.add(task);
                    System.out.println("Task added.");
                    break;
                case 2:  // View Tasks
                    System.out.println("Your tasks:");
                    if (tasks.isEmpty()) {
                        System.out.println("No tasks in the list.");
                    } else {
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i));
                        }
                    }
                    break;
                case 3:  // Remove Task
                    System.out.println("Enter task number to remove:");
                    int taskNumber = sc.nextInt();
                    if (taskNumber > 0 && taskNumber <= tasks.size()) {
                        tasks.remove(taskNumber - 1);  // Remove the task
                        System.out.println("Task removed.");
                    } else {
                        System.out.println("Invalid task number.");
                    }
                    break;
                case 4:  // Exit
                    System.out.println("Exiting...");
                    return;  // Exit the application
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
