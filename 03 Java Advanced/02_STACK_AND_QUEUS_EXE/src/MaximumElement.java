import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;

public class MaximumElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        int n = Integer.parseInt(scanner.nextLine());

        for (int count = 1; count <= n ; count++) {
            String command = scanner.nextLine();

            if (command.equals("2")){
                stack.pop();
            } else if (command.equals("3")) {
                if (stack.size() > 0){
                    System.out.println(Collections.max(stack));
                }
            }else {
                int x = Integer.parseInt(command.split("\\s+")[1]);
                stack.push(x);
            }
        }

    }
}
