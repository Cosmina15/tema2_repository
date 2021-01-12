package ro.mta.se.lab;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class is used to write to a file all the displays made .
 */
public class WriteToFile
{
    /**
     * This method is used write into file.
     * @param message string that retains what needs to be written to the file
     * @throws IOException
     */
    public void writeToFile(String message) {
        try {
            FileWriter myWriter = new FileWriter("src/main/java/ro/mta/se/lab/afisari.txt", true);
            myWriter.write("\n");
            myWriter.write(message);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
