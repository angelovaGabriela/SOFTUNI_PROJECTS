import java.util.Scanner;

public class letterAndNumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        double totalSum = 0;
        String[] passwords  = input.split("\\s+");
        for (String password:passwords) {
            double currentSum = getCurrentSum(password);

                    totalSum+=currentSum;
        }
        System.out.printf("%.2f", totalSum);
    }

    private static double getCurrentSum(String password) {
    char firstLatter = password.charAt(0);
        char secondLater = password.charAt(password.length()-1);

        StringBuilder builder = new StringBuilder(password);
        double number = Double.parseDouble(builder.deleteCharAt(0).deleteCharAt(builder.length()-1).toString());


        if (Character.isUpperCase(firstLatter)){
            int positionUpperLatter = (int) firstLatter - 64;
            number = number / positionUpperLatter;


     } else {
            int positionLoweCase = (int)firstLatter - 96;
        number = number * positionLoweCase;


    }
if(Character.isUpperCase(secondLater)){
    int positionUpperLatter = (int) secondLater - 64;
   number = number - positionUpperLatter;
} else {
    int positionLowerLatter = (int)secondLater - 96;
number = number + positionLowerLatter;
}

        return number;
    }
}
