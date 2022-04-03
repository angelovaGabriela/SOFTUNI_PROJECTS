import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.stream.Collectors;

public class VOINA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LinkedHashSet<Integer> firstDeck = Arrays
                .stream(scanner.nextLine().split("\\s+"))
                .map(Integer :: parseInt)
                .collect(Collectors.toCollection(LinkedHashSet :: new));

        LinkedHashSet<Integer> secondDeck = Arrays
                .stream(scanner.nextLine().split("\\s+"))
                .map(Integer :: parseInt)
                .collect(Collectors.toCollection(LinkedHashSet :: new));

        int rounds = 50;


        while (rounds > 0 && !firstDeck.isEmpty() && !secondDeck.isEmpty()){

            Iterator<Integer> firstIterator = firstDeck.iterator();
            Iterator<Integer> secondIterator = secondDeck.iterator();

            int firstCard = firstIterator.next();
            firstIterator.remove();
            int secondCard = secondIterator.next();
            secondIterator.remove();




            if (firstCard > secondCard){
                firstDeck.add(firstCard);
                firstDeck.add(secondCard);
            } else if (secondCard > firstCard){
                secondDeck.add(firstCard);
                secondDeck.add(secondCard);
            }
            rounds--;
        }

        if (firstDeck.size() > secondDeck.size()){
            System.out.println("First player win!");
        } else if(secondDeck.size() > firstDeck.size()){
            System.out.println("Second player win!");
        } else {
            System.out.println("Draw!");
        }
    }
}
