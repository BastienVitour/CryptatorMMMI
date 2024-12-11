public class Main {
    public static void main(String[] args) {

        String input = "notmatching";
        if(!Polybius.IsValidString(input)) {
            System.out.println("Veuillez entrer un message valide (uniquement des lettres minuscules)");
        }
        else {
            String aaa = Polybius.Encrypt(input);
            System.out.println(aaa);
            String bbb = Polybius.Decrypt(aaa);
            System.out.println(bbb);
        }


    }
}