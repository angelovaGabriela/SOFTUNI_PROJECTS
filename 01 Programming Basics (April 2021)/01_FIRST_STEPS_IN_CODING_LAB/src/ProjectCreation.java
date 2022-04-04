import java.util.Scanner;

public class ProjectCreation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //1. 1 проект = 3 часа
        //2. The architect <name> will need <hours> hours to complete <numberOfProjects> project/s.
        String name = scanner.nextLine();
        int numberOfProjects = Integer.parseInt(scanner.nextLine());
        System.out.println("The architect " + name + " will need " + 3 * numberOfProjects + " hours to complete " + numberOfProjects + " project/s.");



    }
}
