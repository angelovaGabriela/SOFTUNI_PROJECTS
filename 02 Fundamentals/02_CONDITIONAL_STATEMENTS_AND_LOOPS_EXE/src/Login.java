import java.util.Scanner;

public class Login {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userName = scanner.nextLine();
        int countFailAttempts = 0;



        String password = "";
        for (int position = userName.length() - 1; position >= 0  ; position--) {
            char currentSymbol = userName.charAt(position);
            password += currentSymbol;

            
        }
        String enteredPassword  = scanner.nextLine();
        while (!enteredPassword.equals(password)){

            countFailAttempts++;
            if (countFailAttempts >= 4){
                System.out.printf("User %s blocked!", userName);
                break;
            } System.out.println("Incorrect password. Try again.");
            enteredPassword = scanner.nextLine();
        } if(enteredPassword.equals(password)) {


            System.out.printf("User %s logged in.", userName);
        }
    }
}
