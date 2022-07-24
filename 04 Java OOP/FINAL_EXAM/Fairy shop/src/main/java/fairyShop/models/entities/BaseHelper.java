package fairyShop.models.entities;

import fairyShop.common.ExceptionMessages;
import fairyShop.models.Helper;
import fairyShop.models.Instrument;

import java.util.ArrayList;
import java.util.Collection;

public  abstract class BaseHelper implements Helper {
    private String name;
    private int energy;
    private Collection<Instrument> instruments;

    protected BaseHelper(String name, int energy) {
       setName(name);
        this.setEnergy(energy);
        this.instruments = new ArrayList<>();
    }

    @Override
    public void work() {
        this.setEnergy(this.getEnergy()-10);
        if (this.energy < 0) {
            this.energy = 0;
        }
    }

    @Override
    public void addInstrument(Instrument instrument) {
       this.instruments.add(instrument);
    }

    @Override
    public boolean canWork() {
        return this.energy > 0;
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {

        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.HELPER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public int getEnergy() {
        return this.energy;
    }

    protected void setEnergy(int energy) {
        this.energy = energy;
    }

    public Collection<Instrument> getInstruments() {
        return instruments;
    }

    private BaseHelper setInstruments(Collection<Instrument> instruments) {
        this.instruments = instruments;
        return this;
    }
}
