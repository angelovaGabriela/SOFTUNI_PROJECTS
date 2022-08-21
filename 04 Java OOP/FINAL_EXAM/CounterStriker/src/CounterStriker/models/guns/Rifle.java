package CounterStriker.models.guns;

public class Rifle extends GunImpl {
    private int firedBullets;

    public Rifle(String name, int bulletsCount) {
        super(name, bulletsCount);
    }
    @Override
    public int fire() {
        super.fire();
        this.setBulletsCount(this.getBulletsCount() - 10);
        firedBullets += 10;
        return firedBullets;
    }
}
