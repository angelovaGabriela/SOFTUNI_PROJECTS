package rpg_lab;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class AxeTest extends TestCase {

    // Test if weapon loses durability after each attack;

    @Test
    public void testWeaponLosesDurabilityAfterAttack() {

         // Arrange
         Axe axe = new Axe(10, 10);
         Dummy dummy = new Dummy(100, 100);
         // Act
         axe.attack(dummy);

         // Assert - expectations vs reality
        Assert.assertEquals(9, axe.getDurabilityPoints());
    }

}