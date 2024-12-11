import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

    public static List<String> ReadFile() {

        try {
            BufferedReader br = new BufferedReader(new FileReader("./Passwords/password.txt"));
            String line = br.readLine();
            List<String> lines = new ArrayList<>();

            while(line != null) {
                lines.add(line);
                line = br.readLine();
            }

            return lines;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
