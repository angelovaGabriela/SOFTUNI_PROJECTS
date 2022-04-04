import java.util.Scanner;

public class dayOfWeek {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] days = {
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday",
                "Sunday",

        };
        int currentDay = Integer.parseInt(scanner.nextLine());
        if (currentDay>=1 && currentDay<=7){
            System.out.println(days[currentDay - 1]);// защото първият ден е с индекс 0, човека задава стойност 1, но иска да вземе първия елемент, който е винаги с индекс 1. Взимаме елемент от масива days.
        } else {
            //Invalid day!
            System.out.println("Invalid day!");
        }

    }
}

