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

}
