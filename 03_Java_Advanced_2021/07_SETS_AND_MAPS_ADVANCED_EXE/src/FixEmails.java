import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class FixEmails {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String name = scanner.nextLine();

        // Map<name, email>
        Map<String, String> emailsData = new LinkedHashMap<>();
        while (!name.equals("stop")){
          String email = scanner.nextLine();
          //обавям имейли само ако не завършват на us ul com
            if (!email.endsWith("us") && !email.endsWith("uk") && !email.endsWith("ul") && !email.endsWith("com")){
                emailsData.put(name, email);
            }

            name = scanner.nextLine();
        }
        emailsData.entrySet().forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));


    }
}
