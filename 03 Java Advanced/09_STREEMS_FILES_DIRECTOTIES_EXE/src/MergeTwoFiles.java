import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class MergeTwoFiles {
    public static void main(String[] args) throws IOException {


        Path pathFileOne = Paths.get("C:\\Users\\ANGELOVA\\Downloads\\04. Java-Advanced-Streams-Files-and-Directories-Resources\\04. Java-Advanced-Files-and-Streams-Exercises-Resources//inputOne.txt");

        List<String> allLinesFirstFile = Files.readAllLines(pathFileOne);

        Path pathFileTwo = Paths.get("C:\\Users\\ANGELOVA\\Downloads\\04. Java-Advanced-Streams-Files-and-Directories-Resources\\04. Java-Advanced-Files-and-Streams-Exercises-Resources//inputTwo.txt");

        List<String> allLinesSecondFile = Files.readAllLines(pathFileTwo);

        PrintWriter write = new PrintWriter("outputMerge.txt");

        allLinesFirstFile.forEach(write::println);
        allLinesSecondFile.forEach(write::println);

        write.close();
    }
}
