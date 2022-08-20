package football.entities.field;

import football.common.ConstantMessages;
import football.common.ExceptionMessages;
import football.entities.player.Player;
import football.entities.supplement.Supplement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public abstract class BaseField implements Field {
    private String name;
    private int capacity;
    private Collection<Supplement> supplements;
    private Collection<Player> players;

    protected BaseField(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        supplements = new ArrayList<>();
        players = new ArrayList<>();
    }

    @Override
    public int sumEnergy() {
        int sumEnergy = 0;

        for (Supplement supplement : this.supplements) {
            int currentEnergy = supplement.getEnergy();
            sumEnergy += currentEnergy;
        }

        return sumEnergy;
    }

    @Override
    public void addPlayer(Player player) {

        if (this.capacity > this.players.size()) {
            this.players.add(player);
        } else {
            throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY);
        }

    }

    @Override
    public void removePlayer(Player player) {

        this.players.remove(player);

    }

    @Override
    public void addSupplement(Supplement supplement) {
        this.supplements.add(supplement);
    }

    @Override
    public void drag() {

        for (Player player: this.players) {
            player.stimulation();
        }

    }

    @Override
    public String getInfo() {
       //"{fieldName} ({fieldType}):
        //Player: {playerName1} {playerName2} {playerName3} (…) / Player: none
        //Supplement: {supplementsCount}
        //Energy: {sumEnergy}"

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("%s (%s):", this.name, this.getClass().getSimpleName()))
                .append(System.lineSeparator());
        stringBuilder.append("Player: ");
        if (this.players.isEmpty()) {
            stringBuilder.append("none");
        } else {
            stringBuilder.append(this.players.stream().map(Player::getName).collect(Collectors.joining(" ")));
        }
        stringBuilder.append(System.lineSeparator());

        stringBuilder.append(String.format("Supplement: %d", this.supplements.size()))
                .append(System.lineSeparator());

        stringBuilder.append(String.format("Energy: %d", this.sumEnergy()));

       return  stringBuilder.toString().trim();
    }



    @Override
    public Collection<Player> getPlayers() {
        return this.players;
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return this.supplements;
    }

    @Override
    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.FIELD_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public int getCapacity() {
        return this.capacity;
    }


}
