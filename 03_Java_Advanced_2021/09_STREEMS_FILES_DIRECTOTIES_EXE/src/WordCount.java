import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class WordCount {
    public static void main(String[] args) throws IOException {
// думите за броене
      Path pathToWords = Paths.get("C:\\Users\\ANGELOVA\\Downloads\\04. Java-Advanced-Streams-Files-and-Directories-Resources\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\words.txt");

        LinkedHashMap<String, Integer> wordsCount = new LinkedHashMap<>();

        List <String> allLinesWords = Files.readAllLines(pathToWords);

        allLinesWords.forEach(line -> Arrays.stream(line.split("\\s+")).forEach(word -> wordsCount.put(word, 0)));

        // прочитаме текстът, в който ще броим

        Path pathText =  Paths.get("C:\\Users\\ANGELOVA\\Downloads\\04. Java-Advanced-Streams-Files-and-Directories-Resources\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\text.txt");
//C:\Users\ANGELOVA\Downloads\04. Java-Advanced-Streams-Files-and-Directories-Resources\04. Java-Advanced-Files-and-Streams-Exercises-Resources
        List<String> textAllLines = Files.readAllLines(pathText);

        for (String line : textAllLines) {
            String [] wordLine = line.split("\\s+");
            Arrays.stream(wordLine).forEach(word -> {
                if (word.contains(",")){
                  word =  word.replaceAll(",", "");
                } if (word.contains(".")){
                    word =  word.replaceAll(".", "");
                        }

                if (wordsCount.containsKey(word)){
                    int currentCount = wordsCount.get(word);
                    wordsCount.put(word, currentCount + 1);
                }
            }
            );
        }
        //сортиране по валюе по descending order
        wordsCount.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        // печатаме данните в нов файл
        PrintWriter writer = new PrintWriter("countWords.txt");
        wordsCount.entrySet().forEach(entry -> writer.println(entry.getKey() + " - " + entry.getValue()));

        writer.close();
    }
}
