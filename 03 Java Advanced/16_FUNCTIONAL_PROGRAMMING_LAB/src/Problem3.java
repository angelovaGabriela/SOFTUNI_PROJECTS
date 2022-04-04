import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Problem3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//чета си входния текст и го събирам в масив сплитнат по празно място
        String[] strings = scanner.nextLine().split("\\s+");
//Нещото по което филтрираме - цедка (думите които започват с главна буква)
        Predicate<String> predicate = str-> Character.isUpperCase(str.charAt(0));
// събирам в лист от стрингове думите които започват с главна буква, преминават през филтъра, през цедката
     List<String> upperCaseStrings =  Arrays.stream(strings)
                .filter(predicate)
                 .collect(Collectors.toList());
// принтирам броя на думите с главна буква
        System.out.println(upperCaseStrings.size());
// Принтирам всяка дума с главна буква на нов ред
        System.out.println(String.join(System.lineSeparator(), upperCaseStrings));


    }
}
