import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ReadFile {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        String path =
                "C:\\Users\\ANGELOVA\\Desktop\\SoftUni\\3_JAVA_ADVANCED\\4_STREAM_FILES_DIRECTIONS\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\input.txt";

        FileInputStream inputStream = new FileInputStream(path);

                int value = inputStream.read();

        while (value != -1){
            System.out.print( Integer.toBinaryString(value) + " ");

            value = inputStream.read();
        }
    }
}
