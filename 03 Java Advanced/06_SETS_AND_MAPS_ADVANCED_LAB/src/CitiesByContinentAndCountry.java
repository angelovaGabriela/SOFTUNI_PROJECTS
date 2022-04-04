import java.util.*;

public class CitiesByContinentAndCountry {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Map<String, Map<String, List<String>>> countries = new LinkedHashMap<>();

       //континен, мап страна със списък от градове
        while (n-- > 0){ // докато броя на държавите минус 1 > 0

            String [] tokens = scanner.nextLine().split("\\s+");
            String continent = tokens[0];
            String country = tokens[1];
            String city = tokens[2];

            countries.putIfAbsent(continent,new LinkedHashMap<>());
            // вътрешния мап за държавата с градовете
            Map<String, List<String>> map = countries.get(continent);
           //добавям страна ако липсва
            map.putIfAbsent(country, new ArrayList<>());
            //добавям град с списъка ако липсва
            List<String> cities = map.get(country);
            cities.add(city);

        }

        countries.entrySet()
                .forEach(entry ->{
                    System.out.println(entry.getKey() + ":");

                    entry.getValue().entrySet()
                            .forEach(ie ->
                                    System.out.println("  " + ie.getKey() + " -> "
                                     + String.join(", ", ie.getValue())
                                    ));
                });
    }
}
