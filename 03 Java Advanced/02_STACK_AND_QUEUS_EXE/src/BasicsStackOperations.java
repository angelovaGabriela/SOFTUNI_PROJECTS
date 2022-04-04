
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;

public class BasicsStackOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        //n - брой на числа за добавяне
        //s - брой на числа за премахване
        //x - проверка дали го има в стека true / min element in stack


        int n = scanner.nextInt(); // взима следващия int без значение къде е той
        int s = scanner.nextInt();
        int x = scanner.nextInt();
//n - брой на числа за добавяне
        for (int count = 1; count <= n; count++) {
            stack.push(scanner.nextInt());//добави следващото число, което видищ в конзолата в стека
        }

        //s - брой на числа за премахване
        for (int count = 1; count <= s; count++) {
            stack.pop(); //премахваме
        }
    //ако х го има в стека

        if (stack.contains(x)){
            System.out.println("true");
        } else {
            if (!stack.isEmpty()){
                System.out.println(Collections.min(stack));  //минимален елемент
            } else {
                System.out.println(0);
            }
        }
    }

// https://www.youtube.com/watch?v=XK-rdc4oCl8&feature=emb_title - Robotics**
}
