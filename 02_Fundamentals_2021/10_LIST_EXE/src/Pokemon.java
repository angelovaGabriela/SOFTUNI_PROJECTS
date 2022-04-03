import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Pokemon {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> distance = Arrays
                .stream(scanner.nextLine().split(" "))
                .map(Integer :: parseInt)
                .collect(Collectors.toList());

        int sumOfRemoved = 0;
       // защото няма как да е минус 1 и ще търпи промяна

        while (distance.size() > 0) {

            int index = Integer.parseInt(scanner.nextLine());
            int removedElement = -1;

            if (index < 0) {
                removedElement = distance.remove(0);
                sumOfRemoved += removedElement;
                int lastElement = distance.get(distance.size() - 1);
                distance.add(0, lastElement);


            } else if (index >= distance.size()) {
                removedElement = distance.remove(distance.size() - 1);
                sumOfRemoved += removedElement;
                int firstElement = distance.get(0);
                distance.add(firstElement);
            } else {
                removedElement = distance.remove(index);
                sumOfRemoved += removedElement;
            }


            for (int i = 0; i < distance.size(); i++) {
                if (distance.get(i) <= removedElement){
                    distance.set(i, distance.get(i)+ removedElement);//добавям към стойността на индекса

                } else {
                    distance.set(i, distance.get(i) - removedElement);
                }
            }
        }
        System.out.println(sumOfRemoved);

        }

    }


