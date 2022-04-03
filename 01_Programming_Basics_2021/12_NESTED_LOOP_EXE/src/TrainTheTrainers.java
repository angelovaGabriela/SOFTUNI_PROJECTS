import java.util.Scanner;

public class TrainTheTrainers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine()); // броя на журито
        String command = scanner.nextLine();

        double allGradesSum = 0;
        int countPresentation = 0; // средно аритметично на оценките

        while(!command.equals("Finish")){
            String presentation = command;// презентации
            countPresentation++;

            double gradeSum = 0.0; // всички оценки от журито
            double avgGrade = 0.0; // средно аритметично на оценките

            for (int i = 0; i < n; i++) { // колкото е журито толкова оценки трябва да прочета
                double grade = Double.parseDouble(scanner.nextLine());// оценка от всеки човек
                gradeSum += grade;
            }
            avgGrade = gradeSum / n; // взимам оценките които съм намерила във фор цикъла и ги деля на броя на журито
            allGradesSum += avgGrade; // към общата сума прибавям средните аритметични оценки

            System.out.printf("%s - %.2f.%n", presentation, avgGrade);

            command = scanner.nextLine();
        }
        System.out.printf("Student's final assessment is %.2f.", allGradesSum / countPresentation);
    }
}
