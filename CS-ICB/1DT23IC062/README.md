# Password Generator in Java

This is a simple Java program that generates secure random passwords of 8 characters in length. It uses a combination of uppercase letters, lowercase letters, digits, and special characters to ensure strong password generation.

## Features

- Generates 8-character passwords
- Includes uppercase and lowercase letters, numbers, and special characters
- Uses `SecureRandom` for better security compared to `Random`

## How It Works

The program works as follows:

1. **Character Pools**:
   - Uppercase letters: `A-Z`
   - Lowercase letters: `a-z`
   - Digits: `0-9`
   - Special characters: `!@#$%^&*()-_=+[]{}|;:,.<>?`
   
   All of these characters are combined into a single string called `ALL_CHARS`.

2. **Password Generation**:
   - The program uses `SecureRandom`, which is a cryptographically strong random number generator.
   - It loops 8 times (since we want an 8-character password).
   - In each iteration, it randomly selects one character from `ALL_CHARS` and appends it to the password.

3. **Output**:
   - The generated password is printed to the console.

## How to Run

1. Clone the repository or copy the code into a file named `PasswordGenerator.java`.

2. Open a terminal and navigate to the folder containing the file.

3. Compile the program:
   ```bash
   javac PasswordGenerator.java
