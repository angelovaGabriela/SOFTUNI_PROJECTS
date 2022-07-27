package christmasRaces.entities.drivers;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.entities.cars.Car;

public class DriverImpl implements Driver {

    private String name;
    private Car car;
    private int numberOfWins;
    private boolean canParticipate;

    public DriverImpl(String name) {
        this.setName(name);
        this.setCanParticipate(canParticipate);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Car getCar() {
        return this.car;
    }

    @Override
    public int getNumberOfWins() {
        return this.numberOfWins;
    }

    @Override
    public void addCar(Car car) {

        if (car == null) {
            throw new IllegalArgumentException(ExceptionMessages.CAR_INVALID);
        } else {
            this.car = car;
        }
    }

    @Override
    public void winRace() {
        this.numberOfWins++;
    }

    @Override
    public boolean getCanParticipate() {
        return this.canParticipate;
    }

    private void setName(String name) {
       if(name.trim().isEmpty() || name.length() < 5) {
           throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_NAME, name, 5));
       }
       this.name = name;
    }


    private void setCanParticipate(boolean canParticipate) {
        if (this.car != null) {
            this.canParticipate = true;
        } else {
            this.canParticipate = canParticipate;
        }
    }
}
