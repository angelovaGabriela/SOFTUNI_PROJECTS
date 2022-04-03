import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class houseParty {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int countCommands = Integer.parseInt(scanner.nextLine());
        List <String> people = new ArrayList<>();// празен лист, към който ще добавям


        //["{name}, "is", "going!"]
        //["{name}, "is", "not", "going!"]

        for (int i = 1; i <= countCommands; i++) { //от 1 защото започваме от първия човек, няма как да имаме нулев човек. Обхождаме ги от първия гост
            String input = scanner.nextLine();
            //сплитвам си командите на масив с отделни индекси за да мога да ги достъпя
            String[]array = input.split("\\s+");
            //["{name}, "is", "going!"]
            //["{name}, "is", "not", "going!"]

            String name = array[0];

            if (array[2].equals("going!")){
                if(people.contains(name)){
                    System.out.printf("%s is already in the list!%n", name);
                } else {
                    people.add(name);
                }
            } else if (array[2].equals("not")){
                if (people.contains(name)){
                    people.remove(name);
                } else {
                    System.out.printf("%s is not in the list!%n", name);
                }
            }
            

        }

            printList(people);
    }

    private static void printList(List<String> people) {
        for (String name : people) {
            System.out.println(name);

        }
    }
}
