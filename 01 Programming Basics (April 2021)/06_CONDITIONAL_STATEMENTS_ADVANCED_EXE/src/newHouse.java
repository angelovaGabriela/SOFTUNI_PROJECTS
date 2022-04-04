import java.util.Scanner;

public class newHouse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String typeFlower = scanner.nextLine();
        int numberFlowers = Integer.parseInt(scanner.nextLine());
        int budget = Integer.parseInt(scanner.nextLine());

        double totalPrice = 0.00;





        switch (typeFlower){
            case "Roses":
                totalPrice = numberFlowers * 5;
                if (numberFlowers > 80){
                    totalPrice = totalPrice - (totalPrice * 0.10);

                }
                break;
            case "Dahlias":
                totalPrice = numberFlowers * 3.80;
                if (numberFlowers > 90){
                    totalPrice = totalPrice - (totalPrice * 0.15);
                }
                break;
            case "Tulips":
                totalPrice = numberFlowers * 2.80;
                if (numberFlowers > 80){
                    totalPrice = totalPrice - (totalPrice * 0.15);

                }
                break;
            case "Narcissus":
                totalPrice = numberFlowers * 3.00;
                if (numberFlowers < 120){
                    totalPrice = totalPrice + (totalPrice * 0.15);
                }
                break;
            case "Gladiolus":
                totalPrice = numberFlowers * 2.50;
                if (numberFlowers < 80){
                    totalPrice = totalPrice + (totalPrice * 0.20);

                }
                break;
            default:
                break;
        } if (totalPrice <= budget){
            System.out.printf("Hey, you have a great garden with %d %s and %.2f leva left.", numberFlowers, typeFlower, budget - totalPrice);
        } else {
            System.out.printf("Not enough money, you need %.2f leva more.", totalPrice - budget);
        }


    }
}
