
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Inventory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> items = Arrays.stream(scanner.nextLine().split(", ")).collect(Collectors.toList());

   String commands = scanner.nextLine();

   while (!commands.equals("Craft!")){
       String tokens = commands.split(" - ")[0];


       switch (tokens){
           case "Collect":
               String element = commands.split(" - ")[1];
               if (!items.contains(element)){
                   items.add(element);
               }
               break;
           case "Drop":
               String element2 = commands.split(" - ")[1];

        if (!items.contains(element2)) {
            break;
        } else {
        items.remove(element2);
}



               break;
           case "Combine Items":
               String[] combine = commands.split(" - ");
               String elements = combine[1];
               String oldItem = elements.split(":")[0];
               String newElement = elements.split(":")[1];

               if(items.contains(oldItem)){
                 int index =  items.indexOf(oldItem) + 1;
                   items.add(index, newElement);
               }



               break;
           case "Renew":
               String renew = commands.split(" - ")[1];

               if(items.contains(renew)){
                   items.remove(renew);
                   items.add(renew);
               }
               break;
       }


       commands = scanner.nextLine();
   }



            String string = items.toString();
            System.out.println(string.substring(1, string.length()-1));




    }
}
