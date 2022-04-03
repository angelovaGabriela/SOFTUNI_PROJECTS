package Problem3;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfCars = Integer.parseInt(scanner.nextLine());

        Map<String, Car> cars = new LinkedHashMap<>();

        for (int i = 0; i < numberOfCars ; i++) {
            String[] input = scanner.nextLine().split(" ");
            String model = input[0];
            double fuelAmount = Double.parseDouble(input[1]);
            double consumption = Double.parseDouble(input[2]);

            Car car = new Car(model, fuelAmount, consumption);
            //Трябва да си запазя колите някъде.
            // След като е казано,
            // че моделът на колата е уникален трябва да използвам MAP
            //key = string - име
            //value = CAR - обект
            //принтираме по реда на тяхното появяване = Linked hasMap
            cars.put(model,car);

        }

        String[] driveCommand = scanner.nextLine().split(" ");
        while (!driveCommand[0].equals("End")){ // Докато първата думичка от командата не е равна на End

        String carModel = driveCommand[1];
        Double kilometersToDrive = Double.parseDouble(driveCommand[2]);
        Car car = cars.get(carModel); // взимам си колата от Map по нейния ключ, (нейното име/модел)
            // казваме и да кара ако е възможно като изпозваме метода на обекта .drive()
            if (!car.drive(kilometersToDrive)){
                System.out.println("Insufficient fuel for the drive");
            }


            driveCommand = scanner.nextLine().split(" ");
        }
        cars.values().forEach(System.out::println);
        // принтирам колите в реда в който са ми ги дали,
        // колите се съхраняват във value
        // за да работи принта се имплементира TO STRING  класа CAR
    }
}
