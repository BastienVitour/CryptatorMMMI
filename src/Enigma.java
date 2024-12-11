public class Enigma {

    private final static EnigmaRotor[] rotors = {
        new EnigmaRotor("ekmflgdqvzntowyhxuspaibrcj", 3),
        new EnigmaRotor("ajdksiruxblhwtmcqgznpfyvoe", 6),
        new EnigmaRotor("bdfhjlcprtxvznyeiwgakmusqo", 8),
    };

    /**
     * Encrypt a string based on Enigma
     * @param inputString The string to encrypt
     * @return The encrypted string
     */
    public static String Encrypt(String inputString) {

        String outputString = "";
        char encryptedLetter;

        for(char letter: inputString.toCharArray()) {

            encryptedLetter = letter;

            // Pass the letter in the rotors
            for (EnigmaRotor rotor: rotors) {
                encryptedLetter = rotor.Encrypt(letter);
            }

            // Pass the letter in the reflector
            encryptedLetter = Reflector(encryptedLetter);

            // Pass the letter in the rotors in the other direction
            for (EnigmaRotor rotor: rotors) {
                encryptedLetter = rotor.ReverseEncrypt(letter);
            }

            outputString = outputString.concat(String.valueOf(encryptedLetter));

        }

        return outputString;

    }

    /**
     * Decrypt a string based on Enigma
     * @param inputString The string to decrypt
     * @return The decrypted string
     */
    public static String Decrypt(String inputString) {

        String outputString = "";
        char encryptedLetter;

        for(char letter: inputString.toCharArray()) {

            encryptedLetter = letter;

            // Pass the letter in the rotors
            for (EnigmaRotor rotor: rotors) {
                encryptedLetter = rotor.ReverseEncrypt(letter);
            }

            // Pass the letter in the reflector
            encryptedLetter = Reflector(encryptedLetter);

            // Pass the letter in the rotors in the other direction
            for (EnigmaRotor rotor: rotors) {
                encryptedLetter = rotor.Encrypt(letter);
            }

            outputString = outputString.concat(String.valueOf(encryptedLetter));

        }

        return outputString;

    }

    /**
     * The reflector of the enigma machine. Returns the letter on the opposite side of the alphabet (ex: A -> Z)
     * @param letter The letter to reflect (ex: B)
     * @return The reflected letter (ex: Y)
     */
    private static char Reflector(char letter) {

        int letterIndex = 'z' - letter;
        return (char)('a' + letterIndex);

    }

}
