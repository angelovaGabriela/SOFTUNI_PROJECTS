package Problem5;

import java.util.*;

public class Main {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        int numberOfEngines = Integer.parseInt(scanner.nextLine());

        Map<String, Engine> engines = new HashMap<>(); // String - модел, Engine - референция към обектите в колекцията.

        //Четем двигатели от конзолата
        for (int i = 0; i < numberOfEngines; i++) {
            // чета двигатели от конзолата и сплитвам входа за да си взема елементите
            String[] input = scanner.nextLine().split(" ");

            String model = input[0];
            int power = Integer.parseInt(input[1]);
            Engine engine = null;
            if (input.length == 2){ // проверяваме дължината на входните данни и разбираме колко параметъра има и кой конструктор ще използваме
                engine = new Engine(model, power);
            } else if (input.length == 4){
                int  displacement = Integer.parseInt(input[2]);
                String efficiency = input[3];
                engine = new Engine(model, power, displacement, efficiency);

            } else if (input.length == 3){
                try {
                   int displacement = Integer.parseInt(input[2]);
                   engine = new Engine(model, power, displacement);
                } catch (NumberFormatException e ){
                    String efficiency = input[2];
                    engine = new Engine(model, power, efficiency);
                }
            }
            //трябва да запазя двигателите в колекция и след като водещ е модела трябва да използвам MAP
            engines.put(model,engine);
        }

        //Четем коли от конзолата
        int numberOfCars = Integer.parseInt(scanner.nextLine());

        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < numberOfCars; i++) {
            String[] input = scanner.nextLine().split(" ");
            String model = input[0];
            String engineModel = input[1]; // казват ми модела на двигатела и аз трябва да го взема от мапа
            Engine carEngine = engines.get(engineModel);

            Car car = null;

            if (input.length == 2){
                car = new Car(model, carEngine);
            } else if (input.length == 4){
                int weight = Integer.parseInt(input[2]);
                String color = input[3];
                car = new Car(model, carEngine, weight, color);
            } else {
                try {
                    int weight = Integer.parseInt(input[2]);
                    car = new Car(model, carEngine, weight);
                } catch (NumberFormatException e) {
                    String color = input[2];
                    car = new Car(model, carEngine, color);
                }

            }
                //колите можем да ги съхраним и в лист  - както ги четем така трябва да ги принтираме
                cars.add(car);

        }

        cars.forEach(System.out::println);
        //необходими са 2 .toString() един за Car и един за Engine

    }

}
