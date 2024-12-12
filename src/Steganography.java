import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class Steganography {

    private final static String startString = "StartText";
    private final static String stopString = "StopText";

    public static void Encrypt(String text, String sourcePath, String outputPath) throws IOException {

        BufferedImage image = ImageIO.read(new File(sourcePath));

        WritableRaster raster = image.getRaster();
        int width = raster.getWidth();
        int height = raster.getHeight();

        int index = 0;
        int startIndex = 0;
        int stopIndex = 0;
        int messageIndex = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int[] pixel = raster.getPixel(x, y, (int[]) null);

                int red = pixel[0];
                int green = pixel[1];
                int blue = pixel[2];

                if(startIndex < startString.length()) {
                    red = startString.charAt(startIndex);
                    startIndex++;
                }
                else if(messageIndex < text.length()) {
                    green = text.charAt(messageIndex);
                    messageIndex++;
                }
                else if(stopIndex < stopString.length()) {
                    blue = stopString.charAt(stopIndex);
                    stopIndex++;
                }

                // Update the pixel
                pixel[0] = red;   // Red remains the same
                pixel[1] = green; // Green remains the same
                pixel[2] = blue;  // Modified blue

                raster.setPixel(x, y, pixel);
                index++;
            }
        }

        File outputFile = new File(outputPath);
        ImageIO.write(image, "png", outputFile);

    }

    public static String Decrypt(String imagePath) throws IOException {

        BufferedImage image = ImageIO.read(new File(imagePath));

        Raster raster = image.getRaster();
        int width = raster.getWidth();
        int height = raster.getHeight();

        int index = 0;
        int startIndex = 0;
        int stopIndex = 0;
        int messageIndex = 0;

        int[] bluePixels = new int[height * width];
        int blueIndex = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int[] pixel = raster.getPixel(x, y, (int[]) null);
                bluePixels[blueIndex] = pixel[2];
                blueIndex++;
            }
        }

        startIndex = startString.length();

        stopIndex = FindStopIndex(bluePixels);

        if(stopIndex == -1) {
            return "Stop index not found";
        }

        byte[] messageBytes = new byte[stopIndex - startIndex];

        outerloop:
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                if(index < startIndex) {
                    index++;
                    continue;
                }
                if(index == stopIndex) {
                    break outerloop;
                }

                int[] pixel = raster.getPixel(x, y, (int[]) null);

                int red = pixel[0];
                int green = pixel[1];
                int blue = pixel[2];

                messageBytes[messageIndex] = (byte) green;

                messageIndex++;
                index++;
            }
        }

        return new String(messageBytes);

    }

    public static int FindStopIndex(int[] values) {

        byte[] targetValues = stopString.getBytes();
        int totalValues = values.length;

        // Iterate through the large array
        for (int i = 0; i <= totalValues - targetValues.length; i++) {
            boolean match = true;

            // Check if the subsequence matches
            for (int j = 0; j < targetValues.length; j++) {
                if (values[i + j] != targetValues[j]) {
                    match = false;
                    break;
                }
            }

            // If a match is found, return the starting index
            if (match) {
                return i;
            }
        }

        // Return -1 if no match is found
        return -1;

    }

}
