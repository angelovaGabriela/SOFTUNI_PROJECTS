import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;

public class AllCapitals {
    public static void main(String[] args) throws IOException {
      String pathStr = "C:\\\\Users\\\\ANGELOVA\\\\Downloads\\\\04. Java-Advanced-Streams-Files-and-Directories-Resources\\\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\\\input.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        Files.readAllLines(Paths.get(pathStr)).forEach(
                line -> {
                    try {
                        writer.write(line.toUpperCase());
                        writer.newLine();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
        );
        writer.close();
    }
}
