
import java.util.ArrayDeque;
import java.util.Scanner;

public class BalancedParentheses {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        ArrayDeque<Character> openBrackets = new ArrayDeque<>();
        boolean areBalanced = false;
        for (int index = 0; index < input.length(); index++) {

            char currentBracket = input.charAt(index);

            if (currentBracket == '(' || currentBracket == '{' || currentBracket == '[') {
                openBrackets.push(currentBracket);
            } else if (currentBracket == ')' || currentBracket == '}' || currentBracket == ']') {
                // проверка дали затворената скоба съвпада с последната отворена

                if (openBrackets.isEmpty()){
                    areBalanced = false;
                    break;
                }
                char lastOpenBracket = openBrackets.pop(); // последната отворена скоба
                if (lastOpenBracket == '(' && currentBracket == ')') {
                    areBalanced = true;
                } else if (lastOpenBracket == '{' && currentBracket == '}') {
                    areBalanced = true;
                } else if (lastOpenBracket == '[' && currentBracket == ']') {
                    areBalanced = true;
                } else {
                    areBalanced = false;
                    break;
                }
            }
        }

        if (areBalanced){
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }


    }

}
