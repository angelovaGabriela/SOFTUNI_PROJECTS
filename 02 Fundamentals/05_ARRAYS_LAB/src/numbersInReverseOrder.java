import java.util.Scanner;

public class numbersInReverseOrder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());//големината на масива (колко елемента съдържа)

        int[] numbers = new int[n]; //масив = нов масив от n елемнта
        // четем всеки един от тези n елементи от конзолата, повтарящо се деинствие - for loop
        for (int i = 0; i < n; i++) {//от първия до последния елмент
            //прочитам елемент от конзолата и го добавям в масива
            numbers[i]= Integer.parseInt(scanner.nextLine());
            

        } //прочитам масива на обратно
        for (int i = numbers.length - 1; i >=0 ; i--) {
            System.out.print(numbers[i]+ " ");
            
        }

    }
}
