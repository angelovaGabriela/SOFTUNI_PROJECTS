package cats;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
}
