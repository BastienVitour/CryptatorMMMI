public class Main {
    public static void main(String[] args) {

        String enigma = Enigma.Encrypt("hello");
        System.out.println(enigma);
        System.out.println(Enigma.Decrypt(enigma));

        String input = "not matching";
        if(!Polybius.IsValidString(input)) {
            System.out.println("Veuillez entrer un message valide (uniquement des lettres minuscules)");
        }
        else {
            String aaa = Polybius.Encrypt(input);
            System.out.println(aaa);
            String bbb = Polybius.Decrypt(aaa);
            System.out.println(bbb);
            FileManager.CreateFile();
            String usage = "gmail";
            String method = "Polybius";
            PasswordManager.RegisterPassword(usage, aaa, method, "");
        }

    }
}