public class EnigmaRotor {

    private String alphabet;
    private int position;

    /**
     * Define the class parameters
     * @param customAlphabet Define a custom alphabet for each rotor
     * @param position Define the starting position of the rotor
     */
    public EnigmaRotor(String customAlphabet, int position) {
        this.alphabet = customAlphabet;
        this.position = position;
    }

    /**
     * Encrypt a letter when it is going from base to reflector
     * @param letter The letter to encrypt
     * @return The encrypted letter
     */
    public char Encrypt(char letter) {

        int index = (letter - 97 + this.position) % 26;
        return alphabet.charAt(index);

    }

    /**
     * Encrypt a letter when it is going from reflector to base
     * @param letter The letter to encrypt
     * @return The encrypted letter
     */
    public char ReverseEncrypt(char letter) {

        int index = alphabet.indexOf(letter);
        return (char) (97 + (index - this.position + 26) % 26);

    }

}
