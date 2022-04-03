import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayDeque<String> stack = new ArrayDeque<>();

        String[] input = scanner.nextLine().split("\\s+");


        Collections.addAll(stack, input); //добавям в стака всичко от масива, може и с foreach


        while (stack.size() > 1){
            int firstNum = Integer.valueOf(stack.pop());//взимам числото което е най-отгоре и го премахвам
            String operator = stack.pop();//отгоре ми остава оператора взимам го него и го премахвам
            int secondNum = Integer.valueOf(stack.pop());// отгоре остава следващото число, взимам го и го премахвам

            switch (operator){
                case "+":
                    stack.push(String.valueOf(firstNum + secondNum));//извършвам операцията и добавям резултата към стака
                    break;
                case "-":
                    stack.push(String.valueOf(firstNum - secondNum));// same
                    break;
            }
        }
         System.out.println(stack.pop());// принтирам резултата, той е единственото останало число в стака
    //https://pastebin.com/CL5v8FYi
        }
    }

