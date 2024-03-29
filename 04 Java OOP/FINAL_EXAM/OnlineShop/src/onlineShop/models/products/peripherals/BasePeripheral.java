package onlineShop.models.products.peripherals;

import onlineShop.common.constants.OutputMessages;
import onlineShop.models.products.BaseProduct;

public abstract class BasePeripheral extends BaseProduct implements Peripheral {

    private String connectionType;

    protected BasePeripheral(int id, String manufacturer,
                             String model, double price,
                             double overallPerformance, String connectionType) {
        super(id, manufacturer, model, price, overallPerformance);
        this.connectionType = connectionType;
    }

    @Override
    public String getConnectionType() {
        return null;
    }
    private void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(super.toString())
                .append(" ")
                .append(String.format(OutputMessages.PERIPHERAL_TO_STRING, this.connectionType));

        return stringBuilder.toString();
    }
}
