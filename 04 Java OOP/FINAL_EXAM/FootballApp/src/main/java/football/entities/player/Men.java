package football.entities.player;

public class Men extends BasePlayer {
    private static final double KG = 85.50;

    public Men(String name, String nationality, int strength) {
        super(name, nationality, KG, strength);
    }

    @Override
    public void stimulation() {
        this.setStrength(this.getStrength() + 145);
    }

    //TODO: I can only play on NaturalGrass!
}
