import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class BattleManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();

        Map<String,PersonData> people = new LinkedHashMap<>();

        while (!command.equals("Results")) {
           String[] commandParts = command.split(":");
           String commandName = commandParts[0];

           switch (commandName) {
               case "Add":
                   String personName = commandParts[1];
                   int health = Integer.parseInt(commandParts[2]);
                   int energy = Integer.parseInt(commandParts[3]);

                   if (!people.containsKey(personName)) {
                       PersonData person = new PersonData(health, energy);
                       people.put(personName, person);
                   } else {
                       people.get(personName).increaseHealth(health);
                   }
                   break;
               case "Attack":
                   String attackerName = commandParts[1];
                   String defenderName = commandParts[2];
                   int damage = Integer.parseInt(commandParts[3]);

                   if (people.containsKey(attackerName) && people.containsKey(defenderName)) {
                       people.get(defenderName).reduceHealth(damage);
                       people.get(attackerName).reduceEnergy();

                       if (people.get(defenderName).getHealth() <= 0) {
                           System.out.printf("%s was disqualified!%n", defenderName);
                           people.remove(defenderName);
                       }
                       if (people.get(attackerName).getEnergy() <= 0) {
                           System.out.printf("%s was disqualified!%n", attackerName);
                           people.remove(attackerName);
                       }
                   }


                   break;
               case "Delete":
                   String userName = commandParts[1];
                   if (userName.equals("All")){
                       people.clear();
                   } else {
                       people.remove(userName);
                   }
                   break;
           }

           command = scanner.nextLine();
        }

        System.out.printf("People count: %d%n", people.size());
        people.entrySet().forEach(e -> System.out.printf(
                "%s - %d - %d%n", e.getKey(), e.getValue().getHealth(), e.getValue().getEnergy()));


    }
}
class PersonData {
    private int health;
    private int energy;

    public PersonData(int health, int energy) {
        this.health = health;
        this.energy = energy;
    }

    public void increaseHealth(int health) {
        this.health += health;
    }


    public void reduceHealth(int damage) {
        this.health -= damage;
    }

    public void reduceEnergy() {
        this.energy -= 1;
    }

    public int getHealth() {
        return health;
    }

    public int getEnergy() {
        return energy;
    }
}