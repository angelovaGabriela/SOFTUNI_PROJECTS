import java.util.Scanner;

public class RepeatString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] words = scanner.nextLine().split(" ");
        for (String word: words) {

            String repeatedWord  = repeatWord(word, word.length());
            System.out.print(repeatedWord);
        }
    }

    private static String repeatWord(String word, int numberOfRepetition) {

   String result = "";
        for (int i = 0; i < numberOfRepetition; i++) {
           result =  result + word;
        }
        return result;
    }
}
