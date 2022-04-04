import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;

public class BasicQueueOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt(); // number of integers in the Queue .offer
        int s = scanner.nextInt(); // numbers of elements in the deque .poll
        int x = scanner.nextInt(); // if it is present in the deque print @true if not print the smallest element

        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (int count = 1; count <= n; count++) {
            queue.offer(scanner.nextInt());

        }

        for (int index = 1; index <= s; index++) {
            queue.poll();

        }

        if (queue.contains(x)) {
            System.out.println("true");
        } else {
            if (!queue.isEmpty()) {
                System.out.println(Collections.min(queue));
            } else {
                System.out.println(0);
            }
        }
    }
}


