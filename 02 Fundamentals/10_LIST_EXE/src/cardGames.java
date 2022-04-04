import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class cardGames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> firstDeck = Arrays
                .stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());


        List<Integer> secondDeck = Arrays
                .stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int sumPlayer1 = 0;
        int sumPlayer2 = 0;

        for (int i = 0; i < firstDeck.size(); i++) {
            for (int j = 0; j < secondDeck.size(); j++) {

                int currentNum1 = firstDeck.get(i);
                int currentNum2 = secondDeck.get(j);

                if (currentNum1 > currentNum2) {
                    firstDeck.add(currentNum1);
                    firstDeck.add(currentNum2);

                    secondDeck.remove(0); //махам картата от първия индекс
                    firstDeck.remove(0);

                    j--;//за да започвам всеки път от 0 (след всяка итерация манам индекс 0 и започвам от следващия)

                    if (firstDeck.size() == 0 || secondDeck.size() == 0) {
                        break;//ако свършат картите играта приключва
                    }

                } else if (currentNum2 > currentNum1) {
                    secondDeck.add(currentNum1);
                    secondDeck.add(currentNum2);


                    firstDeck.remove(0);
                    secondDeck.remove(0);

                    j--;// намалявам броя на итерациите

                    if (firstDeck.size() == 0 || secondDeck.size() == 0) {
                        break;
                    }
                } else {
                    firstDeck.remove(0);
                    secondDeck.remove(0);

                    j--;

                    if (secondDeck.size() == 0 || firstDeck.size() == 0) {
                        break;
                    }
                }

            }
        }

        if (firstDeck.size() > secondDeck.size()) {
            for (int card : firstDeck) {
                sumPlayer1 += card;
            }
            System.out.printf("First player wins! Sum: %d", sumPlayer1);

        } else if (secondDeck.size() > firstDeck.size()) {
                for (int card : secondDeck) {
                    sumPlayer2 += card;
                }
                System.out.printf("Second player wins! Sum: %d", sumPlayer2);
        }
    }
    }

