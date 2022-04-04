
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SumBites {
    public static void main(String[] args) throws IOException {
        String pathStr = "C:\\Users\\ANGELOVA\\Downloads\\04. Java-Advanced-Streams-Files-and-Directories-Resources\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\input.txt";

        byte[] allBytes = Files.readAllBytes((Paths.get(pathStr)));

        long sum = 0;
        for (byte value : allBytes) {
            if (value != 10 && value != 13) { // ако валюто не е равно на нов ред от аски таблицата или каридж ретърн (да започне да чете реда от начало)
                sum += value;
            }

        }
        System.out.println(sum);
    }
}
