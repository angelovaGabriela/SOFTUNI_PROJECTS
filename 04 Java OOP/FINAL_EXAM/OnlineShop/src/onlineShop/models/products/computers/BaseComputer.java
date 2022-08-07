package onlineShop.models.products.computers;

import onlineShop.common.constants.ExceptionMessages;
import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseComputer extends BaseProduct implements Computer {

    private List<Component> components;
    private List<Peripheral> peripherals;

    protected BaseComputer(int id, String manufacturer,
                           String model, double price,
                           double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public List<Component> getComponents() {
        return null;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return null;
    }

    @Override
    public void addComponent(Component component) {
        // ако го намериш вземи първото - има го в колекцията != null
        // ако НЕ го намериш върни null - няма го в колекцията == null
        Component foundComponent =
                this.components.stream().
                        filter(c -> c.getClass().getSimpleName()
                                .equals(component.getClass().getSimpleName()))
                        .findFirst().orElse(null);

        if (foundComponent != null) {
            String componentType = component.getClass().getSimpleName();
            String computerType = this.getClass().getSimpleName();
            int id = super.getId();
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXISTING_COMPONENT, componentType, computerType, id));
        } else {
            this.components.add(component);
        }

        //else add


    }

    @Override
    public Component removeComponent(String componentType) {
        return null;
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {

    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        return null;
    }


    @Override
    public double getOverallPerformance() {
        if (this.components.isEmpty()) {
            return super.getOverallPerformance();
        } else {

            double computerOverallPerformance = super.getOverallPerformance();

            double performance = 0;
            double averageOverallPerformance;
            int countOfComponents = this.components.size();

            for (Component component : this.components) {

                double current = component.getOverallPerformance();
                performance += current;
            }

            averageOverallPerformance = performance / countOfComponents;

            return averageOverallPerformance + computerOverallPerformance;
        }
    }

    @Override
    public double getPrice() {
        double computerPrice = super.getPrice();

        double totalComponentsPrice = 0;
        for (Component component : this.components) {
            double currentPrice = component.getPrice();
            totalComponentsPrice += currentPrice;
        }

        double totalPeripheralPrice = 0;
        for (Peripheral peripheral : this.peripherals) {
            double currentPrice = peripheral.getPrice();
            totalPeripheralPrice += currentPrice;
        }

        return computerPrice + totalComponentsPrice + totalPeripheralPrice;
    }

    @Override
    public String toString() {

      StringBuilder builder = new StringBuilder();

      double overallPerformance = 0;

        for (Peripheral peripheral : this.peripherals) {
            double current = peripheral.getOverallPerformance();
            overallPerformance += current;
        }

        int count = this.peripherals.size();
        double averageOverallPerformance = overallPerformance / count;

      builder.append(super.toString()).append(System.lineSeparator());

      builder.append(String.format(" Components (%d):", this.components.size())).append(System.lineSeparator());
      this.components.stream().forEach(component -> builder.append("  ").append(component).append(System.lineSeparator()));

      builder.append(String.format(" Peripherals (%d); Average Overall Performance (%f):",
              this.peripherals.size(), averageOverallPerformance)).append(System.lineSeparator());
      this.peripherals.forEach(peripheral -> builder.append("  ").append(peripheral).append(System.lineSeparator()));

      return builder.toString();

    }



}
