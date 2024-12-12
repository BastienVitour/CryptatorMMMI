import java.time.LocalDateTime;

public class LFSR {

    public static String RandomHexString(String seed, int turns) {

        int[] generatedNumbers = GenerateRandomNumbers(seed, turns);

        StringBuilder result = new StringBuilder();
        for (int number : generatedNumbers) {
            result.append(Integer.toHexString(number));
        }

        return result.toString();

    }

    public static String RandomLowercaseTextString(String seed, int turns) {

        int[] generatedNumbers = GenerateRandomNumbers(seed, turns);

        StringBuilder result = new StringBuilder();
        for (int number : generatedNumbers) {
            int asciiCompatibleNumber = (number + 96) % 26 + 96;
            result.append((char) asciiCompatibleNumber);

        }

        return result.toString();

    }

    private static int[] GenerateRandomNumbers(String seed, int turns) {

        LocalDateTime dateTime = LocalDateTime.now();
        String binaryString = StringToBinary(seed + dateTime);

        int[] generatedNumbers = new int[turns];

        for (int i = 0; i < turns; i++) {

            binaryString = LFSRFunction(binaryString);
            int generatedNumber = Integer.parseInt(binaryString, 2);
            generatedNumbers[i] = generatedNumber;

        }

        return generatedNumbers;

    }

    private static String LFSRFunction(String seed) {

        String newString;
        char charToAdd;

        // XOR function
        charToAdd = seed.charAt(0) == seed.charAt(seed.length() - 1) ? '0' : '1';

        // Remove first bit and add XOR result at the end
        newString = seed.substring(1).concat(String.valueOf(charToAdd));

        return newString;

    }

    // Convert initial seed in binary
    private static String StringToBinary(String text) {

        String binary = "";

        byte[] textBytes = text.getBytes();
        for (byte textByte : textBytes) {
            binary = binary.concat(String.format("%8s", Integer.toBinaryString(textByte)).replace(" ", "0"));
        }

        String editedBinary = "";

        for (int i = 0; i < binary.length(); i+=2) {
            editedBinary = editedBinary.concat(String.valueOf(binary.charAt(i)));
        }

        return editedBinary.substring(editedBinary.length() - 10);

    }


}
