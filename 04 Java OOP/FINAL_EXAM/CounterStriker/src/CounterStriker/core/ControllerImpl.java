package CounterStriker.core;

import CounterStriker.common.ExceptionMessages;
import CounterStriker.common.OutputMessages;
import CounterStriker.models.field.Field;
import CounterStriker.models.guns.Gun;
import CounterStriker.models.guns.Pistol;
import CounterStriker.models.guns.Rifle;
import CounterStriker.models.players.CounterTerrorist;
import CounterStriker.models.players.Player;
import CounterStriker.models.players.Terrorist;
import CounterStriker.repositories.GunRepository;
import CounterStriker.repositories.PlayerRepository;

public class ControllerImpl implements Controller {
    private GunRepository guns;
    private PlayerRepository players;
    private Field field;

    public ControllerImpl() {
        guns = new GunRepository();
        players = new PlayerRepository();
    }

    @Override
    public String addGun(String type, String name, int bulletsCount) {
       Gun gun;
        if (type.equals("Pistol")){
           gun = new Pistol(name, bulletsCount);
        } else if (type.equals("Rifle")) {
            gun = new Rifle(name, bulletsCount);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_GUN_TYPE);
        }
        this.guns.add(gun);
        return String.format(OutputMessages.SUCCESSFULLY_ADDED_GUN, name);
    }

    @Override
    public String addPlayer(String type, String username, int health, int armor, String gunName) {
        Player player;
        Gun gun = this.guns.findByName(gunName);
        if (gun == null) {
            throw new NullPointerException(ExceptionMessages.GUN_CANNOT_BE_FOUND);
        }

        if (type.equals("Terrorist")) {
            player = new Terrorist(username, health, armor, gun);
        } else if (type.equals("CounterTerrorist")) {
            player = new CounterTerrorist(username, health,armor,gun);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_TYPE);
        }

        this.players.add(player);
        return String.format(OutputMessages.SUCCESSFULLY_ADDED_PLAYER, username);
    }

    @Override
    public String startGame() {
        return this.field.start(this.players.getModels());
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        for (Player player : this.players.getModels()) {
            sb.append(player.toString()).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
