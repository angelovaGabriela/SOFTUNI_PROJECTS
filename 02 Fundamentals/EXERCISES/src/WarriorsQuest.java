
import java.util.Scanner;

public class WarriorsQuest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        StringBuilder skillString = new StringBuilder(scanner.nextLine()); // fr1c710n

        String input = scanner.nextLine();

        while (!input.equals("For Azeroth")) {
            String[] commandParts = input.split(" ");
            String commandName = commandParts[0];

            switch (commandName) {
                case "GladiatorStance":

                    String replaceToUpperCase = skillString.toString();
                    replaceToUpperCase = replaceToUpperCase.toUpperCase();
                    skillString = new StringBuilder(replaceToUpperCase);

                    System.out.println(skillString);
                    break;
                case "DefensiveStance":
                    String replaceToLowerCase = skillString.toString();
                    replaceToLowerCase = replaceToLowerCase.toLowerCase();
                    skillString = new StringBuilder(replaceToLowerCase);

                    System.out.println(skillString);
                    break;
                case "Dispel":

                    int index = Integer.parseInt(commandParts[1]);
                    String latter = commandParts[2];

                    char latterAsChar = latter.charAt(0);

                    if (index < 0 || index > skillString.length()) {
                        System.out.println("Dispel too weak.");
                    } else {
                        skillString.setCharAt(index, latterAsChar);
                        System.out.println("Success!");
                    }


                    break;
                case "Target":
                    String action = commandParts[1];
                    if (action.equals("Change")) {
                        String firstSubstring = commandParts[2];
                        String secondSubstring = commandParts[3];

                        String toStringSkill = skillString.toString();

                        if (toStringSkill.contains(firstSubstring)) {

                            toStringSkill = toStringSkill.replace(firstSubstring, secondSubstring);
                            skillString = new StringBuilder(toStringSkill);

                            System.out.println(skillString);
                        } else {
                            System.out.println(skillString);
                        }

                    } else if (action.equals("Remove")) {

                        String substring = commandParts[2];
                        String skillAsString = skillString.toString();

                        if (skillAsString.contains(substring)) {

                            int beginIndex = skillAsString.indexOf(substring);
                            int substringLength = substring.length();
                            int endIndex = beginIndex + substringLength;

                                skillString = skillString.delete(beginIndex, endIndex);

                            System.out.println(skillString);
                        }

                    }
                    break;
                default:
                    System.out.println("Command doesn't exist!");
                    break;
            }
            input = scanner.nextLine();
        }
    }
}

//o	"Dispel {index} {letter}"
//o	"Target Change {first substring} {second substring}"
//o	"Target Remove {substring}"