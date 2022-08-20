package football.core;


import football.common.ConstantMessages;
import football.common.ExceptionMessages;
import football.entities.field.ArtificialTurf;
import football.entities.field.Field;
import football.entities.field.NaturalGrass;
import football.entities.player.Men;
import football.entities.player.Player;
import football.entities.player.Women;
import football.entities.supplement.Liquid;
import football.entities.supplement.Powdered;
import football.entities.supplement.Supplement;
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
        switch (fieldType) {
            case "ArtificialTurf":
                Field newField = new ArtificialTurf(fieldName);
                this.fields.add(newField);
                break;
            case "NaturalGrass":
                Field field = new NaturalGrass(fieldName);
                this.fields.add(field);
                break;
            default:
                throw new NullPointerException(ExceptionMessages.INVALID_FIELD_TYPE);
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FIELD_TYPE, fieldType);
    }

    @Override
    public String deliverySupplement(String type) {
        switch (type) {
            case "Powdered":
                Supplement powdered = new Powdered();
                this.supplement.add(powdered);
                break;
            case "Liquid":
                Supplement liquid = new Liquid();
                this.supplement.add(liquid);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_SUPPLEMENT_TYPE);
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
    }

    @Override
    public String supplementForField(String fieldName, String supplementType) {
        Supplement supplementToAdd = supplement.findByType(supplementType);

        if (supplementToAdd == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_SUPPLEMENT_FOUND, supplementType));
        } else {
            Field field = this.fields.stream().filter(f -> f.getName().equals(fieldName)).findFirst().orElse(null);
            assert field != null;
            field.getSupplements().add(supplementToAdd);
            this.supplement.remove(supplementToAdd);
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_IN_FIELD,supplementType, fieldName);
    }

    @Override
    public String addPlayer(String fieldName, String playerType, String playerName, String nationality, int strength) {
        Field field = this.fields.stream().filter(f -> f.getName().equals(fieldName)).findFirst().orElse(null);
        assert field != null;
        Player player;
        switch (playerType){
            case "Women":
                player = new Women(playerName, nationality, strength);
                if (field.getClass().getSimpleName().equals("ArtificialTurf")) {
                    field.addPlayer(player);
                } else {
                    return String.format(ConstantMessages.FIELD_NOT_SUITABLE);
                }
                break;
            case "Men":
                player = new Men(playerName, nationality, strength);
                if (field.getClass().getSimpleName().equals("NaturalGrass")) {
                    field.addPlayer(player);
                } else {
                    return String.format(ConstantMessages.FIELD_NOT_SUITABLE);
                }
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_TYPE);
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PLAYER_IN_FIELD, playerType, fieldName);
    }

    @Override
    public String dragPlayer(String fieldName) {
        Field field = this.fields.stream().filter(f -> f.getName().equals(fieldName)).findFirst().orElse(null);
        assert field != null;
        field.drag();
        int draggedPlayers = field.getPlayers().size();

        return String.format(ConstantMessages.PLAYER_DRAG, draggedPlayers);
    }

    @Override
    public String calculateStrength(String fieldName) {
        Field field = this.fields.stream().filter(f -> f.getName().equals(fieldName))
                .findFirst().orElse(null);
        assert field != null;

        int sumOfStrength = 0;
        for (Player player : field.getPlayers()) {
            int currentStrength = player.getStrength();
            sumOfStrength += currentStrength;
        }
        return String.format(ConstantMessages.STRENGTH_FIELD, fieldName, sumOfStrength);
    }

    @Override
    public String getStatistics() {
        StringBuilder statistics = new StringBuilder();
        for (Field field : this.fields) {
            statistics.append(field.getInfo())
                    .append(System.lineSeparator());
        }
        return statistics.toString();
    }
}
