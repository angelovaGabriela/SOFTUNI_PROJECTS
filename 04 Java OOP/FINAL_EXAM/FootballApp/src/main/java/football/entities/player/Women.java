package football.entities.player;

public class Women extends BasePlayer {
    private static final double KG = 60.00;

    public Women(String name, String nationality, int strength) {
        super(name, nationality, KG, strength);
    }
    //TODO: I can only play on ArtificialTurf!

    @Override
    public void stimulation() {
       this.setStrength(this.getStrength() + 115);
    }
}
