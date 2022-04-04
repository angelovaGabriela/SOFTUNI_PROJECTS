import java.util.Scanner;

public class MiddleCharacters {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        printMiddleCharacter(input);
    }

    private static void printMiddleCharacter(String input){
        if(input.length() % 2 != 0){
            int indexMiddleSymbol = input.length()/2;
            System.out.println(input.charAt(indexMiddleSymbol));
        } else {
            int indexFirstMiddleSymbol = input.length()/2-1;
            int indexSecondMiddleSymbol = input.length() / 2;
            System.out.printf("%c%c", input.charAt(indexFirstMiddleSymbol),input.charAt(indexSecondMiddleSymbol));
        }
    }
}
