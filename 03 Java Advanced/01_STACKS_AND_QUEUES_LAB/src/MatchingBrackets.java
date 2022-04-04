import java.util.ArrayDeque;
import java.util.Scanner;

public class MatchingBrackets {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // stack защото работи по последния елемент

        ArrayDeque <Integer> stack = new ArrayDeque<>();

        String expression = scanner.nextLine();

        for (int i = 0; i < expression.length() ; i++) {
            char current = expression.charAt(i);
            if (current == '('){
                stack.push(i);
        } else if (current == ')'){
                int begin = stack.pop();
                System.out.println(expression.substring(begin, i+1));

            }
        }
    }
}
