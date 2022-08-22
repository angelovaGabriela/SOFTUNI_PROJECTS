package goldDigger.models.discoverer;

import goldDigger.common.ExceptionMessages;
import goldDigger.models.museum.BaseMuseum;
import goldDigger.models.museum.Museum;



import static goldDigger.common.ConstantMessages.FINAL_DISCOVERER_ENERGY;
import static goldDigger.common.ConstantMessages.FINAL_DISCOVERER_NAME;


public abstract class BaseDiscoverer implements Discoverer {
   private String name;
   private double energy;
   private Museum museum;

    protected BaseDiscoverer(String name, double energy) {
        this.setName(name);
        this.setEnergy(energy);
        museum = new BaseMuseum();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new NullPointerException(ExceptionMessages.DISCOVERER_NAME_NULL_OR_EMPTY);
        }

        this.name = name;
    }

    private void setEnergy(double energy) {
        if (energy < 0) {
            throw new IllegalArgumentException(ExceptionMessages.DISCOVERER_ENERGY_LESS_THAN_ZERO);
        }
        this.energy = energy;
    }

    private void setMuseum(Museum museum) {
        this.museum = museum;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getEnergy() {
        return this.energy;
    }

    @Override
    public boolean canDig() {
        return this.energy > 0;
    }

    @Override
    public Museum getMuseum() {
        return this.museum;
    }

    @Override
    public void dig() {
        this.energy -= 15;
        if (this.energy < 0) {
            this.energy = 0;
        }
    }

    @Override
    public String toString() {
        //Name: {discovererName}
        //Energy: {discovererEnergy}
        //Museum exhibits: museumExhibits1, museumExhibits2, museumExhibits3, …, museumExhibits n}"

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(FINAL_DISCOVERER_NAME, this.name)).append(System.lineSeparator());
        stringBuilder.append(String.format(FINAL_DISCOVERER_ENERGY, this.energy)).append(System.lineSeparator());
        stringBuilder.append("Museum exhibits: ");
        if (this.museum.getExhibits().isEmpty()) {
            stringBuilder.append("None");
        } else {
            this.museum.getExhibits().forEach(e->stringBuilder.append(String.format("%s", e)).append(", "));
        }
        return stringBuilder.toString().trim();
        }
}
