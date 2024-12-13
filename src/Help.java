public class Help {

    public static void DisplayHelp(int method) {

        switch (method) {
            case 1:
                System.out.println("ROT(X) Encryption:");
                System.out.println("This is a simple cipher that shifts each letter in the text by X places in the alphabet.");
                System.out.println("Example: A -> D in ROT(3).");
                break;
            case 2:
                System.out.println("Vigenère Encryption:");
                System.out.println("This uses a repeating keyword to shift letters. Each letter in the keyword determines the shift.");
                System.out.println("Example: 'HELLO' with keyword 'KEY' is encrypted by shifting H by K, E by E, and so on.");
                break;
            case 3:
                System.out.println("Polybius Encryption:");
                System.out.println("A grid is used to encode letters into pairs of numbers based on their position in the grid.");
                System.out.println("Example: H = 23 in a 5x5 grid.");
                break;
            case 4:
                System.out.println("Enigma Encryption:");
                System.out.println("Used in WWII, this complex machine cipher scrambled letters based on rotor settings.");
                System.out.println("Example: Typing A might produce Q depending on the rotor configuration.");
                break;
            case 5:
                System.out.println("RC4 Encryption:");
                System.out.println("This stream cipher encrypts data one bit or byte at a time using a key stream derived from a shared key.");
                System.out.println("Example: 'HELLO' becomes scrambled based on the key, producing an encrypted result.");
                break;
            case 6:
                System.out.println("AES Encryption:");
                System.out.println("AES is a modern encryption standard that splits data into blocks and scrambles it using mathematical steps.");
                System.out.println("Example: 'HELLO WORLD' becomes a secure, unreadable format based on the key.");
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 6.");
        }

    }

}
