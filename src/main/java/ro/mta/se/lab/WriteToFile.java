package ro.mta.se.lab;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile
{
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
