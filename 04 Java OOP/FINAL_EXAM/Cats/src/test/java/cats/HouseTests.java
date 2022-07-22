package cats;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HouseTests {
    private Cat cat;
    private House house;

    @Before
    public void prepare() {
        cat = new Cat("Vanesa");
        house = new House("VanesaHouse",219);

    }

    @Test
    public void testHouseConstructorShouldReturnValidHouse() {
        House expected = new House("VanesaHouse", 219);
    }

    @Test
    public void testGetHouseNameMustReturnCorrectName() {
        String expected = "VanesaHouse";
        String actual = house.getName();

        Assert.assertEquals("The name isn't correct", expected, actual);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameMustThrowExceptionWhenNull() {
       House newHouse = new House(null, 123);
    }

    @Test
    public void getCapacityShouldReturn219() {
        int expected = 219;
        int actual = house.getCapacity();

        Assert.assertEquals("Capacity isn't correct", expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCapacityTestBelowOrEqualTo0MustThrowException() {
        House pugHouse = new House("Pug house", -2);
    }

    @Test
    public void getCountTestShouldReturn1() {
        int expected = 1;
        house.addCat(cat);
        int actual = house.getCount();

        Assert.assertEquals("Count of cats isn't correct.", expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenHouseIsFullAddCatMustThrowException() {
        House naughtyCats = new House("Naughty cats", 1);
        naughtyCats.addCat(cat);
        Cat extraCat = new Cat("Pongo");
        naughtyCats.addCat(extraCat);
    }

    @Test
    public void removeCatTest() {
        Cat extraCat = new Cat("Pongo");

        house.addCat(cat);
        house.addCat(extraCat);

        house.removeCat("Pongo");

        int expected = 1;
        int actual = house.getCount();

        Assert.assertEquals("Cat isn't removed.", expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeCatWithNullNameMustThrowException() {
        house.removeCat(null);
    }

    @Test
    public void catForSaleMustReturnTheCorrectCat() {
        house.addCat(cat);
        Cat expected = cat;
        Cat actual = house.catForSale("Vanesa");

        Assert.assertEquals(
                "There isn't a cat with name Vanesa",
                expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void sellingCatWithNullNameShouldThrowException() {
        house.catForSale(null);
    }

    @Test
    public void statisticsTestShouldReturnTheActualCats() {

        house.addCat(cat);

        String expected = "The cat Vanesa is in the house VanesaHouse!";
        String actual = house.statistics();

        Assert.assertEquals(
                "Cats are not living in this house.",
                expected, actual);
    }

    @Test
    public void isHungryShouldReturnTrue() {
      cat.setHungry(true);
      boolean hungry = cat.isHungry();

      Assert.assertTrue(hungry);

    }
}
