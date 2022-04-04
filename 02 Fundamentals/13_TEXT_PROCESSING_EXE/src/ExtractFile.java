import java.util.Scanner;

public class ExtractFile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    String path = scanner.nextLine();
    String[] parts = path.split("\\\\");
    String fullFileName = parts[parts.length -1];
    String fileName  = fullFileName.split("\\.")[0];
    String extension = fullFileName.split("\\.")[1];

        System.out.println("File name: " + fileName);
        System.out.println("File extension: " + extension);
    }
}
