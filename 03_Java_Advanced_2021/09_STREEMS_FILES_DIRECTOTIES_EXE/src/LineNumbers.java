import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LineNumbers {
    public static void main(String[] args) throws IOException {
        String pathStr = "C:\\\\Users\\\\ANGELOVA\\\\Downloads\\\\04. Java-Advanced-Streams-Files-and-Directories-Resources\\\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\\\inputLineNumbers.txt";


        List<String> allLines = Files.readAllLines(Paths.get(pathStr));


        PrintWriter writer = new PrintWriter( "output_line_numbers.txt");

                int countRow = 1;

        for (String line : allLines) {

            writer.println(countRow + ". " + line);
            countRow++;
        }

        writer.close();
    }
}
