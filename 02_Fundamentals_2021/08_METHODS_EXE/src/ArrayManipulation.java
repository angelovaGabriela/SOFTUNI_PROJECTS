import java.util.Arrays;
import java.util.Scanner;

public class ArrayManipulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] lineOfInts = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        String command = scanner.nextLine();
        while (!command.equals("end")) {
            //•	exchange {index} - void
            if (command.equals("exchange" + lineOfInts[Integer.parseInt(scanner.nextLine())])) {
                String[] input = scanner.nextLine().split(" "); // всеки масив 'команда' го сплитвам по интервал
                String typeCommands = input[0]; // типът команда ми се намира на първа позиция или индекс "0"
                // •exchange
                //•	max even/odd–return
                //•	min even/odd –return
                //•	first {count} even/odd - return
                //•	last {count} even/odd - return
                switch (typeCommands) {
                    case "exchange":
                        int exchangeIndex = Integer.parseInt(input[1]);
                        if (isValidIndex(exchangeIndex, lineOfInts.length)) {
                            lineOfInts = exchange(lineOfInts, exchangeIndex);
                        } else {
                            System.out.println("Invalid index");
                        }

                        break;
                    case "max":
                        String evenOrOdd = input[1];
                        if (evenOrOdd.equals("even")) {
                            printIndexMaxEvenNumber(lineOfInts);
                        } else if (evenOrOdd.equals("odd")) {
                            printIndexMaxOddNumber(lineOfInts);

                        }

                        break;
                    case "min":
                        String minEvenOrOdd = input[1];
                        if (minEvenOrOdd.equals("even")) {
                            printIndexMinEvenNumber(lineOfInts);
                        } else if (minEvenOrOdd.equals("odd")) {
                            printIndexMinOddNumber(lineOfInts);
                        }
                        break;
                    case "first":
                        int count = Integer.parseInt(input[1]);
                        if (!isValidCount(count, lineOfInts.length)) {
                            System.out.println("Invalid count");
                            command = scanner.nextLine();
                            continue;
                        }
                        String firstEvenOrOdd = input[2];
                        if (firstEvenOrOdd.equals("even")) {
                            printFirstEven(lineOfInts, count);
                        } else if (firstEvenOrOdd.equals("odd")) {
                            printFirstOdd(lineOfInts, count);
                        }
                        break;
                    case "last":
                        int countLast = Integer.parseInt(input[1]);
                        if (!isValidCount(countLast, lineOfInts.length)) {
                            System.out.println("Invalid count");
                            command = scanner.nextLine();
                            continue;
                        }
                        String lastEvenOdd = input[2];
                        if (lastEvenOdd.equals("even")) {
                            printLastEven(lineOfInts, countLast);
                        } else if (lastEvenOdd.equals("odd")) {
                            printLastOdd(lineOfInts, countLast);
                        }
                        break;
                }
                command = scanner.nextLine();
            }
        }
        System.out.print("[");
        for (int index = 0; index <= lineOfInts.length - 1; index++) {
            if (index == lineOfInts.length - 1) {
                System.out.print(lineOfInts[index]);
            } else {
                System.out.print(lineOfInts[index] + ", ");
            }
        }
        System.out.print("]");
    }

    private static boolean isValidCount(int count, int length) {
        return count <= length;
    }

    private static void printLastOdd(int[] lineOfInts, int countLast) {

        StringBuilder lastOdd = new StringBuilder();
        for (int index = lineOfInts.length-1; index >=0 ; index--) {
            int currentNumber = lineOfInts[index];
            if (currentNumber % 2 == 1){
                lastOdd.append(currentNumber);
                countLast--;
            }
            if(countLast==0){
                break;
            }
        }
        System.out.println("[");
        for (int index = lastOdd.length()-1; index >=0 ; index--) {
            if(index == 0){
                System.out.println(lastOdd.charAt(index));
            } else {
                System.out.println(lastOdd.charAt(index) + ", ");
            }

        }
        System.out.println("]");

    }

    private static void printLastEven(int[] lineOfInts, int countLast) {
    StringBuilder lastEven = new StringBuilder();
        for (int index = lineOfInts.length-1; index >=0 ; index--) {
            int currentNumber = lineOfInts[index];
            if (currentNumber % 2 == 0){
                lastEven.append(currentNumber);
                countLast--;
            }
            if(countLast==0){
                break;
            }
        }
        System.out.print("]");
        for (int index = lastEven.length()-1; index >=0 ; index--) {
           if(index == 0){
               System.out.print(lastEven.charAt(index));
           } else {
               System.out.print(lastEven.charAt(index) + ", ");
           }

        }
        System.out.print("]");

    }

    private static void printFirstOdd(int[] lineOfInts, int count) {
        StringBuilder output = new StringBuilder();
        output.append("[");

        for (int index = 0; index <= lineOfInts.length-1 ; index++) {
            int currentNumber = lineOfInts[index];

            if (currentNumber % 2 == 1){
                output.append(currentNumber);// сложи го в String Builder след отворената скоба
               if(count != 1){
                   output.append(", ");// добавям запетайка и интервал
               }
                count--; // намалявам броя на числата, които трябва да се отпечатат с 1 след като съм взела числото
            }
            if (count == 0){

                break;//ако каунт е 0 излизам от цикъла
            }

        }
        output.append("]");// затварям StringBuilder
        System.out.println(output.toString());

    }

    private static void printFirstEven(int[] lineOfInts, int count) {
    StringBuilder output = new StringBuilder();
    output.append("[");

        for (int index = 0; index <= lineOfInts.length-1 ; index++) {
           int currentNumber = lineOfInts[index];

           if (currentNumber % 2 == 0){
               output.append(currentNumber);// сложи го в String Builder след отворената скоба
               if(count != 1){
                   output.append(", ");// добавям запетайка и интервал
               }

               count--; // намалявам броя на числата, които трябва да се отпечатат с 1 след като съм взела числото
           }
           if (count == 0){
               break;//ако каунт е 0 излизам от цикъла
           }

        }
        output.append("]");// затварям StringBuilder
        System.out.println(output.toString());// Print StringBuilder като текст .toString()
    }

    private static void printIndexMinOddNumber(int[] lineOfInts) {
        int minOdd = Integer.MAX_VALUE;
        int indexMinOdd = -1;
        for (int index = 0; index <= lineOfInts.length-1; index++) {
            int currentNumber = lineOfInts[index];
            if (currentNumber % 2 == 1 && currentNumber <= minOdd){
                minOdd = currentNumber;
                indexMinOdd = index;
            }
        } if(indexMinOdd == -1){
            System.out.println("No matches");
        } else {
            System.out.println(indexMinOdd);
        }

    }

    private static void printIndexMinEvenNumber(int[] lineOfInts) {
    int minEven = Integer.MAX_VALUE;
    int indexMinEven = -1;
        for (int index = 0; index <= lineOfInts.length-1; index++) {
        int currentNumber = lineOfInts[index];
        if (currentNumber % 2 == 0 && currentNumber <= minEven){
         minEven = currentNumber;
        indexMinEven = index;
        }
        } if(indexMinEven == -1){
            System.out.println("No matches");
        } else {
            System.out.println(indexMinEven);
        }

    }


    private static void printIndexMaxOddNumber(int[] lineOfInts) {
        int maxOdd = Integer.MIN_VALUE;
        int indexMaxOdd = -1;// защото трябва да го сложа а такъм индекс не съществува
        for (int index = 0; index <= lineOfInts.length-1 ; index++) {
            int currentNumber = lineOfInts[index];

            if(currentNumber % 2 ==1 && currentNumber >= maxOdd){// ако currentNumber е нечетно или е по-голямо от maxEven
                maxOdd = currentNumber;//това е макс
                indexMaxOdd = index; // индекса от който съм го взела е индекса на макс нечетното число
            }
        } if(indexMaxOdd == -1){// значи не съм влизала в масива и индекса си е останал съшия нелеп отгоре
            System.out.println("No matches");
        } else {
            System.out.println(indexMaxOdd);
        }
    }


    private static void printIndexMaxEvenNumber(int[] lineOfInts) {
        int maxEven = Integer.MIN_VALUE;
        int indexMaxEven = -1;// защото трябва да го сложа а такъм индекс не съществува
        for (int index = 0; index <= lineOfInts.length-1 ; index++) {
            int currentNumber = lineOfInts[index];

            if(currentNumber % 2 ==0 && currentNumber >= maxEven){// ако currentNumber е четно или е по-голямо от maxEven
                maxEven = currentNumber;//това е макс
                indexMaxEven = index; // индекса от който съм го взела е индекса на макс четното число
            }
        } if(indexMaxEven == -1){// значи не съм влизала в масива и индекса си е останал съшия нелеп отгоре
            System.out.println("No matches");
        } else {
            System.out.println(indexMaxEven);
        }
    }

    private static int [] exchange(int[] lineOfInts, int exchangeIndex) {
        int [] numbersToExchangeIndex = new int [exchangeIndex + 1];// взимаме числата до дадения индекс. понеже индексите в масива започват от 0 добавям 1
        int [] numbersAfterExchangeIndex = new int [lineOfInts.length-exchangeIndex-1]; // взимаме числата след индекса. = дължината на масива - numbersToExchangeIndex-1

    //обхождам първия масив
        for (int index = 0; index <= exchangeIndex; index++) {// искам всеки път да си взимам числото и да го слагам в новия масив на определен индекс
            int currentElement = lineOfInts[index];
            numbersToExchangeIndex [index] = currentElement; //взимам нещо от индекса на единия масив и го слагам на същия индекс в другия масив
        } // хапълних новия масив с числата от основния масив до exchangeIndex

        // обхождам втория масив и на съответните индекси след exchangeIndex слагам елементите от главния масив lineOfInts
        for (int index = exchangeIndex +1; index <= lineOfInts.length - 1 ; index++) { // обхождам всички индекси от exchangeIndex до края на основния масив, затова е exchangeIndex + 1
       int currentElement = lineOfInts[index];// взимаме елементи от главния масив
       numbersAfterExchangeIndex [index-exchangeIndex - 1] = currentElement;// искам да го сложа на 0 позиция. затова текущия индекс - ексчейндж индекса - 1 и стигам до 0

        }
        // създавам нов масив
        int[] exchangedArray = new int[lineOfInts.length];
        // numbersAfterExchangeIndex + numbersToExchangeIndex
        //обхождам втория масив
        for (int index = 0; index <= numbersAfterExchangeIndex.length-1 ; index++) {
            exchangedArray[index]= numbersAfterExchangeIndex[index];// искам exchangedArray да получи същия елемент на същия индекс както в масива numbersAfterExchangeIndex
        }
        for (int index = numbersAfterExchangeIndex.length; index <= exchangedArray.length ; index++) { // започвам от края на втория масив и продъмжавам до края на exchanged array
        exchangedArray[index] = numbersToExchangeIndex[index-numbersAfterExchangeIndex.length];// искам на първия индекс след numbersAfterExchangeIndex да ми седи число от първия индекс на numbersToExchangeIndex
        }
        return exchangedArray;
        }





    private static boolean isValidIndex(int index, int length) {
        return index >= 0 && index <= length-1;// върни ми дали индекса е валиден

    }


}

