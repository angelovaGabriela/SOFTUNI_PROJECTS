import java.util.ArrayDeque;
import java.util.Scanner;

public class SimpleTextEditor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<String> wordStates = new ArrayDeque<>();
        int n = Integer.parseInt(scanner.nextLine());
        StringBuilder currentText = new StringBuilder();

        for (int count = 1; count <= n ; count++) {
            String command = scanner.nextLine();
            //"1 [string]"
            //"2 [count]"
            //"3 [index]"
            //"4"

            String commandNumber = command.split("\\s+")[0];

            switch (commandNumber){
                case "1":
                    String textToAdd = command.split("\\s+")[1];
                    currentText.append(textToAdd);
                    wordStates.push(currentText.toString());
                    break;
                case "2":
                    int countElements = Integer.parseInt(command.split("\\s+")[1]);
                    int startIndex = currentText.length() - countElements;
                    currentText.delete(startIndex, startIndex + countElements);
                    wordStates.push(currentText.toString());
                    break;
                case "3":
                    int index = Integer.parseInt(command.split("\\s+")[1]);
                    System.out.println(currentText.charAt(index - 1));// 3-тия символ == индекса и е 3-1, защото индексацията започва от 0=а, 1=б, 2=с
                    break;
                case "4":
                    if (wordStates.size() > 1) {
                        wordStates.pop();// махни ми сегашното състояние
                        currentText = new StringBuilder(wordStates.peek());// искам сегашната дума да е равна на предната

                    } else {
                      currentText = new StringBuilder();
                    }
                    break;
            }
        }
    }
}
