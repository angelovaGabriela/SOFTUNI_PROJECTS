
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Problem2_MID_EXAM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> sequence = Arrays
                .stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        String input = scanner.nextLine();

        while (!input.equals("END")) {
            String command = input.split(" ")[0];

            switch (command) {
                case "add":
                    if (input.split(" ")[1].equals("to") && input.split(" ")[2].equals("start")) {
                    String indexStart = input.split(" ")[3];


                        String[] numbersEnd = input.substring(3).split(" ");
                        String numbers2 = Arrays.toString(numbersEnd);
                        sequence.add(sequence.size() - 1, Integer.parseInt(numbers2));

                    }
                    break;
                case "remove":
                    if (input.split(" ")[1].equals("lower")) {
                        int value = Integer.parseInt(input.split(" ")[3]);
                        if (sequence.contains(value)) {
                            for (int element : sequence) {
                                if (element < value) {
                                    sequence.remove(element);
                                }
                            }
                        }

                    }  if (input.split(" ")[1].equals("greater")) {

                        int value2 = Integer.parseInt(input.split(" ")[3]);
                        if (sequence.contains(value2)) {
                            for (int element : sequence) {
                                if (element > value2) {
                                    sequence.remove(element);
                                }
                            }
                        }

                    }  if (input.split(" ")[1].equals("at")) {
                        int index = Integer.parseInt(input.split(" ")[3]);
                        if (index >= 0 && index <= sequence.size() - 1) {
                            sequence.remove(index);
                        } else {
                            continue;
                        }


                    }  if (input.split(" ")[1].equals("count")) {

                        int count = Integer.parseInt(input.split(" ")[2]);
                        if (sequence.size() > count) {

                            for (int i = sequence.size() - 1; i >= count; i--) {
                                sequence.remove(i);

                                if (sequence.size() == 0) {
                                    break;
                                }
                            }
                        }
                    }

                    break;
                case "replace":
                    int replace = Integer.parseInt(input.split(" ")[1]);
                    int replacement = Integer.parseInt(input.split(" ")[2]);

                    if (sequence.contains(replace)){

                        sequence.set(replace, replacement);

                    } else {
                        continue;
                    }

                    break;
                case "find":
                    if (input.split(" ")[1].equals("even")){
                        for (int number: sequence) {
                            if (number % 2 == 0){
                                System.out.println(number + " ");
                            }
                        }
                    } else if (input.split(" ")[1].equals("odd")) {
                        for (int number1:sequence) {
                            if (number1 % 2 != 0){
                                System.out.println(number1 + " ");
                            }
                        }
                    }
                    break;
            }


            input = scanner.nextLine();
        }

        String string = sequence.toString();
        System.out.println(string.substring(1, string.length()-1));
    }
}



        //  String string = items.toString();
        //  System.out.println(string.substring(1, string.length()-1));

