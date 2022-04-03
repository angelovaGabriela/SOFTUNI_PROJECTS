import java.util.ArrayDeque;
import java.util.Scanner;

public class ReverseNumberWithStack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //1.Всички числа да ги сложа в стека
        //2. Вадя ги от стека - > докато stack.size == 0

        String [] inputNumbers = scanner.nextLine().split("\\s+");
        ArrayDeque<String> stack = new ArrayDeque<>();

        for (String number: inputNumbers) {
            stack.push(number);
        }

        while (!stack.isEmpty()){
            System.out.print(stack.pop() + " ");//отпечатвам и махам от стека
        }
    }
}
