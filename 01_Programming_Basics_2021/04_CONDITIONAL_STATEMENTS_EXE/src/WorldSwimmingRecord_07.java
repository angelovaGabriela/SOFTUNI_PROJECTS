import java.util.Scanner;

public class WorldSwimmingRecord_07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double recordSeconds = Double.parseDouble(scanner.nextLine());
        double distance = Double.parseDouble(scanner.nextLine());
        double timeForMeter = Double.parseDouble(scanner.nextLine());

        double result = distance * timeForMeter;
        double slowDown = Math.floor(distance/15);
        double slowDown2 = slowDown * 12.5;

        double finalResult = result + slowDown2;

        if (finalResult < recordSeconds) {
            System.out.printf("Yes, he succeeded! The new world record is %.2f seconds.", finalResult);


        } else {
            System.out.printf("No, he failed! He was %.2f seconds slower.",Math.abs(recordSeconds-finalResult));
        }


    }
}
