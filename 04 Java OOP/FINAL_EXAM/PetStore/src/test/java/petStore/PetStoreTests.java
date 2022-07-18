package petStore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PetStoreTests {
    Animal animal;
    PetStore petStore;

    @Before
    public void setUp() {
        animal = new Animal("dog", 5, 1050.60);
        petStore = new PetStore();
        petStore.addAnimal(animal);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addAnimalShouldTrowEx() {
        petStore.addAnimal(null);
    }

    @Test
    public void animalCountShouldBeOne() {
        int actualSize = 1;
        int petStoreCount = petStore.getCount();

        Assert.assertEquals(actualSize, petStoreCount);
    }

    @Test
    public void getAllAnimalsShouldReturnCorrectCollection() {

        List<Animal> expected = new ArrayList<>();
        expected.add(animal);

        List<Animal> actual = petStore.getAnimals();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getTheMostExpensiveAnimalShouldReturnCorrectAnimal() {

        Animal expensiveAnimal = new Animal("cat", 5, 2000.30);
        petStore.addAnimal(expensiveAnimal);

        Animal actualAnimal = petStore.getTheMostExpensiveAnimal();

        Assert.assertEquals(expensiveAnimal, actualAnimal);

    }

    @Test
    public void allAnimalsWithMaxKg10() {

        Animal animal1 = new Animal("animal1", 7, 1050.60);
        Animal animal2 = new Animal("animal2", 2, 1050.60);
        Animal animal3 = new Animal("animal3", 9, 1050.60);

        List<Animal> expectedAnimals = new ArrayList<>();
        expectedAnimals.add(animal1);
        expectedAnimals.add(animal3);

        petStore.addAnimal(animal1);
        petStore.addAnimal(animal2);
        petStore.addAnimal(animal3);

        List<Animal> actualAnimals = petStore.findAllAnimalsWithMaxKilograms(6);

        Assert.assertEquals(expectedAnimals, actualAnimals);

    }

    @Test
    public void findAllAnimalBySpicesMustReturnMice () {

        Animal mouseMan = new Animal("mouse", 7, 1050.60);
        Animal cat = new Animal("cat", 2, 1050.60);
        Animal mouseWomen = new Animal("mouse", 9, 1050.60);

        petStore.addAnimal(mouseMan);
        petStore.addAnimal(cat);
        petStore.addAnimal(mouseWomen);

        List<Animal> expected = new ArrayList<>();
        expected.add(mouseMan);
        expected.add(mouseWomen);

        List<Animal> actual = petStore.findAllAnimalBySpecie("mouse");

        Assert.assertEquals(expected, actual);


    }

}

