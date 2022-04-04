package vehicles;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        String[] tokens = scanner.nextLine().split(" ");
        double carFuelAmount = Double.parseDouble(tokens[1]);
        double carConsumption = Double.parseDouble(tokens[2]);
        double carTankCapacity = Double.parseDouble(tokens[3]);
        Vehicle car = new Car(carFuelAmount, carConsumption, carTankCapacity);

        tokens = scanner.nextLine().split(" ");
        double truckFuelAmount = Double.parseDouble(tokens[1]);
        double truckConsumption = Double.parseDouble(tokens[2]);
        double truckTankCapacity = Double.parseDouble(tokens[3]);
        Vehicle truck = new Truck(truckFuelAmount, truckConsumption, truckTankCapacity);

        tokens = scanner.nextLine().split(" ");
        double busFuelAmount = Double.parseDouble(tokens[1]);
        double busConsumption = Double.parseDouble(tokens[2]);
        double busTankCapacity = Double.parseDouble(tokens[3]);
        Vehicle bus = new Bus(busFuelAmount, busConsumption, busTankCapacity);



        Map<String, Vehicle> vehicles = new LinkedHashMap<>();
        vehicles.put("Car", car);
        vehicles.put("Truck", truck);
        vehicles.put("Bus", bus);

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            tokens = scanner.nextLine().split(" ");

            String commandName = tokens[0];
            String vehicleType = tokens[1];

            Vehicle vehicle = vehicles.get(vehicleType);

    try {


    switch (commandName) {
        case "Drive":
            double distance = Double.parseDouble(tokens[2]);

            if (vehicle instanceof Bus) {
                ((Bus) vehicle).setEmpty(false);
            }
            System.out.println(vehicle.drive(distance));
            break;
        case "DriveEmpty":
            double driveEmptyBusDistance = Double.parseDouble(tokens[2]);
            if (vehicle instanceof Bus) {
                ((Bus) vehicle).setEmpty(true);
            }
            System.out.println(vehicle.drive(driveEmptyBusDistance));

            break;
        case "Refuel":
            double liters = Double.parseDouble(tokens[2]);
            vehicles.get(vehicleType).refuel(liters);

            break;
    }

    } catch (IllegalArgumentException exception){
    System.out.println(exception.getMessage());
}
        }
        vehicles.values().forEach(System.out::println);
    }
}
