import java.util.ArrayDeque;

import java.util.Scanner;

public class HotPotato {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] names = scanner.nextLine().split("\\s+");
        int n = scanner.nextInt();

        ArrayDeque <String> queue = new ArrayDeque<>();
        for (int i = 0; i <= names.length - 1; i++) {
            String currentName = names[i];
            queue.offer(currentName);

        }

        while(queue.size() > 1){
            for (int count = 1; count < n; count++) {
                queue.offer(queue.poll());//последния премахнат става първи
            }

            System.out.println("Removed " + queue.poll());
        }

            System.out.println("Last is " + queue.poll());


        }
    }

