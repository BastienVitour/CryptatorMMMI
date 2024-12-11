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
        if(method == 1) {
            // Encrypt the user's input with Cesar method
            String input = "hello";
            int rot = 3;
            if(!Cesar.IsValidString(input)){
                System.out.println("Please enter a valid password.");
            } else {
                Cesar.Encrypt(input, rot);
            }

        } else if(method == 2){
            // Encrypt the user's input with Vigenere method
            String plaintext = Vigenere.EntryChain();
            String key = Vigenere.EntryKey();
            Vigenere.VigenereEncrypt(plaintext, key);

        } else if(method == 3){
            // Encrypt the user's input with Polybius method
            String input = "notmatching";
            if(!Polybius.IsValidString(input)) {
                System.out.println("Please enter a valid password (only lowercase letters)");
            } else {
                Polybius.Encrypt(input);
            }
        } else if(method == 4){
            // Encrypt the user's input with Enigma method
            Enigma.Encrypt("hello");
        } else if(method == 5){

        } else if(method == 6){

        }

    }

}
