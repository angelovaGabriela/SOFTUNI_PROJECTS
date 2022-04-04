import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CountCharacterType {
    public static void main(String[] args) throws IOException {
        String pathStr = "C:\\\\Users\\\\ANGELOVA\\\\Downloads\\\\04. Java-Advanced-Streams-Files-and-Directories-Resources\\\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\\\input.txt";

        List<String> allLines = Files.readAllLines(Paths.get(pathStr));

        int countVowels = 0;
        int countConsonants = 0;
        int countPunctuations = 0;

        for (String line : allLines) {

            for (int index = 0; index < line.length(); index++) {
                char currentSymbol = line.charAt(index);
                if (currentSymbol == ' '){
                 continue;
                }
                if (currentSymbol == 'a' || currentSymbol == 'e' || currentSymbol == 'i' || currentSymbol == 'o' || currentSymbol == 'u') {
                    countVowels++;
                } else if (currentSymbol == '!' || currentSymbol == ',' || currentSymbol == '.' || currentSymbol == '?') {
                    countPunctuations++;
                } else {
                    countConsonants++;
                }
            }
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("output_4.txt"));

        writer.write("Vowels: " + countVowels + "\n");
        writer.write("Consonants: " + countConsonants  + "\n");
        writer.write("Punctuation: " + countPunctuations);

        writer.close();
    }
}