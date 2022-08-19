package football.core;


import football.entities.field.Field;
import football.repositories.SupplementRepository;
import football.repositories.SupplementRepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller {

    private SupplementRepository supplement;
    private Collection<Field> fields;

    public ControllerImpl() {
        this.supplement = new SupplementRepositoryImpl();
        this.fields = new ArrayList<>();
    }

    @Override
    public String addField(String fieldType, String fieldName) {
        return null;
    }

    @Override
    public String deliverySupplement(String type) {
        return null;
    }

    @Override
    public String supplementForField(String fieldName, String supplementType) {
        return null;
    }

    @Override
    public String addPlayer(String fieldName, String playerType, String playerName, String nationality, int strength) {
        return null;
    }

    @Override
    public String dragPlayer(String fieldName) {
        return null;
    }

    @Override
    public String calculateStrength(String fieldName) {
        return null;
    }

    @Override
    public String getStatistics() {
        return null;
    }
}
