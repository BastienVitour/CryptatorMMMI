import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    /**
     * Creates a file at the specified path
     */
    public static void CreateFile(String path) {

        try {
            File file = new File(path);

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

    /**
     * Adds text to the specified file
     * @param path The path of the file to edit
     * @param text The text to add to the file
     */
    public static void AddTextToFile(String path, String text) {

        try {
            FileWriter fw = new FileWriter(path, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(text);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }

    /**
     * Reads data from a file
     * @param path The path to the file
     * @return An array of the lines of the file
     */
    public static List<String> ReadFile(String path) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
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
