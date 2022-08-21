package CounterStriker.models.field;

import CounterStriker.common.OutputMessages;
import CounterStriker.models.players.Player;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FieldImpl implements Field {

    private final List<Player> terrorists;
    private final List<Player> counterTerrorists;

    public FieldImpl() {
        this.terrorists = new ArrayList<>();
        this.counterTerrorists = new ArrayList<>();
    }

    @Override
    public String start(Collection<Player> players) {

        for (Player player : players) {

            if (player.getClass().getSimpleName().equals("Terrorist")) {
                this.terrorists.add(player);

            } else if (player.getClass().getSimpleName().equals("CounterTerrorist")) {
                this.counterTerrorists.add(player);
            }
        }

        String returnValue = null;

        while ((!this.terrorists.isEmpty()) || (!this.counterTerrorists.isEmpty())) {

            for (int t = 0; t < this.terrorists.size(); t++) {
                for (int ct = 0; ct < this.counterTerrorists.size(); ct++) {
                    this.counterTerrorists.get(ct).takeDamage(this.terrorists.get(t).getGun().fire());
                    if (this.counterTerrorists.get(ct).getHealth() == 0) {
                        Player player = this.counterTerrorists.get(ct);
                        this.counterTerrorists.remove(player);
                    }
                    if (this.counterTerrorists.isEmpty()) {
                        returnValue = String.format(OutputMessages.TERRORIST_WINS);
                        break;
                    }

                    this.terrorists.get(t).takeDamage(this.counterTerrorists.get(ct).getGun().fire());
                    if (this.terrorists.get(t).getHealth() == 0) {
                        Player player = this.terrorists.get(t);
                        this.terrorists.remove(player);

                    }
                    if (this.terrorists.isEmpty()) {
                        returnValue = String.format(OutputMessages.COUNTER_TERRORIST_WINS);
                        break;
                    }
                }
            }
        }

       return returnValue;
    }
}
//continues while players from one team  have 0 health
//two teams Terrorist & CounterTerrorist.
//Each live terrorist shoots on each live CounterTerrorist once
// and inflicts damage equal to the bullets fired
//After that each live CounterTerrorist shoots on each live terrorist.
// If Terrorists win return "Terrorist wins!"
// otherwise return "Counter Terrorist wins!"