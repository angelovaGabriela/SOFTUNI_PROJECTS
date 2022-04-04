import java.util.Scanner;

public class ExamPreparation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberBadMarks = Integer.parseInt(scanner.nextLine());



        int failedTimes = 0;
        int counterSolvedExe = 0;
        double sumMarks = 0;
        String lastTask = "";
        boolean isFailed = true;

        while ( failedTimes < numberBadMarks){
            String nameTask = scanner.nextLine();
            if(nameTask.equals("Enough")){
                isFailed = false;
                break;
            } int mark = Integer.parseInt(scanner.nextLine());
            if (mark <= 4){
                failedTimes++;
            } sumMarks += mark;
            counterSolvedExe++;
            lastTask = nameTask;

        } if (isFailed){
            System.out.printf("You need a break, %d poor grades.", numberBadMarks);

        } else {
            System.out.printf("Average score: %.2f%n", sumMarks/ counterSolvedExe);
            System.out.printf("Number of problems: %d%n", counterSolvedExe);
            System.out.printf("Last problem: %s", lastTask);
        }
    }
}
