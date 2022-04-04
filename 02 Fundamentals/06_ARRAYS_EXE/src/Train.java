import java.util.Scanner;

public class Train {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int [] peopleInWagons = new int[n];
        int sumPeople = 0;
        for (int index = 0; index <= peopleInWagons.length-1 ; index++) {
           peopleInWagons [index] = Integer.parseInt(scanner.nextLine());// индекс = стойността, която прочитам от конзолата
        }
        for (int countPeople :peopleInWagons) {//за всеки един елемент в този масив
            System.out.print(countPeople + " ");
            sumPeople += countPeople;
        }
        System.out.println();
        System.out.println(sumPeople);
    }
}
