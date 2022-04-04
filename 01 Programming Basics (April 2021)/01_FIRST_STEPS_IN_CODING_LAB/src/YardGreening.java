import java.util.Scanner;

public class YardGreening {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double greenMetres = Double.parseDouble(scanner.nextLine());
        double price = greenMetres * 7.61;
        double discount = price * 0.18;
        double finalSum = price - discount;
        String one = "The final price is: ";
        String two = "The discount is: ";

        System.out.printf("%s" + "%.2f lv. %n"+ "%s" + "%.2f lv.", one, finalSum,two, discount);

        // 1 кв.м = 7.61 лв
        //отстъпка 18% = 0.18 * крайната сума
        //сума = брой кв.м * 7.61
        //крайна сума = сума-(сума * 0.18)
    }
}
