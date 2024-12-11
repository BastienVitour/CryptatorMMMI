import java.util.Base64;

public class PasswordManager {

    private static String EncodeText(String usage, String password, String method, String secretKey) {

        String encodedUsage = Base64.getEncoder().encodeToString(usage.getBytes());
        String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
        String encodedMethod = Base64.getEncoder().encodeToString(method.getBytes());
        String encodedSecretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        return String.join("|", encodedUsage, encodedPassword, encodedMethod, encodedSecretKey);

    }

    public static void RegisterPassword(String usage, String password, String method, String secretKey) {

        String stringToAddToFile = EncodeText(usage, password, method, secretKey);
        FileManager.AddTextToFile(stringToAddToFile);

    }

}
