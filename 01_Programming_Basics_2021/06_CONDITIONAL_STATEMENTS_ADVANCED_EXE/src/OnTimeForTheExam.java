import java.util.Scanner;

public class OnTimeForTheExam {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int hourOfTheExam = Integer.parseInt(scanner.nextLine());
        int minOfTheExam = Integer.parseInt(scanner.nextLine());
        int arrivingHour = Integer.parseInt(scanner.nextLine());
        int arrivingMinute = Integer.parseInt(scanner.nextLine());

        int totalMinutes = hourOfTheExam * 60 + minOfTheExam;
        int totalMinutesArriving = arrivingHour * 60 + arrivingMinute;

        if (totalMinutes == totalMinutesArriving){
            System.out.println("On time");
        } else if (totalMinutes > totalMinutesArriving){
          if(totalMinutes - totalMinutesArriving <= 30){
              System.out.println("On time");
          } else {
              System.out.println("Early");
          } if (totalMinutes - totalMinutesArriving < 60){
                System.out.printf(" %d minutes before the start", totalMinutes - totalMinutesArriving);
            } else {
              hourOfTheExam = (totalMinutes - totalMinutesArriving) / 60;
              minOfTheExam = (totalMinutes - totalMinutesArriving) % 60;
                System.out.printf("%d:%02d hours before the start", hourOfTheExam,minOfTheExam);

            }
        } else {
            System.out.println("Late");
            if (totalMinutesArriving - totalMinutes < 60){
                System.out.printf("%d minutes after the start", totalMinutesArriving - totalMinutes);
            } else {
                hourOfTheExam = (totalMinutesArriving - totalMinutes) / 60;
                minOfTheExam = (totalMinutesArriving - totalMinutes) % 60;
                System.out.printf("%d:%02d hours after the start",hourOfTheExam, minOfTheExam);

            }
        }
    }
}
