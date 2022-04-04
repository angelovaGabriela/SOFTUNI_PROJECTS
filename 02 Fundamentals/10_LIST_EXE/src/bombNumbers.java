import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class bombNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List <Integer> sequence = Arrays.
                stream(scanner.nextLine().split("\\s+"))
                .map(Integer :: parseInt)
                .collect(Collectors.toList());

        List <Integer> commands = Arrays
                .stream(scanner.nextLine().split("\\s+"))
                .map(Integer ::parseInt)
                .collect(Collectors.toList());
int bombNum = commands.get(0);
int power = commands.get(1);
// обхождам целия масив и търся дали го има числото бомба
        for (int i = 0; i <= sequence.size()-1; i++) {
            if(sequence.get(i)==bombNum){
                int start = i - power;//търся от къде да започна да трия
                if (start < 0){
                    start = 0; //искам да започна от нулевия индекс
                }
                //търся крайната точка - до къде да трия
                int finish = i + power +1;//
                if (finish > sequence.size()){
                    finish = sequence.size();// ако листа няма толкова индекси просто трием до края
                }
                for (int j = start; j < finish ; j++) {
                    sequence.remove(start);
                } i--;
            }
        } int sum = 0;
        for (Integer num : sequence) {
            sum += num;
        }
        System.out.println(sum);

        }

        }






