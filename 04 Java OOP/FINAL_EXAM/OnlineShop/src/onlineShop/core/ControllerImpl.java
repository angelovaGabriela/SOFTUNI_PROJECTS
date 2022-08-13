package onlineShop.core;

import onlineShop.common.constants.ExceptionMessages;
import onlineShop.common.constants.OutputMessages;
import onlineShop.core.interfaces.Controller;

import onlineShop.models.products.components.*;
import onlineShop.models.products.computers.Computer;
import onlineShop.models.products.computers.DesktopComputer;
import onlineShop.models.products.computers.Laptop;
import onlineShop.models.products.peripherals.*;


import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller {

    private final List<Computer> computers;
    private final List<Component> components;
    private final List<Peripheral> peripherals;

    public ControllerImpl() {
        this.peripherals = new ArrayList<>();
        this.components = new ArrayList<>();
        this.computers = new ArrayList<>();
    }

    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {

        Computer computer;

        if (computerType.equals("DesktopComputer")) {

           computer = new DesktopComputer(id, manufacturer, model, price);
           computerAlreadyExist(id);

        } else if (computerType.equals("Laptop")) {

            computer = new Laptop(id, manufacturer, model, price);
            computerAlreadyExist(id);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_COMPUTER_TYPE);
        }

        this.computers.add(computer);
        return String.format(OutputMessages.ADDED_COMPUTER, computer.getId());
    }

    private void computerAlreadyExist(int id) {
        for (Computer c : this.computers) {
            int currentId = c.getId();
            if (currentId == id) {
                throw new IllegalArgumentException(ExceptionMessages.EXISTING_COMPUTER_ID);
            }
        }
    }

    @Override
    public String addPeripheral(int computerId, int id,
                                String peripheralType, String manufacturer,
                                String model, double price, double
                                overallPerformance, String connectionType) {

        Peripheral peripheral;

        switch (peripheralType) {
            case "Headset" -> {
                peripheral = new Headset(id, manufacturer, model, price,
                        overallPerformance, connectionType);
                peripheralAlreadyExist(id);
            }
            case "Keyboard" -> {
                peripheral = new Keyboard(id, manufacturer, model, price,
                        overallPerformance, connectionType);
                peripheralAlreadyExist(id);
            }
            case "Monitor" -> {
                peripheral = new Monitor(id, manufacturer, model, price,
                        overallPerformance, connectionType);
                peripheralAlreadyExist(id);
            }
            case "Mouse" -> {
                peripheral = new Mouse(id, manufacturer, model, price,
                        overallPerformance, connectionType);
                peripheralAlreadyExist(id);
            }
            default -> throw new IllegalArgumentException(ExceptionMessages.INVALID_PERIPHERAL_TYPE);
        }
        Computer computer = this.computers
                .stream()
                .filter(c -> c.getId() == computerId)
                .findFirst()
                .orElse(null);

        assert computer != null;

        this.peripherals.add(peripheral);
        computer.getPeripherals().add(peripheral);


        return String.format(OutputMessages.ADDED_PERIPHERAL, peripheralType, id, computerId);
    }

    private void peripheralAlreadyExist(int id) {
        for (Peripheral p : this.peripherals) {
            int currentId = p.getId();
            if (currentId == id) {
                throw new IllegalArgumentException(ExceptionMessages.EXISTING_PERIPHERAL_ID);
            }
        }
    }

    @Override
    public String removePeripheral(String peripheralType, int computerId) {
      // remove it from the computer with the given id
      // remove it from the collection of peripherals

        Peripheral peripheral = this.peripherals
                .stream()
                .filter(p -> p.getClass().getSimpleName().equals(peripheralType))
                .findFirst()
                .orElse(null);

        Computer computer = this.computers
                .stream()
                .filter(c -> c.getId() == computerId)
                .findFirst()
                .orElse(null);

        assert computer != null;
        computer.getPeripherals().remove(peripheral);

        this.peripherals.remove(peripheral);

        assert peripheral != null;

        return String.format(OutputMessages.REMOVED_PERIPHERAL, peripheralType, peripheral.getId());


    }

    @Override
    public String addComponent(int computerId, int id, String
            componentType, String manufacturer, String model,
                               double price, double overallPerformance, int generation) {

        //int id, String manufacturer, String model,
        //double price, double overallPerformance, int generation

        Component component;

        switch (componentType) {
            case "Motherboard" -> {
                component = new Motherboard(id, manufacturer, model, price, overallPerformance, generation);
                componentAlreadyExist(id);
            }
            case "PowerSupply" -> {
                component = new PowerSupply(id, manufacturer, model, price, overallPerformance, generation);
                componentAlreadyExist(id);
            }
            case "RandomAccessMemory" -> {
                component = new RandomAccessMemory(id, manufacturer, model, price, overallPerformance, generation);
                componentAlreadyExist(id);
            }
            case "SolidStateDrive" -> {
                component = new SolidStateDrive(id, manufacturer, model, price, overallPerformance, generation);
                componentAlreadyExist(id);
            }
            case "VideoCard" -> {
                component = new VideoCard(id, manufacturer, model, price, overallPerformance, generation);
                componentAlreadyExist(id);
            }
            case "CentralProcessingUnit" -> {
                component = new CentralProcessingUnit(id, manufacturer, model, price, overallPerformance, generation);
                componentAlreadyExist(id);
            }
            default -> throw new IllegalArgumentException(ExceptionMessages.INVALID_COMPONENT_TYPE);
        }

        Computer computer = this.computers.stream().filter(c -> c.getId() == computerId).findFirst().orElse(null);
        assert computer != null;
        computer.addComponent(component);

        this.components.add(component);

        return String.format(OutputMessages.ADDED_COMPONENT, componentType, id, computerId);
    }

    private void componentAlreadyExist(int id) {
        for (Component c : this.components) {
            int currentId = c.getId();
            if (currentId == id) {
                throw new IllegalArgumentException(ExceptionMessages.EXISTING_COMPONENT_ID);
            }
        }
    }

    @Override
    public String removeComponent(String componentType, int computerId) {

        Computer computer = this.computers
                .stream()
                .filter(c -> c.getId() == computerId)
                .findFirst().orElse(null);

        assert computer != null;

        Component component = computer.getComponents()
                .stream()
                .filter(c -> c.getClass().getSimpleName().equals(componentType))
                .findFirst().orElse(null);

        computer.getComponents().remove(component);
        this.components.remove(component);

        assert component != null;
        return String.format(OutputMessages.REMOVED_COMPONENT, componentType, component.getId());

    }

    @Override
    public String buyComputer(int id) {

        Computer computer =
                this.computers
                        .stream()
                        .filter(c -> c.getId() == id).findFirst()
                        .orElse(null);

        this.computers.remove(computer);
        assert computer != null;

        return computer.toString();

    }

    @Override
    public String BuyBestComputer(double budget) {
        Computer bestComputer = null;
        double biggestPerformance = 0;
        for (Computer computer : this.computers) {
            double currentPerformance = computer.getOverallPerformance();
            double price = computer.getPrice();
            if (currentPerformance <= biggestPerformance && price <= budget) {
                biggestPerformance = currentPerformance;
                bestComputer = computer;
            }

            if (this.computers.isEmpty() || bestComputer == null) {
                throw new  IllegalArgumentException
                        (String.format(ExceptionMessages.CAN_NOT_BUY_COMPUTER, budget));
            }
        }
        assert bestComputer != null;

        this.computers.remove(bestComputer);
        return bestComputer.toString();
    }

    @Override
    public String getComputerData(int id) {
       Computer computer =
               this.computers
                       .stream()
                       .filter(c -> c.getId() == id)
                       .findFirst().orElse(null);

        assert computer != null;
        return computer.toString();
    }
}
