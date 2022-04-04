import java.util.Scanner;

public class SumPrimeNonPrime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        int sumPrime = 0;
        int sumNoPrime = 0;

        while (!input.equals("stop")){
            int number = Integer.parseInt(input); // превръщам си стринга в интежер
            boolean isPrime = true;
            if (number < 0 ){
                System.out.println("Number is negative.");
            } else {
                for (int i = 2; i < number; i++) { //от първото просто число
                    if (number % i == 0 ){ // ако се дели на i без остатък не е просто и програмата приключва
                        isPrime = false;
                        break;
                    }
                } if (isPrime){
                    sumPrime += number;

                } else {
                    sumNoPrime += number;
                }
            }
            input = scanner.nextLine();
        }
        System.out.printf("Sum of all prime numbers is: %d%n", sumPrime);
        System.out.printf("Sum of all non prime numbers is: %d", sumNoPrime);


    }
}
