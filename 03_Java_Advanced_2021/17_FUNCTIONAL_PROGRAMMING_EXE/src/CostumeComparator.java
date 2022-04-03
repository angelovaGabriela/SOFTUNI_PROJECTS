import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CostumeComparator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer :: parseInt).collect(Collectors.toList());
       //компаратора - сравнява числата в листа. След всяко едно от сравненията дава стойности ако = 0, не им разменя местата
       //той е функция, която приема два елемента и връща цяло число (1, 0, -1)
       //взима винаги числата по двойки


  Comparator<Integer> comparator = ((first, second) -> {
     if (first % 2 == 0 && second % 2 != 0 ) {
         return -1; // не искам да ги разменям

     } else if (first % 2 != 0 && second % 2 == 0) {
         return 1; // искам да ги размени
     } else { // две четни,две нечетни, компаратора ги сравнява и решава сам какво да ги прави - 1>2 = 1 - разменя 1 < 2 = -1 - не разменя, 1==2 = няма размяна
         return first.compareTo(second);

     }
  });


        numbers.stream().sorted(comparator).forEach(number -> System.out.print(number + " "));
    }
}
