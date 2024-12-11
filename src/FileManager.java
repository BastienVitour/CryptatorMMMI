import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

    public static void CreateFile() {

        try {
            File file = new File("./Passwords/password.txt");

            if(file.createNewFile()) {
                System.out.println("File created");
            }
            else {
                System.out.println("File already exists");
            }
        }
        catch(IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }

    public static void AddTextToFile(String text) {

        try {
            FileWriter fw = new FileWriter("./Passwords/password.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(text);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }

}
