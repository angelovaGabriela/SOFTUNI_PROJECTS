import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class WriteInFile {



        public static void main(String[] args) throws IOException {


            String path =
                    "C:\\Users\\ANGELOVA\\Desktop\\SoftUni\\3_JAVA_ADVANCED\\4_STREAM_FILES_DIRECTIONS\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\input.txt";

            FileInputStream inputStream = new FileInputStream(path);
            FileOutputStream outputStream = new FileOutputStream("out.txt");

          int value = inputStream.read();

          while (value != -1){
              char current = (char) value;
              if (current != ',' && '.' != current && current != '!'&& current != '?'){
                  outputStream.write(current);
              }
              value = inputStream.read();
            }

          outputStream.close();//след като съм отворила inputStream задължително да го затворя
        }
    }
