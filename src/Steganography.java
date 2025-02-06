import java.io.IOException;

public class Steganography {

    //Input WAV file path
    private static final String audioFilePath = "AudioFiles/input.wav";
    //Stego WAV file path
    private static final String outputFilePath = "AudioFiles/output.wav";
    //Text to embed in the WAV file
    private static String text = "secret message";

    public static void embed(){
        System.out.println("Embedding text into audio...");
        try {
            Embed.embedTextInWav(audioFilePath,outputFilePath,text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Text embedded successfully.");

    }
    public static void extract(){
        System.out.println("\n----------------------------------------------");
        System.out.println("Extracting text from audio...");
        try {
            String extractedText = Extract.extractTextFromWav(outputFilePath);
            System.out.println("\n\u001B[1m Extracted Text: " + extractedText);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void main(String[] args){
//        embed();
//        extract();
    }

}
