package football.entities.field;

import football.common.ExceptionMessages;
import football.entities.player.Player;
import football.entities.supplement.Supplement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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
        return 0;
    }

    @Override
    public void addPlayer(Player player) {

    }

    @Override
    public void removePlayer(Player player) {

    }

    @Override
    public void addSupplement(Supplement supplement) {

    }

    @Override
    public void drag() {

    }

    @Override
    public String getInfo() {
        return null;
    }

    //TODO: HERE I AM

    @Override
    public Collection<Player> getPlayers() {
        return Collections.unmodifiableCollection(this.players);
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return Collections.unmodifiableCollection(this.supplements);
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
