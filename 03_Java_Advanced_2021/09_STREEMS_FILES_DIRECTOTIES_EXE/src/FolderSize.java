import java.io.File;
import java.io.IOException;

public class FolderSize {

    public static void main(String[] args) throws IOException {
        String pathStr = "C:\\Users\\ANGELOVA\\Downloads\\04. Java-Advanced-Streams-Files-and-Directories-Resources\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\Exercises Resources";


        File folder = new File(pathStr);
        File[] filesInFolder = folder.listFiles();

      int folderSize = 0;

      for (File file : filesInFolder){
        folderSize += file.length();

      }

        System.out.println("Folder size: " + folderSize);
    }
}
