package restaurant.core;

import restaurant.common.ExceptionMessages;
import restaurant.common.OutputMessages;
import restaurant.core.interfaces.Controller;
import restaurant.entities.drinks.Fresh;
import restaurant.entities.drinks.Smoothie;
import restaurant.entities.healthyFoods.Salad;
import restaurant.entities.healthyFoods.VeganBiscuits;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.tables.InGarden;
import restaurant.entities.tables.Indoors;
import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.interfaces.*;

import static restaurant.common.OutputMessages.FOOD_ADDED;

public class ControllerImpl implements Controller {


    private final HealthFoodRepository<HealthyFood> healthFoodRepository;
    private final BeverageRepository<Beverages> beverageRepository;
    private final TableRepository<Table> tableRepository;

    public ControllerImpl(HealthFoodRepository<HealthyFood> healthFoodRepository,
                          BeverageRepository<Beverages> beverageRepository,
                          TableRepository<Table> tableRepository) {
        this.healthFoodRepository = healthFoodRepository;
        this.beverageRepository = beverageRepository;
        this.tableRepository = tableRepository;
    }

    @Override
    public String addHealthyFood(String type, double price, String name) {
        HealthyFood food = type.equals("Salad")
                ? new Salad(name, price)
                : new VeganBiscuits(name,price);

        HealthyFood previouslyAddedFood = this.healthFoodRepository.foodByName(name);
        if (previouslyAddedFood == null) { // if the repository doesn't contain this food == null
            this.healthFoodRepository.add(food);
            return String.format(OutputMessages.FOOD_ADDED, name);

        } else {
            throw new IllegalArgumentException(String.format(ExceptionMessages.FOOD_EXIST, name));
        }


    }

    @Override
    public String addBeverage(String type, int counter, String brand, String name){
        Beverages beverage = type.equals("Fresh")
                ? new Fresh(name,counter, brand)
                : new Smoothie(name,counter,brand);

        Beverages previousAddedBeverage = this.beverageRepository.beverageByName(name, brand);
        if (previousAddedBeverage == null) { // if the repository doesn't contain this food == null
            this.beverageRepository.add(beverage);
            return String.format(OutputMessages.BEVERAGE_ADDED, type, brand);

        } else {
            throw new IllegalArgumentException(String.format(ExceptionMessages.BEVERAGE_EXIST, name));
        }


    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table = type.equals("Indoors")
                ? new Indoors(tableNumber, capacity)
                : new InGarden(tableNumber, capacity);

        Table previousAddedTable = this.tableRepository.byNumber(tableNumber);
        if (previousAddedTable == null) { // if the repository doesn't contain this food == null
            this.tableRepository.add(table);
            return String.format(OutputMessages.TABLE_ADDED, tableNumber);

        } else {
            throw new IllegalArgumentException(String.format(ExceptionMessages.TABLE_IS_ALREADY_ADDED, tableNumber));
        }
    }

    @Override
    public String reserve(int numberOfPeople) {
        //TODO:
        return null;
    }

    @Override
    public String orderHealthyFood(int tableNumber, String healthyFoodName) {
        //TODO:
        return null;
    }

    @Override
    public String orderBeverage(int tableNumber, String name, String brand) {
        //TODO:
        return null;
    }

    @Override
    public String closedBill(int tableNumber) {
        //TODO:
        return null;
    }


    @Override
    public String totalMoney() {
        //TODO:
        return null;
    }
}
