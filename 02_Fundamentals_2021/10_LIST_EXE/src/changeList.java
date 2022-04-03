import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class changeList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> elements = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        String command = scanner.nextLine();


        //•	Delete {element} - ["Delete", "{element}"]
        //•	Insert {element} {position} - ["Insert", "{element}", "{position}"]
        while (!command.equals("end")) {
            String[] commandData = command.split("\\s+");
            String commandName = commandData[0];


            switch (commandName) {
                case "Delete":
                    int element = Integer.parseInt(commandData[1]);

                    elements.removeAll(Collections.singletonList(element));

                    break;
                case "Insert":
                    int element2 = Integer.parseInt(commandData[1]);
                    int insertIndex = Integer.parseInt(commandData[2]);


                        elements.add(insertIndex, element2);


                    break;
            }

            command = scanner.nextLine();

        }
        for (int element : elements) {
            System.out.print(element + " ");
        }

    }



}

