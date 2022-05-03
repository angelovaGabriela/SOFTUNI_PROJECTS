package rpg_lab;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AxeTest  {
    private static final int AXE_ATTACK = 10;
    private static final int AXE_DURABILITY = 10;

    private static final int HEALTH = 100;
    private static final int EXPERIENCE = 100;
    private Dummy dummy;
    private Axe axe;
    private Axe brokenAxe;

   @Before
   public void setUp() {
       this.axe = new Axe(AXE_ATTACK,AXE_DURABILITY);
       this.brokenAxe = new Axe(AXE_ATTACK,0);
       this.dummy = new Dummy(HEALTH, EXPERIENCE);
   }
    @Test
    public void testAxeLosesDurabilityAfterAttack() {
         axe.attack(dummy);
         Assert.assertEquals(AXE_ATTACK - 1, axe.getDurabilityPoints());
    }

    @Test(expected = IllegalStateException.class)
    public void testAxeAttackThrowsIfAxeIsBroken() {
        brokenAxe.attack(dummy);
    }

}