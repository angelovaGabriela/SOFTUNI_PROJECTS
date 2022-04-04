import java.util.Scanner;

public class Substring {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String wordToRemove = scanner.nextLine();
        String input = scanner.nextLine();

        while (input.contains(wordToRemove)){
            input = removeOccurrence(input, wordToRemove);

        }
        System.out.println(input);
    }

    private static String removeOccurrence(String text, String wordToRemove) {
    int beginIndex = text.indexOf(wordToRemove);//начален индекс
    int endIndex = wordToRemove.length() + beginIndex;

    String firstPart = text.substring(0,beginIndex);
    String secondPart = text.substring(endIndex);

    return firstPart + secondPart;

    }
}
