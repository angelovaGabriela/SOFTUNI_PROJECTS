import java.util.Scanner;

public class OldBooks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String favouriteBook = scanner.nextLine();
        int counter = 0;
        boolean isFound = false;
        String nextBook = scanner.nextLine();

        while (!nextBook.equals("No More Books")) {
            if (nextBook.equals(favouriteBook)) {
                isFound = true;
                break;
            }
            counter++;
            nextBook = scanner.nextLine();
        }

            if (isFound){
                System.out.printf("You checked %d books and found it.", counter);
            }
            else {
                System.out.println("The book you search is not here!");
                System.out.printf("You checked %d books.", counter);
            }

            }



    }


