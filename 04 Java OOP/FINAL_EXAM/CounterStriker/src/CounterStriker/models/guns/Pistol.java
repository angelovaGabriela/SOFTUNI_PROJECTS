package CounterStriker.models.guns;

public class Pistol extends GunImpl {
    private static final int BULLETS_FIRED = 1;
    public Pistol(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        super.fire();
        this.setBulletsCount(this.getBulletsCount() - 1);
        return BULLETS_FIRED;
    }
}
