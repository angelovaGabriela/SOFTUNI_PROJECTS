import java.util.Scanner;

public class passwordValidation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();

        boolean isValidLength = isValidLength(password);
        if(!isValidLength){
            System.out.println("Password must be between 6 and 10 characters");
        }

        boolean isValidContent = isValidContent(password);
        if(!isValidContent){
            System.out.println("Password must consist only of letters and digits");
        }

        boolean isValidCount = isValidCountDigits(password);
        if (!isValidCount){
            System.out.println("Password must have at least 2 digits");
        }
        if(isValidLength && isValidContent && isValidCount){
            System.out.println("Password is valid");
        }
        // дължина
        // букви и цифри
        // поне 2 цифри?
        // метод за дължина
    }
   private static boolean isValidLength(String password){
       return password.length() >= 6 && password.length() <= 10;

    }  private static boolean isValidContent(String password){
        for (int index = 0; index < password.length(); index++) {
         char currentSymbol = password.charAt(index);
         if (!Character.isLetterOrDigit(currentSymbol)){
             return false;
         }
        } return true;

    } private static boolean isValidCountDigits(String password){
        int count = 0;
        for (int index = 0; index < password.length(); index++) {
            char currentSymbol = password.charAt(index);
            if (Character.isDigit(currentSymbol)){
                count++;
            }
        }
        return count >= 2;

    }
}
