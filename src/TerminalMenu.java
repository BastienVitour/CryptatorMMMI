import java.util.Scanner;

public class TerminalMenu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (true) {
            // Display main menu
            System.out.println("\n=== Main Menu ===");
            System.out.println("1 - Add a password");
            System.out.println("2 - Display a password");
            System.out.println("3 - Hash a password");
            System.out.println("4 - Help");
            System.out.println("5 - Crypter");
            System.out.println("0 - Quit");
            System.out.print("\nEnter your choice : ");

            // Read user entry
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\nPlease enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addPassword(scanner);
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

    // Add password method
    private static void addPassword(Scanner scanner) {
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

        int method = 0;
        try {
            method = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("\nPlease enter a valid number.");
            return;
        }

        if(method == 1) {

        } else if(method == 2){

            String plaintext = Vigenere.EntryChain();
            String key = Vigenere.EntryKey();
            String encrypted = Vigenere.VigenereEncrypt(plaintext, key);
            System.out.println("\nThe encrypted password for site '" + usage + "' is: " + encrypted);

        } else if(method == 3){
            Main.main(new String[0]);
        } else if(method == 4){

        } else if(method == 5){

        } else if(method == 6){

        }

    }

}
