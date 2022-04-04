import java.io.*;

public class CopyBytes {
    public static void main(String[] args) throws IOException {

        String path =
                "C:\\Users\\ANGELOVA\\Desktop\\SoftUni\\3_JAVA_ADVANCED\\4_STREAM_FILES_DIRECTIONS\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\input.txt";

        FileInputStream inputStream = new FileInputStream(path);
        FileOutputStream outputStream = new FileOutputStream("out-as-bytes.txt");

        PrintStream out = new PrintStream(outputStream);

        int value = inputStream.read();

        while (value != -1){
           if (value != 10 && value != 32) {

              out.print(value);
           } else {
               out.print((char) value);
           }
            value = inputStream.read();
        }

        outputStream.close();//след като съм отворила inputStream задължително да го затворя
    }
}

