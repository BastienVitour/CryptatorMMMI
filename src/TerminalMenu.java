import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class TerminalMenu {

    public static void main(String[] args) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, IOException {
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
            System.out.println("6 - Crypter un mot de passe");
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
                    DisplayHelp(scanner);
                    break;
                case 6:
                    System.out.println("\nGTFO");
                    scanner.close();
                    System.out.println("\u001B[31m");
                    System.out.println("Process finished with exit code user is a dumbass");
                    System.out.println("\u001B[0m");
                    return;
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
    private static void AddPassword(Scanner scanner) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, IOException {
        System.out.print("\nFor which site ? : ");
        String usage = scanner.nextLine();
        System.out.println("\n=== Choose a crypt method ===");
        System.out.println("1 - Cesar");
        System.out.println("2 - Vigenere");
        System.out.println("3 - Polybe");
        System.out.println("4 - Enigma");
        System.out.println("5 - RC4");
        System.out.println("6 - AES");
        System.out.println("7 - Encryption chain");
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
        if(!outputFile.exists()) {
            Files.createDirectory(Paths.get(System.getProperty("user.dir") + "/Passwords"));
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
                }
                break;
            case 2:
                // Encrypt the user's input with Vigenere method
                String vigenereInput = AskForPassword(scanner, "Vigenere", usage);
                String key = getInput(scanner, "Enter the key to encrypt with Vigenere method: ");
                encryptedPassword = Vigenere.VigenereEncrypt(vigenereInput, key);
                PasswordManager.RegisterPassword(usage, encryptedPassword, "Vigenere", key);
                break;
            case 3:
                // Encrypt the user's input with Polybius method
                String polybiusInput = AskForPassword(scanner, "Polybius", usage);
                if (!Polybius.IsValidString(polybiusInput)) {
                    System.out.println("Please enter a valid password (only lowercase letters).");
                } else {
                    encryptedPassword = Polybius.Encrypt(polybiusInput);
                    PasswordManager.RegisterPassword(usage, encryptedPassword, "Polybius", "");
                }
                break;
            case 4:
                // Encrypt the user's input with Enigma method
                String enigmaInput = AskForPassword(scanner, "Enigma", usage);
                encryptedPassword = Enigma.Encrypt(enigmaInput);
                PasswordManager.RegisterPassword(usage, encryptedPassword, "Enigma", "");
                break;
            case 5:
                // Encrypt the user's input with Rc4 method
                String rc4Input = AskForPassword(scanner, "RC4", usage);
                String keyRc4 = getInput(scanner, "Enter a key: ");
                encryptedPassword = Rc4.Encrypt(rc4Input,keyRc4);
                PasswordManager.RegisterPassword(usage, encryptedPassword, "Rc4", keyRc4);
                break;
            case 6:
                // Encrypt the user's input with AES method
                String aesInput = AskForPassword(scanner, "AES", usage);
                AESData data = AES.Encrypt(aesInput);
                PasswordManager.RegisterPassword(usage, data.getEncryptedText(), "AES", data.getSecretKey() + "-" + data.getIv());
                System.out.println("Encrypted password: " + AES.Encrypt(aesInput));
                break;

            case 7:
                EncriptionChain(scanner, usage);
                break;
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
        String hashPassword;
        switch (method) {
            case 1:
                hashPassword = Hash.hashMD5(password); // Call the MD5 hashing method
                PasswordManager.RegisterPassword(usage, hashPassword, "Hash MD5", password);
                System.out.println("Hashed password (MD5): " + hashPassword);
                break;
            case 2:
                hashPassword = Hash.hashSHA256(password); // Call the SHA-256 hashing method
                PasswordManager.RegisterPassword(usage, hashPassword, "Hash SHA-256", password);
                System.out.println("Hashed password (SHA-256): " + hashPassword);
                break;
            case 3:
                String salt = getInput(scanner, "Enter the salt for HMAC: ");
                String pepper= getInput(scanner, "Enter the pepper for HMAC: ");
                String secretKey = getInput(scanner, "Enter the secret key for HMAC: ");
                hashPassword = HMAC.generateHash(password, salt, pepper, secretKey); // Generate HMAC
                PasswordManager.RegisterPassword(usage, hashPassword, "HMAC", "");
                System.out.println("Generated HMAC (Hex): " + hashPassword);
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

    private static void DisplayHelp(Scanner scanner) {
        System.out.println("Choose an encryption method to learn about:");
        System.out.println("1. ROT(X) Encryption");
        System.out.println("2. Vigenère Encryption");
        System.out.println("3. Polybius Encryption");
        System.out.println("4. Enigma Encryption");
        System.out.println("5. RC4 Encryption");
        System.out.println("6. AES Encryption");

        int choice = getIntInput(scanner, "Enter your choice (1-6): ");
        Help.DisplayHelp(choice);

    }

    // Method to handle encryption with encryption chain
    public static void EncriptionChain(Scanner scanner, String usage) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        System.out.println("\n=== Choose a crypt method ===");
        System.out.println("1 - Cesar");
        System.out.println("2 - Vigenere");
        System.out.println("3 - Polybe");
        System.out.println("4 - Enigma");
        System.out.println("5 - RC4");
        System.out.println("6 - AES");
        System.out.println("0 - Finish");
        System.out.print("\nChoose a method: ");

        int[] method = new int[6]; // Variable to store the chosen encryption method
        for (int i = 0; i < method.length; i++) {
            method[i] = Integer.parseInt(scanner.nextLine()); // Parse the chosen method as an integer
            // Verify the method chosed and let the user choose another one or not
            if (method[i] == 0 || method[i] == 3 || method[i] == 5 || method[i] == 6 ) {
                break;
            }
            else {
                System.out.print("\nChoose another one: ");
            }
        }

        File outputFile = new File("./Passwords/password.txt");
        if(!outputFile.exists()) {
            FileManager.CreateFile("./Passwords/password.txt");
        }
        String encryptedPassword = "";
        StringBuilder key = new StringBuilder();
        StringBuilder methodString = new StringBuilder();

        // Handle different encryption methods based on the user's choice
        for (int i = 0; i < method.length; i++) {
            if (i == 0) {
                encryptedPassword = AskForPassword(scanner, "EncryptionChain", usage);
            }
            switch (method[i]) {
                case 1:
                    // Encrypt the user's input with Cesar method
                    int rotation = getIntInput(scanner, "Enter the rotation value: ");
                    if (!Cesar.IsValidString(encryptedPassword)) {
                        System.out.println("Please enter a valid password.");
                    } else {
                        encryptedPassword = Cesar.Encrypt(encryptedPassword, rotation);
                        key.append(rotation).append(" ");
                        methodString.append("Cesar").append(" ");
                    }
                    break;
                case 2:
                    // Encrypt the user's input with Vigenere method
                    String keyVigenere = getInput(scanner, "Enter the key to encrypt with Vigenere method: ");
                    encryptedPassword = Vigenere.VigenereEncrypt(encryptedPassword, keyVigenere);
                    key.append(keyVigenere).append(" ");
                    methodString.append("Vigenere").append(" ");
                    break;
                case 3:
                    // Encrypt the user's input with Polybius method
                    if (!Polybius.IsValidString(encryptedPassword)) {
                        System.out.println("Please enter a valid password (only lowercase letters).");
                    } else {
                        encryptedPassword = Polybius.Encrypt(encryptedPassword);
                        methodString.append("Polybius").append(" ");
                        key.append(".").append(" ");
                    }
                    break;
                case 4:
                    // Encrypt the user's input with Enigma method
                    encryptedPassword = Enigma.Encrypt(encryptedPassword);
                    methodString.append("Enigma").append(" ");
                    key.append(".").append(" ");
                    break;
                case 5:
                    // Encrypt the user's input with Rc4 method
                    String keyRc4 = getInput(scanner, "Enter a key: ");
                    encryptedPassword = Rc4.Encrypt(encryptedPassword,keyRc4);
                    key.append(keyRc4).append(" ");
                    methodString.append("Rc4").append(" ");
                    break;
                case 6:
                    // Encrypt the user's input with AES method
                    AESData data = AES.Encrypt(encryptedPassword);
                    encryptedPassword = data.getEncryptedText();
                    methodString.append("AES").append(" ");
                    key.append(data.getSecretKey()).append("-").append(data.getIv()).append(" ");
                    break;
            }
        }

        PasswordManager.RegisterPassword(usage, encryptedPassword, methodString.toString(), key.toString());
    }

}
