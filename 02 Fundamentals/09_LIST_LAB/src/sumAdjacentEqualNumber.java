import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class sumAdjacentEqualNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //3 3 6 1

        //6 6 1
        //12 1

        List<Double> numberList = new ArrayList<>();
        String[] userInput = scanner.nextLine().split(" ");

        for (String stringNumber : userInput){
            numberList.add(Double.parseDouble(stringNumber));
        }
        for (int i = 0; i < numberList.size() - 1 ; i++) {
            if(numberList.get(i).equals(numberList.get(i + 1))){
            double sum = numberList.get(i)+ numberList.get(i + 1);
            numberList.set(i, sum);
            numberList.remove(i + 1);
            i = -1;//-1 защото когато мине през i++ ще добави 1, а ние искаме да започне от първия индекс, който е равен на 0;

        }

        }
        DecimalFormat decimalFormat = new DecimalFormat("0.#");
        for (Double number : numberList) {
            System.out.print(decimalFormat.format(number)+ " ");
        }
    }
}
