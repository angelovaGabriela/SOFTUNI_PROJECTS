import java.util.Scanner;

public class SkiTrip {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int accommodationDays = Integer.parseInt(scanner.nextLine());
        String typeOfRoom = scanner.nextLine();
        String rates = scanner.nextLine();

        double price = 0.00;
        double totalPrice = 0.00;
        double finalPrice = 0.00;
        double discount = 0.00;
        accommodationDays = accommodationDays - 1;
       if (rates.equals("positive")){
           switch (typeOfRoom){
               case "room for one person":
                   price = 18.00;
                   finalPrice = accommodationDays * price;
                   break;


               case "apartment":
                   price = 25.00;
                   totalPrice = accommodationDays * price;


                   if (accommodationDays < 10){
                       discount = totalPrice * 0.3;
                       finalPrice = totalPrice - discount;
                   } else if ((accommodationDays > 10)&&(accommodationDays <=15)){
                       discount = totalPrice * 0.35;
                       finalPrice = totalPrice - discount;
                   } else if (accommodationDays > 15){
                       discount = totalPrice * 0.5;
                       finalPrice = totalPrice - discount;
                   } break;
               case "president apartment":
                   price = 35;
                   totalPrice = accommodationDays * price;

                   if (accommodationDays < 10){
                      discount = totalPrice * 0.1;
                       finalPrice = totalPrice - discount;
                   } else if ((accommodationDays > 10)&&(accommodationDays <=15)){
                      discount = totalPrice * 0.15;
                       finalPrice = totalPrice - discount;
                   } else if (accommodationDays > 15){
                       discount = totalPrice * 0.2;
                       finalPrice = totalPrice - discount;
                   }
                   break;

           } System.out.printf("%.2f", finalPrice = finalPrice + (finalPrice * 0.25));
       } else if (rates.equals("negative")){
           switch (typeOfRoom){
               case "room for one person":
                   price = 18.00;
                   finalPrice = accommodationDays * price;
                   break;
               case "apartment":
                   price = 25.00;
                   totalPrice = accommodationDays * price;
                   if (accommodationDays < 10){
                       discount = totalPrice * 0.3;
                       finalPrice = totalPrice - discount;
                   } else if ((accommodationDays > 10)&&(accommodationDays <=15)){
                        discount = totalPrice * 0.35;
                       finalPrice = totalPrice - discount;
                   } else if (accommodationDays > 15){
                        discount = totalPrice * 0.5;
                       finalPrice = totalPrice - discount;
                   } break;
               case "president apartment":
                   price = 35;
                   totalPrice = accommodationDays * price;
                   if (accommodationDays < 10){
                        discount = totalPrice * 0.1;
                       finalPrice = totalPrice - discount;
                   } else if ((accommodationDays > 10)&&(accommodationDays <=15)){
                       discount = totalPrice * 0.15;
                       finalPrice = totalPrice - discount;
                   } else if (accommodationDays > 15){
                      discount = totalPrice * 0.2;
                      finalPrice = totalPrice - discount;
                   }
                   break;



       } System.out.printf("%.2f", finalPrice = finalPrice - (finalPrice * 0.1));

    }
}
    }