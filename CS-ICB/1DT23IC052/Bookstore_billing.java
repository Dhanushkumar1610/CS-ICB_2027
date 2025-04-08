import java.util.*;

class Book {
    String title;
    double price;

    Book(String title, double price) {
        this.title = title;
        this.price = price;
    }
}

class PurchaseItem {
    Book book;
    int quantity;

    PurchaseItem(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    double getTotal() {
        return book.price * quantity;
    }
}

public class BookstoreBillingSystem {
    static List<Book> bookCatalog = new ArrayList<>();
    static List<PurchaseItem> cart = new ArrayList<>();

    public static void main(String[] args) {
        initBookCatalog();

        Scanner scanner = new Scanner(System.in);
        System.out.println("==== Welcome to City Bookstore ====");
        displayBooks();

        while (true) {
            System.out.print("Enter book number to purchase (0 to checkout): ");
            int bookNo = scanner.nextInt();

            if (bookNo == 0) break;

            if (bookNo < 1 || bookNo > bookCatalog.size()) {
                System.out.println("Invalid book number. Try again.");
                continue;
            }

            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();

            Book selectedBook = bookCatalog.get(bookNo - 1);
            cart.add(new PurchaseItem(selectedBook, quantity));
            System.out.println(quantity + " x \"" + selectedBook.title + "\" added to cart.");
        }

        printReceipt();
        scanner.close();
    }

    static void initBookCatalog() {
        bookCatalog.add(new Book("The Alchemist", 299.00));
        bookCatalog.add(new Book("To Kill a Mockingbird", 349.00));
        bookCatalog.add(new Book("1984", 399.00));
        bookCatalog.add(new Book("Harry Potter", 499.00));
        bookCatalog.add(new Book("The Hobbit", 450.00));
        bookCatalog.add(new Book("Rich Dad Poor Dad", 250.00));
        bookCatalog.add(new Book("Wings of Fire", 300.00));
        bookCatalog.add(new Book("The Power of Habit", 280.00));
    }

    static void displayBooks() {
        System.out.println("\n----- BOOK CATALOG -----");
        for (int i = 0; i < bookCatalog.size(); i++) {
            Book book = bookCatalog.get(i);
            System.out.printf("%d. %-25s Rs. %.2f\n", i + 1, book.title, book.price);
        }
        System.out.println("-------------------------\n");
    }

    static void printReceipt() {
        double total = 0;
        System.out.println("\n========== RECEIPT ==========");
        System.out.printf("%-25s %-10s %-10s\n", "Book", "Qty", "Amount");

        for (PurchaseItem item : cart) {
            double itemTotal = item.getTotal();
            total += itemTotal;
            System.out.printf("%-25s %-10d Rs. %-8.2f\n",
                    item.book.title, item.quantity, itemTotal);
        }

        System.out.println("-----------------------------------");
        System.out.printf("Total Amount:            Rs. %.2f\n", total);
        System.out.println("===================================");
        System.out.println("Thanks for shopping with us!");
    }
}
