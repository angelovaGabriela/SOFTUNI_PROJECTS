import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class ExtractIntegers {
    public static void main(String[] args) throws IOException {


    String path =
            "C:\\Users\\ANGELOVA\\Desktop\\SoftUni\\3_JAVA_ADVANCED\\4_STREAM_FILES_DIRECTIONS\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\input.txt";

    FileInputStream inputStream = new FileInputStream(path);
    FileOutputStream outputStream = new FileOutputStream("out-numbers.txt");

    PrintStream out = new PrintStream(outputStream);

    Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNextLine()){
            if (scanner.hasNextInt()){
                int number = scanner.nextInt();
                out.println(number);
            }
            else {
                scanner.next();
            }
    }

        outputStream.close();//след като съм отворила inputStream задължително да го затворя
}
}


