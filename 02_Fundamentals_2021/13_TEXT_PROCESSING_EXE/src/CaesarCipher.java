import java.util.Scanner;

public class CaesarCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        String encryptedText = "";

        for (char symbol : text.toCharArray()) {
           char encryptedSymbol = (char)(symbol + 3);// получавам аски кода на символа който е 3 позиции след моя
            encryptedText += encryptedSymbol;// добавям го към криптирания текст

        }
        System.out.println(encryptedText.toString());
    }
}
