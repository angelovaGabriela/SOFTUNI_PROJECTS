package RawData;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        //колекция
        List<Car> cars = new ArrayList<>();
        ArrayList<Tire> allTires = new ArrayList<>(4);

        for (int lines = 0; lines < n; lines++) {
            String[] input = scanner.nextLine().split("\\s+");
            String model = input[0];
            int engineSpeed = Integer.parseInt(input[1]);
            int enginePower = Integer.parseInt(input[2]);
            Engine engine = new Engine(engineSpeed, enginePower);
            int cargoWeight = Integer.parseInt(input[3]);
            String cargoType = input[4];
            Cargo cargo = new Cargo(cargoType, cargoWeight);

            double tire1pressure = Double.parseDouble(input[5]);
            int tire1age = Integer.parseInt(input[6]);

            double tire2pressure = Double.parseDouble(input[7]);
            int tire2age = Integer.parseInt(input[8]);

            double tire3pressure = Double.parseDouble(input[9]);
            int tire3age = Integer.parseInt(input[10]);

            double tire4pressure = Double.parseDouble(input[11]);
            int tire4age = Integer.parseInt(input[12]);

            Tire tire1 = new Tire(tire1pressure, tire1age);
            allTires.add(tire1);
            Tire tire2 = new Tire(tire2pressure, tire2age);
            allTires.add(tire2);
            Tire tire3 = new Tire(tire3pressure, tire3age);
            allTires.add(tire3);
            Tire tire4 = new Tire(tire4pressure, tire4age);
            allTires.add(tire4);

            Car car = new Car(model, engine, cargo, allTires);

            cars.add(car);

            // да добавя в колекция


        }

        String command = scanner.nextLine();
        List<String> carModel = new ArrayList<>();
if(command.equals("fragile")) {
    for (Car e : cars) {
        if (e.getCargo().getType().equals("fragile")) {
            for (Tire tireSet : e.getTire()) {
                if (tireSet.getPressure() < 1) {
                    carModel.add(e.getModel());
                    break;
                }
            }
        }
    }
} else {
    //print all cars whose Cargo Type is "fragile"
    //tire whose pressure is  < 1

    cars.forEach(e -> {
        if  (e.getCargo().getType().equals("flamable")
                && e.getEngine().getPower() > 250) {
            carModel.add(e.getModel());
        }
    });
}


        carModel.forEach(System.out::println);
    }

        }

