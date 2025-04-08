import java.io.*;
import java.util.*;

class Account {
    int accountNumber;
    int pin;
    String name;
    double balance;

    public Account(int accountNumber, int pin, String name, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.name = name;
        this.balance = balance;
    }

    public String toFileString() {
        return accountNumber + "," + pin + "," + name + "," + balance;
    }
}

public class SimpleATM {
    static final String FILE_NAME = "accounts.txt";
    static List<Account> accounts = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        loadAccounts();

        System.out.print("Enter account number: ");
        int accNum = sc.nextInt();
        System.out.print("Enter PIN: ");
        int pin = sc.nextInt();

        Account user = authenticate(accNum, pin);
        if (user == null) {
            System.out.println("Invalid account number or PIN.");
            return;
        }

        System.out.println("\nWelcome, " + user.name + "!");

        int choice;
        do {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Current Balance: $" + user.balance);
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = sc.nextDouble();
                    if (depositAmount > 0) {
                        user.balance += depositAmount;
                        System.out.println("Deposit successful.");
                    } else {
                        System.out.println("Invalid amount.");
                    }
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = sc.nextDouble();
                    if (withdrawAmount > 0 && withdrawAmount <= user.balance) {
                        user.balance -= withdrawAmount;
                        System.out.println("Withdrawal successful.");
                    } else {
                        System.out.println("Insufficient balance or invalid amount.");
                    }
                    break;
                case 4:
                    System.out.println("Thank you for using our ATM.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (choice != 4);

        saveAccounts();
    }

    static Account authenticate(int accNum, int pin) {
        for (Account acc : accounts) {
            if (acc.accountNumber == accNum && acc.pin == pin) {
                return acc;
            }
        }
        return null;
    }

    static void loadAccounts() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int accNum = Integer.parseInt(data[0]);
                int pin = Integer.parseInt(data[1]);
                String name = data[2];
                double balance = Double.parseDouble(data[3]);
                accounts.add(new Account(accNum, pin, name, balance));
            }
        } catch (IOException e) {
            System.out.println("Could not load accounts. File may not exist.");
        }
    }

    static void saveAccounts() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Account acc : accounts) {
                pw.println(acc.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving accounts.");
        }
    }
}
