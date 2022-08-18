package onlineShop.models.products.components;

import onlineShop.common.constants.OutputMessages;
import onlineShop.models.products.BaseProduct;

public abstract class BaseComponent extends BaseProduct implements Component {

   private int generation;

    protected BaseComponent(int id, String manufacturer,
                            String model, double price,
                            double overallPerformance, int generation) {
        super(id, manufacturer, model, price, overallPerformance);
        this.generation = generation;
    }

    @Override
    public int getGeneration() {
        return 0;
    }

    private void setGeneration(int generation) {
        this.generation = generation;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(super.toString())
                .append(" ")
                .append(String.format(OutputMessages.COMPONENT_TO_STRING, this.generation));

        return stringBuilder.toString();

    }
}
