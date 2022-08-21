package CounterStriker.models.players;

import CounterStriker.common.ExceptionMessages;
import CounterStriker.models.guns.Gun;

public abstract class PlayerImpl implements Player {
    private String username;
    private int health;
    private int armor;
    private boolean isAlive;
    private Gun gun;

    protected PlayerImpl(String username, int health, int armor, Gun gun) {
        this.setUsername(username);
        this.setHealth(health);
        this.setArmor(armor);
        this.setGun(gun);
    }

    private void setUsername(String username) {

        if (username == null || username.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.INVALID_PLAYER_NAME);
        }
        this.username = username;
    }

    private void setHealth(int health) {
        if (health < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_HEALTH);
        }
        this.health = health;
    }

    private void setArmor(int armor) {
        if (armor < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_ARMOR);

        }
        this.armor = armor;
    }

    private void setGun(Gun gun) {
        if (gun == null) {
            throw new NullPointerException(ExceptionMessages.INVALID_GUN);
        }

        this.gun = gun;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public int getArmor() {
        return this.armor;
    }

    @Override
    public Gun getGun() {
        return this.gun;
    }

    @Override
    public boolean isAlive() {
        if (this.health < 0) {
            this.isAlive = true;
        }
        return this.isAlive;
    }

    @Override
    public void takeDamage(int points) {

        //TODO The takeDamage() method
        // decreases the Player's armor and health.
        // First, you need to reduce the armor.
        // If the armor reaches 0,
        // transfer the damage to health points.
        // If the health points are less than or equal to zero,
        // the player is dead.

        while (this.armor > 0) {
            this.armor -= points;
            if (this.armor == 0) {
                break;
            }
        }
        while (this.health > 0 && this.armor == 0) {
            this.health -= points;
            if (this.health == 0){
                isAlive();
            }
        }
    }

    @Override
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();


        stringBuilder.append(String.format("%s: %s", this.getClass().getSimpleName(), this.username)).append(System.lineSeparator());
        stringBuilder.append(String.format("--Health: %d", this.health)).append(System.lineSeparator());
        stringBuilder.append(String.format("--Armor: %d", this.armor)).append(System.lineSeparator());
        stringBuilder.append(String.format("--Gun: %s", this.gun.getName())).append(System.lineSeparator());

        return stringBuilder.toString().trim();
    }
}
