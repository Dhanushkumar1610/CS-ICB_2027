import java.util.*;


class Book {
    String title;
    String author;
    int year;

    
    Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    void display() {
        System.out.println(" Title: " + title + " |  Author: " + author + " | Year: " + year);
    }
}

public class LibraryTracker {
    static ArrayList<Book> library = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n Library Book Tracker");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book by Title");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addBook();
                case 2 -> viewBooks();
                case 3 -> searchBook();
                case 4 -> System.out.println("Exiting... Goodbye! ");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 4);
    }

    static void addBook() {
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author: ");
        String author = sc.nextLine();
        System.out.print("Enter Year: ");
        int year = sc.nextInt();
        sc.nextLine();

        Book newBook = new Book(title, author, year);
        library.add(newBook);
        System.out.println(" Book added successfully!");
    }

    static void viewBooks() {
        if (library.isEmpty()) {
            System.out.println("No books in library.");
        } else {
            System.out.println("Book List:");
            for (Book b : library) {
                b.display();
            }
        }
    }

    static void searchBook() {
        System.out.print("Enter title to search: ");
        String searchTitle = sc.nextLine().toLowerCase();

        boolean found = false;
        for (Book b : library) {
            if (b.title.toLowerCase().contains(searchTitle)) {
                b.display();
                found = true;
            }
        }
        if (!found) {
            System.out.println(" No book found with that title.");
        }
    }
}
