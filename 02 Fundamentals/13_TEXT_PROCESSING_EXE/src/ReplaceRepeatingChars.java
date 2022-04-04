import java.util.Scanner;

public class ReplaceRepeatingChars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    String inputText = scanner.nextLine();
    StringBuilder newText = new StringBuilder();

    char firstLatter = inputText.charAt(0);
    newText.append(firstLatter);

        for (int index = 1; index < inputText.length(); index++) {
            char currentSymbol = inputText.charAt(index);
            if(currentSymbol != newText.charAt(newText.length()-1)){// ако е различно от последно добавения символ
                newText.append(currentSymbol);
            }

        }
        System.out.println(newText);

    }

}
