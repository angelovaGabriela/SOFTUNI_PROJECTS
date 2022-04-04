import java.util.Scanner;

public class WarriorQuest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String skill = scanner.nextLine();
        String commands = scanner.nextLine();

        while (!commands.equals("For Azeroth")){
           String[] commandParts = commands.split(" ");
           String move = commandParts[0];

           switch (move){
               case "GladiatorStance":
                   skill = skill.toUpperCase();
                   System.out.println(skill);
                   break;
               case "DefensiveStance":
                   skill = skill.toLowerCase();
                   System.out.println(skill);
                   break;
               case "Dispel":
                   int index = Integer.parseInt(commandParts[1]);
                   String latter = commandParts[2];

                   if (skill.length() >= index){
                      char replacement = latter.charAt(0);
                      char toReplace = skill.charAt(index);

                      skill = skill.replaceFirst(String.valueOf(toReplace), String.valueOf(replacement));
                       System.out.println("Success!");

                   } else {
                       System.out.println("Dispel too weak.");
                   }

                   break;
               case "Target":
                   switch (commandParts[1]) {
                       case "Change":
                        String firstSubstring = commandParts [2];
                        String secondSubstring = commandParts[3];

                        if (skill.contains(firstSubstring)){
                            skill = skill.replace(firstSubstring, secondSubstring);
                            System.out.println(skill);
                        } else {
                            System.out.println(skill);
                        }
                           break;
                       default:
                           String substring = commandParts[2];
                           if (skill.contains(substring)){
                               skill = skill.replace(substring, "");
                               System.out.println(skill);
                           }



                          continue;

                   }
                   break;
               default:
                   System.out.println("Command doesn't exist!");
                   break;

           }


            commands = scanner.nextLine();
        }

    }

}
