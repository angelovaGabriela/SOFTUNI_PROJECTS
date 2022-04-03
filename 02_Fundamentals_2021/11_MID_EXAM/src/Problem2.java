import java.util.Arrays;
import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int waitingPeople = Integer.parseInt(scanner.nextLine());
        int[] peopleInside = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        //не знаем, колко кабинки има лифта
        //на всеки вагон може да има по 4 човека

        int freeSpace = 0;
        for (int i = 0; i < peopleInside.length; i++) { // от първата до последната кабинка
//влизат хора
            int peopleInCabin = peopleInside[i];//хората в сегашната кабина
            freeSpace = 4 - peopleInCabin; // свободни места в кабинката

            if (peopleInCabin == 4) {
                continue;
            }
            if (freeSpace >= waitingPeople) {
                peopleInside[i] = peopleInside[i] + waitingPeople;
                waitingPeople = 0;
                break;
            }
            peopleInside[i] = 4;
            waitingPeople = waitingPeople - freeSpace;

        }
        boolean hasEmptySeats = false;
        for (int peopleInCabin:peopleInside) {
            if (peopleInCabin < 4){
                hasEmptySeats = true;
                break;
        }
        }
        if (hasEmptySeats) {
            System.out.println("The lift has empty spots!");

        } else if (!hasEmptySeats && waitingPeople != 0){
            System.out.printf("There isn't enough space! %d people in a queue!%n", waitingPeople);
        }
       // Arrays.stream(peopleInside).forEach(e-> System.out.print(e + " "));
        for (int liftCabin : peopleInside)
        {
            System.out.print(liftCabin + " "); // състоянието на лифта, брой хора във всяка кабинка
            
        }
    }
}
