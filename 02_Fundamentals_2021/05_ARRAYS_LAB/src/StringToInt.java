import java.util.Scanner;

public class StringToInt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        String[]userInputArray = userInput.split(" ");

        int[]numberArray = new int[userInputArray.length];
        for (int i = 0; i < userInputArray.length; i++) {
            numberArray[i] = Integer.parseInt(userInputArray[i]);// масив от стринг го променям в масив от инт с парсване
            
        }
        System.out.println();
    }
}
