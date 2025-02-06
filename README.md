# WAV Steganography in Java

This project implements audio steganography using the Least Significant Bit (LSB) encoding technique to hide and extract secret messages within WAV audio files.

## Features

- **Embed text into a WAV file**: Hides a secret message inside the audio file by modifying the LSBs of the audio files.
- **Extract text from a WAV file**: Recovers the hidden message from an audio file containing embedded text.


## Files Overview

- **`Embed.java`**: Handles the embedding of secret text into the WAV file.
- **`Extract.java`**: Extracts the hidden message from the WAV file.
- **`Steganography.java`**: Provides methods to call the embedding and extraction methods.


## How It Works

1. **Embedding**:

   - Reads the 44-byte WAV header.
   - Converts the secret message into a binary string.
   - Modifies the least significant bit (LSB) of the audio samples to store the binary data.
   - Writes the modified audio data to a new WAV file.

2. **Extraction**:

   - Reads the WAV file and skips the header.
   - Extracts the LSBs from the audio data to reconstruct the binary string.
   - Converts the binary string back into readable text.

## Example Usage

Modify `Steganography.java` to call `embed()` or `extract()` in `main()`:

```java
public static void main(String[] args) {
    embed();  // To hide the message
    extract();  // To retrieve the message
}
```

## Author
**Samy Chame**

