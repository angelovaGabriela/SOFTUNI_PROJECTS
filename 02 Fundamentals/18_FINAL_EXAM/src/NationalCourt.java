import java.util.Scanner;

public class NationalCourt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        // 3 lines of number people each worker can help per hour
        // 1 line of total people count
        // every 4th hour ALL the workers have 1 hour break
        // How much time it will take to help all the people "Time needed: {time}h."


        int worker1 = Integer.parseInt(scanner.nextLine());
        int worker2 = Integer.parseInt(scanner.nextLine());
        int worker3 = Integer.parseInt(scanner.nextLine());

        int countOfPeople = Integer.parseInt(scanner.nextLine());


        int timeNeeded = 0;
        int totalTime = 0;


        int allWorkers = worker1 + worker2 + worker3;

        for (int i = 1; i <= countOfPeople; i++) {


            int peopleLeftAfterHour = countOfPeople - allWorkers;



                if (peopleLeftAfterHour > 0) {

                    timeNeeded += 1;
                }

                if (timeNeeded == 3) {

                    timeNeeded = 0;
                    totalTime += 4;//3 + 1 hour break

                }

            countOfPeople = peopleLeftAfterHour;


        }

        if (countOfPeople <= allWorkers) {
            timeNeeded += 1;
        }

            totalTime += timeNeeded;
            System.out.println("Time needed: " + totalTime + "h.");





    }


}