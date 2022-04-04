import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PrinterQueue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<String> queue = new ArrayDeque<>();

        String task = scanner.nextLine();

        while (!task.equals("print")) {

            if (task.equals("cancel")) {
                if (queue.isEmpty()) {
                    System.out.println("Printer is on standby");
                } else {
                    String cancelTask = queue.poll();
                    System.out.println("Canceled " + cancelTask);
                }
            } else {
                queue.offer(task);

            }
            task = scanner.nextLine();
        }

        System.out.println(queue.stream().map(String::valueOf)
                .collect(Collectors.joining(System.lineSeparator())));
    }

    }
