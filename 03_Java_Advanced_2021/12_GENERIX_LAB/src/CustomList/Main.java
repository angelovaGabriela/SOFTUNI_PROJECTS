package CustomList;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
CustomList <String> customList = new CustomList<String>();

        while (!input.equals("END")){
            String[] commandParts = input.split(" ");
            String commandName = commandParts[0];

            switch (commandName){
                case "Add":

                    String elementToAdd = commandParts[1];
                    customList.add(elementToAdd);
                    break;
                case"Remove":
                    int index = Integer.parseInt(commandParts[1]);
                    customList.remove(index);
                    break;
                case "Contains":
                    String elementToCheck = commandParts[1];
                    System.out.println(customList.contains(elementToCheck));
                    break;
                case "Swap":
                    int firstIndex = Integer.parseInt(commandParts[1]);
                    int secondIndex = Integer.parseInt(commandParts[2]);
                    customList.swap(firstIndex, secondIndex);
                    break;
                case "Greater":
                    String element = commandParts[1];
                    System.out.println(customList.countGreaterThan(element));
                    break;
                case "Max":
                    System.out.println(customList.getMax());
                    break;
                case "Min":
                    System.out.println(customList.getMin());
                    break;
                case "Sort":
                    Sorter.sort(customList);
                    break;
                case "Print":
                    System.out.print(customList);
                    break;


            }

            input = scanner.nextLine();

        }
    }
}
