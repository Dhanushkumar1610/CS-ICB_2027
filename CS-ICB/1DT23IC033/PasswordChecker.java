
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class PasswordChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a password: ");
        String password = scanner.nextLine();
        
        System.out.println("Password Strength: " + checkStrength(password));
        System.out.println("Hashed Password (SHA-256): " + hashPassword(password));
    }

    public static String checkStrength(String password) {
        int score = 0;
        
        if (password.length() >= 8) score++; // Length check
        if (password.matches(".*[A-Z].*")) score++; // Uppercase letter
        if (password.matches(".*[a-z].*")) score++; // Lowercase letter
        if (password.matches(".*\\d.*")) score++; // Digit
        if (password.matches(".*[@#$%^&+=].*")) score++; // Special character
        
        switch (score) {
            case 5: return "Excellent";
            case 4: return "Good";
            case 3: return "Average";
            default: return "Weak";
        }
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return "Error: Unable to hash password";
        }
    }
}



How to Showcase It
- Explain the Concepts:- Highlight cybersecurity principles like password complexity and hashing algorithms.
- Mention the importance of avoiding weak passwords.

- Demo:- Run the program with sample passwords.
- Show the hashed output and discuss how hashing protects passwords.

- Enhancements:- Add a database to store hashed passwords securely.
- Implement real-time suggestions for creating stronger passwords.
- Explore cryptography further by adding salting to hashes.



What do you think, Veer? If you want to expand this or need help with specific parts, let me know! 😊
