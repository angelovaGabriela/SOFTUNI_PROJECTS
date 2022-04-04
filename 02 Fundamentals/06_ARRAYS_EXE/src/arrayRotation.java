import java.util.Scanner;

public class arrayRotation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    String [] numbers = input.split(" ");
    int numberRotations = Integer.parseInt(scanner.nextLine());

        for (int rotation = 1; rotation <= numberRotations; rotation++) {
            String firstElementOfArray = numbers [0];
            for (int index = 0; index < numbers.length - 1; index++) {
                numbers[index]=numbers[index+1];//вземи елемента, който седи на следващия индекс

            }
            numbers[numbers.length-1]=firstElementOfArray;

        }
        System.out.println(String.join(" ", numbers));
    }
}
