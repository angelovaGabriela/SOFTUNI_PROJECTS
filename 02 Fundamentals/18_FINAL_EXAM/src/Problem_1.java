import java.util.Scanner;

public class Problem_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();
        String input = scanner.nextLine();


        while (!input.equals("Done")){
            String[] word = input.split(" ");
            String command = input.split(" ")[0];

            switch (command){
                case "Change":
                    String chars = word[1];
                    String replacement = word[2];

                    System.out.println(text.replace(chars,replacement));

                    break;
                case "Includes":
                    String otherString = word[1];
                    if(text.contains(otherString)){
                        System.out.println("True");
                    } else {
                        System.out.println("False");
                    }

                    break;
                case "End":
                    String otherStr = word[1];
                    boolean result = text.endsWith(otherStr);

                    if(result){
                        System.out.println("True");
                    }else {
                        System.out.println("False");
                    }

                    break;
                case "Uppercase":
                    String newText = text.toUpperCase();
                    System.out.println(newText);
                    break;
                case "FindIndex":
                    String chary = word[1];
                    int takeFirst = text.indexOf(chary);
                    System.out.println(takeFirst);

                    break;
                case "Cut":
                    int startIndex = Integer.parseInt(word[1]);
                    int endIndex = Integer.parseInt(word[2]);

                    if(startIndex >= 0 && startIndex <= text.length()-1 && endIndex >= 0 && endIndex <= text.length()-1) {
                        String newWord = text.substring(startIndex, endIndex);
                        if(!newWord.equals("")){
                            System.out.println(newWord);
                        } else {
                            System.out.println();
                        }
                    }

                    break;
            }


            input = scanner.nextLine();
        }
    }
}
