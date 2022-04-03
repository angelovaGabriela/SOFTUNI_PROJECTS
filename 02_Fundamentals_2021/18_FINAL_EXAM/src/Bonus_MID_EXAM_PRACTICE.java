import java.util.Scanner;

public class Bonus_MID_EXAM_PRACTICE {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//{total bonus} =
// {student attendances} / {course lectures}
// * (5 + {additional bonus})

        int countOfStudents = Integer.parseInt(scanner.nextLine());
        int countOfLectures = Integer.parseInt(scanner.nextLine());
        int bonus = Integer.parseInt(scanner.nextLine());

        double totalBonus = 0;

        double bestStudent = Double.MIN_VALUE;
        int bestStudentAttendances = Integer.MIN_VALUE;


        for (int i = 1; i <= countOfStudents ; i++) {
            int attendance = Integer.parseInt(scanner.nextLine());
            totalBonus = (attendance * 1.0)/ (countOfLectures * 1.0) * 1.0 * (5 + bonus);

           if(bestStudent < totalBonus){
               bestStudent = totalBonus;
               bestStudentAttendances = attendance;
           }

        }
        System.out.printf("Max Bonus: %.0f.%n", Math.ceil(bestStudent));

        if(bestStudentAttendances == 1){
            System.out.printf("The student has attended %d lecture.", bestStudentAttendances);
        } else {
            System.out.printf("The student has attended %d lectures.", bestStudentAttendances);
        }

    }


}
