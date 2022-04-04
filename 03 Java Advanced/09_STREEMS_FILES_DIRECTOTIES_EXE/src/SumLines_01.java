import java.io.IOException;
import java.nio.file.Files;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SumLines_01 {
    public static void main(String[] args) throws IOException {

        String pathStr = "C:\\Users\\ANGELOVA\\Downloads\\04. Java-Advanced-Streams-Files-and-Directories-Resources\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\input.txt";

        Path path = Paths.get(pathStr);

        List<String> allLines = Files.readAllLines(path);

        for (String line : allLines) {
            int sum = 0;

            for (int index = 0; index < line.length(); index++) {
                char currentSymbol = line.charAt(index);

                sum += currentSymbol;
            }
            System.out.println(sum);
        }

    }
}
