package Google;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            Map<String, Person> people = new HashMap<>();
            String input = scanner.nextLine();
            while (!"End".equals(input)) {
                String[] tokens = input.split("\\s+");
                String personName = tokens[0];
                people.putIfAbsent(personName, new Person(personName));
                String informationType = tokens[1];
                switch (informationType) {
                    case "company":
                        //{companyName} {department} {salary}
                        String companyName = tokens[2];
                        String department = tokens[3];
                        double salary = Double.parseDouble(tokens[4]);
                        Company company = new Company(companyName, department, salary);
                        people.get(personName).setCompany(company);
                        break;
                    case "pokemon":
                        //{pokemonName} {pokemonType}
                        String pokemonName = tokens[2];
                        String pokemonType = tokens[3];
                        Pokemon pokemon = new Pokemon(pokemonName, pokemonType);
                        people.get(personName).addPokemon(pokemon);
                        break;
                    case "parents":
                        //{parentName} {parentBirthday}
                        String parentName = tokens[2];
                        String parentBirthday = tokens[3];
                        Parent parent = new Parent(parentName, parentBirthday);
                        people.get(personName).addParent(parent);
                        break;
                    case "children":
                        //{childName} {childBirthday}
                        String childName = tokens[2];
                        String childBirthday = tokens[3];
                        Child child = new Child(childName, childBirthday);
                        people.get(personName).addChild(child);
                        break;
                    case "car":
                        //{carModel} {carSpeed}
                        String carModel = tokens[2];
                        int carSpeed = Integer.parseInt(tokens[3]);
                        Car car = new Car(carModel, carSpeed);
                        people.get(personName).setCar(car);
                        break;
                }
                input = scanner.nextLine();
            }
            String nameForOutput = scanner.nextLine();
            System.out.println(people.get(nameForOutput));
        }
    }

