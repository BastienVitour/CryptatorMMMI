import java.io.File;
import java.util.Scanner;

public class TerminalMenu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner object to read user input
        int choice = 0; // Variable to store user's menu choice

        // Infinite loop to keep the menu running until the user exits
        while (true) {
            // Display main menu
            System.out.println("\n=== Main Menu ===");
            System.out.println("1 - Add a password"); // Option to add a password
            System.out.println("2 - Display a password"); // Option to display password
            System.out.println("3 - Hash a password"); // Option to hash a password
            System.out.println("4 - Verify a hash");
            System.out.println("5 - Help"); // Help page
            System.out.println("6 - Crypter");
            System.out.println("0 - Quit"); // Option to quit the main menu
            System.out.print("\nEnter your choice : ");

            // Try to read and parse the user's choice
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\nPlease enter a valid number.");
                continue;
            }

            // Execute actions based on the user's choice
            switch (choice) {
                case 1:
                    AddPassword(scanner);
                    break;
                case 2:
                    RetrievePassword(scanner);
                    break;
                case 3:
                    HashPassword(scanner);
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 0:
                    System.out.println("\nBye !");
                    scanner.close();
                    return;
                default:
                    System.out.println("\nInvalid option. Choose one between 0 and 4.");
            }
        }
    }

    // Method to handle adding a password
    private static void AddPassword(Scanner scanner) {
        System.out.print("\nFor which site ? : ");
        String usage = scanner.nextLine();
        System.out.println("\n=== Choose a crypt method ===");
        System.out.println("1 - Cesar");
        System.out.println("2 - Vigenere");
        System.out.println("3 - Polybe");
        System.out.println("4 - Enigma");
        System.out.println("5 - RC4");
        System.out.println("6 - Encryption chain");
        System.out.println("0 - Go to main menu");
        System.out.print("\nEnter the number of the method : ");

        int method = 0; // Variable to store the chosen encryption method
        try {
            method = Integer.parseInt(scanner.nextLine()); // Parse the chosen method as an integer
        } catch (NumberFormatException e) {
            System.out.println("\nPlease enter a valid number.");
            return;
        }

        File outputFile = new File("./Passwords/password.txt");
        System.out.println(outputFile.exists());
        if(!outputFile.exists()) {
            FileManager.CreateFile("./Passwords/password.txt");
        }

        String encryptedPassword;

        // Handle different encryption methods based on the user's choice
        switch(method) {
            case 1:
                // Encrypt the user's input with Cesar method
                String cesarInput = AskForPassword(scanner, "Cesar", usage);
                int rotation = getIntInput(scanner, "Enter the rotation value: ");
                if (!Cesar.IsValidString(cesarInput)) {
                    System.out.println("Please enter a valid password.");
                } else {
                    encryptedPassword = Cesar.Encrypt(cesarInput, rotation);
                    PasswordManager.RegisterPassword(usage, encryptedPassword, "Cesar", String.valueOf(rotation));
                    System.out.println("Encrypted password: " + Cesar.Encrypt(cesarInput, rotation));
                }
                break;
            case 2:
                // Encrypt the user's input with Vigenere method
                String vigenereInput = AskForPassword(scanner, "Vigenere", usage);
                String key = getInput(scanner, "Enter the key to encrypt with Vigenere method: ");
                encryptedPassword = Vigenere.VigenereEncrypt(vigenereInput, key);
                PasswordManager.RegisterPassword(usage, encryptedPassword, "Vigenere", key);
                System.out.println("Encrypted password: " + Vigenere.VigenereEncrypt(vigenereInput, key));
                break;
            case 3:
                // Encrypt the user's input with Polybius method
                String polybiusInput = AskForPassword(scanner, "Polybius", usage);
                if (!Polybius.IsValidString(polybiusInput)) {
                    System.out.println("Please enter a valid password (only lowercase letters).");
                } else {
                    encryptedPassword = Polybius.Encrypt(polybiusInput);
                    PasswordManager.RegisterPassword(usage, encryptedPassword, "Polybius", "");
                    System.out.println("Encrypted password: " + Polybius.Encrypt(polybiusInput));
                }
                break;
            case 4:
                // Encrypt the user's input with Enigma method
                String enigmaInput = AskForPassword(scanner, "Enigma", usage);
                encryptedPassword = Enigma.Encrypt(enigmaInput);
                PasswordManager.RegisterPassword(usage, encryptedPassword, "Enigma", "");
                System.out.println("Encrypted password: " + Enigma.Encrypt(enigmaInput));
                break;
            case 5:

            case 6:

        }

    }

    // Method to handle adding a password
    private static void HashPassword(Scanner scanner) {
        System.out.print("\nFor which site ? : ");
        String usage = scanner.nextLine();
        System.out.println("\n=== Choose a hash method ===");
        System.out.println("1 - MD5");
        System.out.println("2 - SHA-256");
        System.out.println("3 - HMAC (with a secret key)");
        System.out.println("0 - Go to main menu");
        System.out.print("\nEnter the number of the method : ");

        int method = 0; // Variable to store the chosen encryption method
        try {
            method = Integer.parseInt(scanner.nextLine()); // Parse the chosen method as an integer
        } catch (NumberFormatException e) {
            System.out.println("\nPlease enter a valid number.");
            return;
        }

        // Handle different hash methods based on the user's choice
        String password = getInput(scanner, "Enter the password to hash: ");
        switch (method) {
            case 1:
                String hashedMD5 = Hash.hashMD5(password); // Call the MD5 hashing method
                System.out.println("Hashed password (MD5): " + hashedMD5);
                break;
            case 2:
                String hashedSHA256 = Hash.hashSHA256(password); // Call the SHA-256 hashing method
                System.out.println("Hashed password (SHA-256): " + hashedSHA256);
                break;
            case 3:
                String secretKey = getInput(scanner, "Enter the secret key for HMAC: ");
                String hmacHash = HMAC.generateHash(password, "", "", secretKey); // Generate HMAC
                System.out.println("Generated HMAC (Hex): " + hmacHash);
                break;
            default:
                System.out.println("Invalid method. Please choose 1 or 2.");

        }
    }
  
    // Retrieve the password of the user based on the name of the website(or something else) where it is used
    private static void RetrievePassword(Scanner scanner) {
        System.out.print("\nWhich password do you want to see ? : ");
        String usage = scanner.nextLine();
        String password = PasswordManager.RetrievePassword(usage);
        if(!password.isEmpty()) {
            System.out.println("Your password for " + usage + " is " + password);
        }
        else {
            System.out.println("You don't have a password for " + usage);
        }
    }

    // Generic method to get a String input
    private static String getInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // Generic method to get an integer input
    private static int getIntInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine()); // Parse and return the integer input
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number."); // Handle invalid input
            }
        }
    }

    private static String AskForPassword(Scanner scanner, String method, String usage) {
        System.out.println("Do you prefer ?");
        System.out.println("1 - Typing your own password");
        int passwordMethod = getIntInput(scanner, "2 - Generate a random password\n");
        String passwordInput;
        if(passwordMethod == 1) {
            passwordInput = getInput(scanner, "Enter the password to encrypt with " + method + " encryption method : ");
        }
        else {
            passwordInput = LFSR.RandomLowercaseTextString(usage + method, 8);
            System.out.println("Your password is " + passwordInput);
        }
        return passwordInput;
    }

}
