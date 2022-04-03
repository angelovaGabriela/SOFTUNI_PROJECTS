import java.util.Scanner;

public class Moving {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int wideFree = Integer.parseInt(scanner.nextLine());
        int lengthFree = Integer.parseInt(scanner.nextLine());
        int heightFree = Integer.parseInt(scanner.nextLine());

        int totalBox = 0;
        int freeSpace = wideFree * lengthFree * heightFree;
        String boxCounter = scanner.nextLine();


        while (!boxCounter.equals("Done")) { // винаги думичката от горе
            int allBoxes = Integer.parseInt((boxCounter));// allBoxes - променлива, в която преобразувам String boxCounter в int
            totalBox += allBoxes;// запазвам броя на кашоните totalBox = totalBox + allBoxes

            if (freeSpace < totalBox) { // ако мястото приключи цикъла спира и излиза
                break;
            }
            boxCounter = scanner.nextLine(); // ако не, отново въвежда String, който после се преобеазува в int
        }
        if (freeSpace < totalBox) { // условието, при което е спрял цикъла
            System.out.printf("No more free space! You need %d Cubic meters more.", totalBox - freeSpace);
        } else { // в противен случай, например команда "Done".
            System.out.printf("%d Cubic meters left.", freeSpace - totalBox);

        }
    }
    }


