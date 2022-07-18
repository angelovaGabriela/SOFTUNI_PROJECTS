package petStore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AnimalTest {
    Animal animal;

    @Before
    public void setUp() {
        animal = new Animal("dog", 5, 1050.60);
        animal.setAge(5);
    }

    @Test
    public void animalGetAgeShouldReturnAge() {
        int expected = 5;
        int actual = animal.getAge();

        Assert.assertEquals(expected, actual);

    }
}
