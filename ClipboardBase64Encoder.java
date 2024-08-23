import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;

public class ClipboardBase64Encoder {

    public static void main(String[] args) {
        boolean encloseInQuotes = false;
        boolean outputToConsole = false;
        String outputFilename = null;
        String inputFilename = null;

        // Parse command-line arguments
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-q":
                    encloseInQuotes = true;
                    break;
                case "-c":
                    outputToConsole = true;
                    break;
                case "-o":
                    if (i + 1 < args.length) {
                        outputFilename = args[++i]; // Get the filename after -o
                    } else {
                        System.out.println("Error: Missing filename after -o");
                        return;
                    }
                    break;
                case "-i":
                    if (i + 1 < args.length) {
                        inputFilename = args[++i]; // Get the filename after -i
                    } else {
                        System.out.println("Error: Missing filename after -i");
                        return;
                    }
                    break;
                case "-h":
                    displayHelp();
                    return; // Exit after showing help
                default:
                    System.out.println("Unknown option: " + args[i]);
                    displayHelp();
                    return;
            }
        }

        try {
            String text;

            // Read input from file if '-i' is specified
            if (inputFilename != null) {
                StringBuilder fileContent = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new FileReader(inputFilename))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        fileContent.append(line).append(System.lineSeparator());
                    }
                } catch (IOException e) {
                    System.out.println("Error reading from file: " + inputFilename);
                    e.printStackTrace();
                    return;
                }
                text = fileContent.toString().trim();
            } else {
                // Read input from clipboard if no input file is specified
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                Transferable content = clipboard.getContents(null);
                if (content != null && content.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    text = (String) content.getTransferData(DataFlavor.stringFlavor);
                } else {
                    System.out.println("No text content found in the clipboard.");
                    return;
                }
            }

            // Encode the text to Base64
            String encodedText = Base64.getEncoder().encodeToString(text.getBytes());

            // If the '-q' switch is present, enclose the encoded text in double quotes
            if (encloseInQuotes) {
                encodedText = "\"" + encodedText + "\"";
            }

            if (outputToConsole) {
                // Print the encoded text to the console
                System.out.println(encodedText);
            } else if (outputFilename != null) {
                // Write the encoded text to the specified file
                try (FileWriter writer = new FileWriter(outputFilename)) {
                    writer.write(encodedText);
                    System.out.println("Encoded text has been written to: " + outputFilename);
                } catch (IOException e) {
                    System.out.println("Error writing to file: " + outputFilename);
                    e.printStackTrace();
                }
            } else {
                // Write the encoded text back to the clipboard
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                StringSelection encodedSelection = new StringSelection(encodedText);
                clipboard.setContents(encodedSelection, null);
                System.out.println("Encoded text has been copied back to the clipboard.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to display help information
    private static void displayHelp() {
        System.out.println("ClipboardBase64Encoder: A simple tool to encode clipboard or file text to Base64.");
        System.out.println("Usage:");
        System.out.println("  java ClipboardBase64Encoder [options]");
        System.out.println("  Without any option(s), the clipboard is used for input and output.\n");
        System.out.println("Options:");
        System.out.println("  -h            Display this help message.");
        System.out.println("  -q            Enclose the Base64 output in double quotes (\"\").");
        System.out.println("  -c            Print the encoded output to the console instead of copying to the clipboard.");
        System.out.println("  -o <FILENAME> Write the encoded output to the specified file instead of copying to the clipboard.");
        System.out.println("  -i <FILENAME> Read input data from the specified file instead of the clipboard.");
        System.out.println("\nExample:");
        System.out.println("  java ClipboardBase64Encoder -q -c");
        System.out.println("  java ClipboardBase64Encoder -q -o output.txt");
        System.out.println("  java ClipboardBase64Encoder -i input.txt -o output.txt");
    }
}
