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
            System.out.println("4 - Help"); // Help page
            System.out.println("5 - Crypter");
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
                    break;
                case 3:
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
        System.out.print("\nEnter the number of the method : ");

        int method = 0; // Variable to store the chosen encryption method
        try {
            method = Integer.parseInt(scanner.nextLine()); // Parse the chosen method as an integer
        } catch (NumberFormatException e) {
            System.out.println("\nPlease enter a valid number.");
            return;
        }

        // Handle different encryption methods based on the user's choice
        switch(method) {
            case 1:
                // Encrypt the user's input with Cesar method
                String cesarInput = getInput(scanner, "Enter the password to encrypt with Cesar encryption method : ");
                int rotation = getIntInput(scanner, "Enter the rotation value: ");
                if (!Cesar.IsValidString(cesarInput)) {
                    System.out.println("Please enter a valid password.");
                } else {
                    System.out.println("Encrypted password: " + Cesar.Encrypt(cesarInput, rotation));
                }
                break;
            case 2:
                // Encrypt the user's input with Vigenere method
                String plaintext = getInput(scanner, "Enter the password to encrypt with Vigenere encryption method: ");
                String key = getInput(scanner, "Enter the key to encrypt with Vigenere method: ");
                System.out.println("Encrypted password: " + Vigenere.VigenereEncrypt(plaintext, key));
                break;
            case 3:
                // Encrypt the user's input with Polybius method
                String polybiusInput = getInput(scanner, "Enter the password for Polybius encryption method: ");
                if (!Polybius.IsValidString(polybiusInput)) {
                    System.out.println("Please enter a valid password (only lowercase letters).");
                } else {
                    System.out.println("Encrypted password: " + Polybius.Encrypt(polybiusInput));
                }
                break;
            case 4:
                // Encrypt the user's input with Enigma method
                String enigmaInput = getInput(scanner, "Enter the password for Enigma encryption method: ");
                System.out.println("Encrypted password: " + Enigma.Encrypt(enigmaInput));
                break;
            case 5:

            case 6:

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

}
