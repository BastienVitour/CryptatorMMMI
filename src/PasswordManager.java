import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

public class PasswordManager {

    /**
     * Encodes the parameters and add the password to the text file
     * @param usage The website where the password is used (Youtube...)
     * @param password The encrypted password
     * @param method The method used to encrypt
     * @param secretKey The secret key used to encrypt (if needed)
     */
    public static void RegisterPassword(String usage, String password, String method, String secretKey) {

        String stringToAddToFile = EncodeText(usage, password, method, secretKey);
        FileManager.AddTextToFile("./Passwords/password.txt", stringToAddToFile);

    }

    /**
     * Encode parameters in Base64 to avoid injections
     * @param usage The website where the password is used (Youtube...)
     * @param password The encrypted password
     * @param method The method used to encrypt
     * @param secretKey The secret key used to encrypt (if needed)
     * @return The encoded string
     */
    private static String EncodeText(String usage, String password, String method, String secretKey) {

        String encodedUsage = Base64.getEncoder().encodeToString(usage.getBytes());
        String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
        String encodedMethod = Base64.getEncoder().encodeToString(method.getBytes());
        String encodedSecretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        return String.join("|", encodedUsage, encodedPassword, encodedMethod, encodedSecretKey);

    }

    /**
     * Retrieves the password from the text file based on where it is used
     * @param usage The website where the password is used (Youtube...)
     * @return The password in clear
     */
    public static String RetrievePassword(String usage) {

        String returnedPassword = "";

        try {
            List<String> passwords = FileManager.ReadFile("./Passwords/password.txt");

            for (String line : passwords) {
                String[] currentLine = line.split("\\|");
                String encodedUsage = currentLine[0];
                String usageInFile = new String(Base64.getDecoder().decode(encodedUsage.getBytes()));

                if (Objects.equals(usage, usageInFile)) {
                    String encodedPassword = currentLine[1];
                    String password = new String(Base64.getDecoder().decode(encodedPassword.getBytes()));
                    String encodedMethod = currentLine[2];
                    String method = new String(Base64.getDecoder().decode(encodedMethod.getBytes()));
                    String encodedSecretKey;
                    String secretKey = "";

                    if (currentLine.length == 4) {
                        encodedSecretKey = currentLine[3];
                        secretKey = new String(Base64.getDecoder().decode(encodedSecretKey.getBytes()));
                    }

                    // Verify if it's a chain or not
                    String[] methodlenght = method.split(" ");
                    String[] key = secretKey.split(" ");
                    if (methodlenght.length == 1) {
                        switch (method) {
                            case "Cesar":
                                returnedPassword = Cesar.Decrypt(password, Integer.parseInt(secretKey));
                                break;
                            case "Vigenere":
                                returnedPassword = Vigenere.VigenereDecrypt(password, secretKey);
                                break;
                            case "Polybius":
                                returnedPassword = Polybius.Decrypt(password);
                                break;
                            case "Enigma":
                                returnedPassword = Enigma.Decrypt(password);
                            case "Rc4":
                                returnedPassword = Rc4.Decrypt(password, secretKey);
                            case "AES":
                                String secretKeyParam = secretKey.split("-")[0];
                                String ivParam = secretKey.split("-")[1];
                                returnedPassword = AES.Decrypt(password, secretKeyParam, ivParam);
                                break;
                            default:
                                break;
                        }
                    }
                    else {
                        // Reverse the method and key string to have it in the right order
                        String[] reverseMethod = new String[methodlenght.length];
                        String[] reverseKey = new String[key.length];
                        returnedPassword = password;
                        for (int i = 0; i < methodlenght.length; i++) {
                            reverseMethod[i] = methodlenght[methodlenght.length-i-1];
                            reverseKey[i] = key[methodlenght.length-i-1];
                        }
                        for (int i = 0; i < reverseMethod.length; i++) {
                            switch (reverseMethod[i]) {
                                case "Cesar":
                                    returnedPassword = Cesar.Decrypt(returnedPassword, Integer.parseInt(reverseKey[i]));
                                    break;
                                case "Vigenere":
                                    returnedPassword = Vigenere.VigenereDecrypt(returnedPassword, reverseKey[i]);
                                    break;
                                case "Polybius":
                                    returnedPassword = Polybius.Decrypt(returnedPassword);
                                    break;
                                case "Enigma":
                                    returnedPassword = Enigma.Decrypt(returnedPassword);
                                    break;
                                case "Rc4":
                                    returnedPassword = Rc4.Decrypt(returnedPassword, reverseKey[i]);
                                    break;
                                case "AES":
                                    String secretKeyParam = reverseKey[i].split("-")[0];
                                    String ivParam = reverseKey[i].split("-")[1];
                                    returnedPassword = AES.Decrypt(returnedPassword, secretKeyParam, ivParam);
                                    break;
                                default:
                                    break;
                            }
                        }
                    }

                }
            }

            return returnedPassword;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
