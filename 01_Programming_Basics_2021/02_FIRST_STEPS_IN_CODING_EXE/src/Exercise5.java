import java.util.Scanner;

public class Exercise5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double rent = Double.parseDouble(scanner.nextLine());

         double priceCake = rent * 0.2;
         double priceDrinks = priceCake - (priceCake * 0.45);
         double animation = rent / 3;
         double sum = rent + priceCake + priceDrinks + animation;

         System.out.printf("%.1f",sum);





    }
}
