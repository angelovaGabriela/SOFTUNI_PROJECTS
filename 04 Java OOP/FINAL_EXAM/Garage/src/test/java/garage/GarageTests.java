package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GarageTests {

   private Car car;
   private Garage garage;


   @Before
    public void setUp() {
       car = new Car("Porsche", 379, 106100.90);
       garage = new Garage();
       garage.addCar(car);
   }

   @Test
   public void getCountTest() {
      List<Car> garageTest = new ArrayList<>();
      Car testCar = new Car("Mercedes", 168, 130900);
      garageTest.add(car);

      int expected = garageTest.size();
      int actual = garage.getCount();

      Assert.assertEquals(expected, actual);
   }
   @Test
    public void addCarMethodTest(){
       Car testCar = new Car("Mercedes", 168, 130900);
       garage.addCar(testCar);
       int actual = garage.getCount();

        Assert.assertEquals(2, actual);
   }


   @Test(expected = IllegalArgumentException.class)
   public void addNULLTest() {

      garage.addCar(null);
   }

   @Test
   public void getCarsTest() {
      List<Car> garageTest = new ArrayList<>();
      Car testCar = new Car("Mercedes", 240, 130900);

      garageTest.add(car);
      garageTest.add(testCar);

      garage.addCar(testCar);

      List<Car> expected = Collections.unmodifiableList(garageTest);
      List<Car> actual = garage.getCars();

      Assert.assertEquals(expected, actual);

   }

   // Start here
   @Test
   public void findAllCarsWithMaxSpeedAbove240hp() {
      List<Car> expected = new ArrayList<>();
      expected.add(car);
      Car testCar = new Car("Mercedes", 240, 9990900);
      garage.addCar(testCar);

      List<Car> actual = garage.findAllCarsWithMaxSpeedAbove(240);

      Assert.assertEquals(expected, actual);
   }

   @Test
   public void getTheMostExpensiveCarTest() {
      Car expected = new Car("Mercedes", 240, 9990900);
      garage.addCar(expected);

      Car actual = garage.getTheMostExpensiveCar();
      Assert.assertEquals(expected, actual);
   }

   @Test
   public void findAllCarsByBrandTest() {
      List<Car> expected = new ArrayList<>();
      Car testCar1 = new Car("Mercedes", 240, 9990900);
      Car testCar2 = new Car("Mercedes", 240, 9990900);

      expected.add(testCar1);
      expected.add(testCar2);

      garage.addCar(testCar1);
      garage.addCar(testCar2);

      List<Car> actual = garage.findAllCarsByBrand("Mercedes");

      Assert.assertEquals(expected, actual);
   }
}