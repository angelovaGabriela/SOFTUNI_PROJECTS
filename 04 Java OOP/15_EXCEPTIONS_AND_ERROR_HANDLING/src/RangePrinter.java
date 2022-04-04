import java.util.Scanner;
import java.util.stream.IntStream;

public class RangePrinter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
while(true) { // да си продължаваме да екзекютваме програмката

    try {
        int start = Integer.parseInt(scanner.nextLine());
        int end = Integer.parseInt(scanner.nextLine());

        printRange(start, end);
        break; // stops the while loop // can't stop the execution of try/catch block
    } catch (NumberFormatException e) {
        System.out.println("Please enter valid integers");

    } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}

    }

    public static void printRange(int start, int end) {

      try {
          validateRange(start, end);
      } catch (InvalidRangeException e){
          throw new IllegalArgumentException(e.getMessage(), e);
      } // unchecked exception - хвърля го на следващия метод да го овладее
        IntStream.rangeClosed(start, end)
                .forEach(System.out::println);
    }
    private static void validateRange(int start, int end) throws InvalidRangeException { // checked exception
        // - показва, че може да възникне грешка докато си пиша кода,
        // този който вика метода (клиента на метода) трябва да го овладее в try/catch
        if (start <= 1 || start >= end || end >= 100){
            throw new InvalidRangeException("The range should be 1 < start < end < 100");
        }
    }
}
