import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Extract {
    public static String extractTextFromWav(String audioFilePath) throws IOException {
        File inputFile = new File(audioFilePath);

        try (FileInputStream fis = new FileInputStream(inputFile)) {

            // Skip the header (44 bytes)
            fis.skip(44);

            StringBuilder binaryText = new StringBuilder();
            int data;

            // Read the audio data to extract text
            while ((data = fis.read()) != -1) {
                binaryText.append(data & 1); // Get the LSB
            }

            return binaryToString(binaryText.toString());
        }
    }

    private static String binaryToString(String binary) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < binary.length(); i += 8) {
            String byteStr = binary.substring(i, Math.min(i + 8, binary.length()));

            //Check if we got to the null terminator
            if (byteStr.equals("00000000"))
                break;
            text.append((char) Integer.parseInt(byteStr, 2));
        }
        return text.toString();
    }

}
