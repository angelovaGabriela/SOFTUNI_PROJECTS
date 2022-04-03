import java.util.Scanner;

public class Graduation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String nameStudent = scanner.nextLine();
        int lowScore = 0;//променлива, в която пазя колко пъти ще получи ниска оценка
        double sumGrades = 0.00;//променлива, която сумира всички годишни оценки
        int grades = 0; // променлива за класът, от който започва обучението


        while (grades < 12) {
            double nextGrade = Double.parseDouble(scanner.nextLine());

            if (nextGrade >= 4) {
                sumGrades += nextGrade;
            } else {
                lowScore++;
                if (lowScore > 1) {
                    break;
                }
            }
            grades++;
        }
        double average = sumGrades / grades;
        if (lowScore > 1){
            System.out.printf("%s has been excluded at %d grade", nameStudent, grades);
        } else {
            System.out.printf("%s graduated. Average grade: %.2f", nameStudent, average);
        }
    }

}




