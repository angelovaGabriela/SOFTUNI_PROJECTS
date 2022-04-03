import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchData {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
String input = scanner.nextLine();
String regex = "\\b(?<day>\\d{2})(?<separator>[\\. \\/-])(?<month>[A-Z][a-z]{2})\\2(?<year>\\d{4})\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matchedDates = pattern.matcher(input);

        while (matchedDates.find()){
            String day = matchedDates.group("day");
            String month = matchedDates.group("month");
            String year = matchedDates.group("year");
            System.out.printf("Day: %s, Month: %s, Year: %s%n", day,month,year);

        }

    }
}
//  \b(?<day>\d{2})(?<separator>[ \/-])(?<month>[A-Z][a-z]{2})\2\(?<year>\d{4})\b