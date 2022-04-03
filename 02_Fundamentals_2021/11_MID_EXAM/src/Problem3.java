import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Problem3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> elements = Arrays.stream(scanner.nextLine().split(" "))
                .collect(Collectors.toList());// използвам лист защото ще премахвам и добавям елементи в редицата
        String userInput = scanner.nextLine();
        int counter = 0;
        while (!userInput.equals("end")) {
            // два индекса
            counter++;
            int[] userInputArray = Arrays.stream(userInput.split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray(); // прочела съм си двата индекса от конзолата

            int firstIndex = userInputArray[0];
            int secondIndex = userInputArray[1];

            if (userIsCheating(firstIndex, secondIndex, elements)) {
                System.out.println("Invalid input! Adding additional elements to the board");
                String penaltyNumber = String.format("-%da", counter);
                elements.add(elements.size() / 2, penaltyNumber);
                elements.add(elements.size() / 2, penaltyNumber);
                userInput = scanner.nextLine();
                continue;

            }

            String firstNumber = elements.get(firstIndex);
            String secondNumber = elements.get(secondIndex);

            if (firstNumber.equals(secondNumber)) {

                elements.remove(firstNumber);
                elements.remove(secondNumber);
                System.out.printf("Congrats! You have found matching elements - %s!%n", firstNumber);
            } else {
                System.out.println("Try again!");
            }


            if (elements.size() == 0) {
                break;
            }

            userInput = scanner.nextLine();

        }
        if (elements.isEmpty()) {
            System.out.printf("You have won in %d turns!", counter);

        } else {
            System.out.println("Sorry you lose :(");
            System.out.println(String.join(" ", elements));
        }
    }


        private static boolean userIsCheating(int firstIndex, int secondIndex, List<String > elements){
            if (firstIndex == secondIndex) {
                return true;
            }
            if (firstIndex < 0 || firstIndex >= elements.size()) {
                return true;
            }
            if (secondIndex < 0 || secondIndex >= elements.size()) {
                return true;
            }
            return false;
        }
    }
