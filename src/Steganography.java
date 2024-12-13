import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class Steganography {

    private final static String startString = "StartText";
    private final static String stopString = "StopText";

    /**
     * Hides a text inside an image
     * @param text The text to hide
     * @param sourcePath The path to the source image
     * @param outputPath The path to the output image with the hidden message
     * @throws IOException
     */
    public static void Encrypt(String text, String sourcePath, String outputPath) throws IOException {

        // Read the image
        BufferedImage image = ImageIO.read(new File(sourcePath));

        WritableRaster raster = image.getRaster();
        int width = raster.getWidth();
        int height = raster.getHeight();

        int index = 0;
        int startIndex = 0;
        int stopIndex = 0;
        int messageIndex = 0;

        // Iterate over each pixel of the image
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int[] pixel = raster.getPixel(x, y, (int[]) null);

                int red = pixel[0];
                int green = pixel[1];
                int blue = pixel[2];

                if(startIndex < startString.length()) {
                    // Edit start point pixels
                    red = startString.charAt(startIndex);
                    startIndex++;
                }
                else if(messageIndex < text.length()) {
                    // Edit message pixels
                    green = text.charAt(messageIndex);
                    messageIndex++;
                }
                else if(stopIndex < stopString.length()) {
                    // Edit stop point pixels
                    blue = stopString.charAt(stopIndex);
                    stopIndex++;
                }

                // Update the pixel
                pixel[0] = red;   // Red remains the same
                pixel[1] = green; // Green remains the same
                pixel[2] = blue;  // Modified blue

                // Set new pixel
                raster.setPixel(x, y, pixel);
                index++;
            }
        }

        // Output the new image
        File outputFile = new File(outputPath);
        ImageIO.write(image, "png", outputFile);

    }

    /**
     * Retrieves the hidden text in an image
     * @param imagePath The path to the source image
     * @return The hidden text
     * @throws IOException
     */
    public static String Decrypt(String imagePath) throws IOException {

        // Read the image
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

        // Retrieve all blue pixels (stop indicator)
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int[] pixel = raster.getPixel(x, y, (int[]) null);
                bluePixels[blueIndex] = pixel[2];
                blueIndex++;
            }
        }

        // Get the start index before the message
        startIndex = startString.length();

        // Get the stop index after the message
        stopIndex = FindStopIndex(bluePixels);

        // If no stop index was found it means there is no message in this image
        if(stopIndex == -1) {
            return "Stop index not found";
        }

        byte[] messageBytes = new byte[stopIndex - startIndex];

        // Iterate over every pixel of the image
        outerloop:
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                // Skip start message
                if(index < startIndex) {
                    index++;
                    continue;
                }
                // Stop when stop message is reached
                if(index == stopIndex) {
                    break outerloop;
                }

                int[] pixel = raster.getPixel(x, y, (int[]) null);

                int red = pixel[0];
                int green = pixel[1];
                int blue = pixel[2];

                // Get message information
                messageBytes[messageIndex] = (byte) green;

                messageIndex++;
                index++;
            }
        }

        return new String(messageBytes);

    }

    /**
     * Find the index of the stop message in the image
     * @param values The values of the blue in the image
     * @return
     */
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
