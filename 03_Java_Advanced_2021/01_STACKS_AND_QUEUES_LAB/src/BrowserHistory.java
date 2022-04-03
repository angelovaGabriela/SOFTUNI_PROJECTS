import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Scanner;

public class BrowserHistory {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<String> history = new ArrayDeque<>();

        String navigation = scanner.nextLine();
        String current = null;

        while (!navigation.equals("Home")){
            boolean hasNoPrevious = false;

            if(navigation.equals("back")){
                if (!history.isEmpty()){
                    current = history.pop();
                } else {
                    System.out.println("no previous URLs");
                hasNoPrevious = true;
                }
            } else {
                if (current != null) {
                    history.push(current);
                }
                current = navigation;
            }
            if (!hasNoPrevious) {
                System.out.println(current);
            }
            navigation = scanner.nextLine();
        }
    }
}
