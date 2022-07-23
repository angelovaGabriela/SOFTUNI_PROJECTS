package fairyShop.models.entities;

import fairyShop.models.Helper;
import fairyShop.models.Instrument;

import java.util.Collection;

public abstract class BaseHelper implements Helper {
    private String name;
    private int energy;
    private Collection<Instrument> instruments;

    protected BaseHelper(String name, int energy) {
        //TODO Add validations
    }

    public String getName() {
        return name;
    }

    private BaseHelper setName(String name) {
        this.name = name;
        return this;
    }

    public int getEnergy() {
        return energy;
    }

    private BaseHelper setEnergy(int energy) {
        this.energy = energy;
        return this;
    }

    public Collection<Instrument> getInstruments() {
        return instruments;
    }

    private BaseHelper setInstruments(Collection<Instrument> instruments) {
        this.instruments = instruments;
        return this;
    }
}
