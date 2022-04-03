import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MuOnline {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        List<String> rooms = Arrays.stream(input.split("\\|")).collect(Collectors.toList());



        int healthCounter = 100;


//initial health = 100
// initial bitcoins = 0

        for (int i = 0; i <= rooms.size()-1; i++) {

            rooms.get(i);
            String command = input.split(" ")[i];



            switch (command) {
                case "potion":
                    int healthAmount = Integer.parseInt(input.split(" ")[i+1]);
                    if (healthCounter < 100){
                        healthCounter += healthAmount;
                    }
                    System.out.printf("You healed for %d hp.%n", healthAmount);
                    System.out.printf("Current health: %d hp.%n", healthCounter);


                    i++;



                    break;
                case "chest":
                    int bitcoinAmount = Integer.parseInt(input.split(" ")[i+1]);
                    System.out.printf("You found %d bitcoins.%n", bitcoinAmount);
                    i++;

                    break;

                default:
                    int monsterAttack = Integer.parseInt(input.split(" ")[i+1]);

                    if(healthCounter >= monsterAttack){
                        healthCounter -= monsterAttack;
                    }
                     if(healthCounter > 0){
                        System.out.printf("You slayed %s.%n", command);
                    }else {
                        System.out.printf("You died! Killed by %s.%n", command);
                        System.out.printf("Best room: %d.%n", i);

                    }



                    i++;
                    break;
                    //facing the monster
            }


        }
    }
}
