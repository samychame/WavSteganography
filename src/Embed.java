import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Embed {

    public static void embedTextInWav(String audioFilePath, String outputFilePath, String text) throws IOException {
        File inputFile = new File(audioFilePath);
        File outputFile = new File(outputFilePath);

        try (FileInputStream fis = new FileInputStream(inputFile);
             FileOutputStream fos = new FileOutputStream(outputFile)) {

            // Read the header (44 bytes for WAV files)
            byte[] header = new byte[44];
            fis.read(header);
            fos.write(header); // Write the header to the output file

            // Convert text to binary
            String binaryText = stringToBinary(text) + "00000000"; // Add a null terminator

            int textIndex = 0;
            int textLength = binaryText.length();

            // Embed text into the audio data
            int data;
            while ((data = fis.read()) != -1) {
                if (textIndex < textLength) {
                    // Modify the least significant bit (LSB)
                    data = (data & 0xFE) | (binaryText.charAt(textIndex) - '0');
                    textIndex++;
                }
                fos.write(data);
            }

            if (textIndex < textLength) {
                throw new IOException("Audio file is too small to embed the entire text.");
            }
        }
    }

    private static String stringToBinary(String text) {
        StringBuilder binary = new StringBuilder();
        for (char c : text.toCharArray()) {
            binary.append(String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0'));
        }
        return binary.toString();
    }




}
