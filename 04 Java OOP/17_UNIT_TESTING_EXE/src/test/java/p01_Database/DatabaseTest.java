package p01_Database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class DatabaseTest {

    //Test the constructor
    //1. Does the constructor can create a Database instance (Happy path)
    //2. If the constructor takes more than 16 elements -> OperationNotSupportedException


    private static final Integer[] NUMBERS = {1,2,3,4};
    private static final Integer[] LARGE_ARRAY = new Integer[17];
    private static final Integer[] SMALL_ARRAY = new Integer[0];
    private Database database;

    @Before
    public void prepare() throws OperationNotSupportedException {
        database = new Database(NUMBERS);
    }

    @Test
    public void testConstructorShouldCreateValidDatabase() throws OperationNotSupportedException {
        Integer[] dbElements = database.getElements();

        Assert.assertEquals("Count of db elements is incorrect", dbElements.length, NUMBERS.length);
        for (int i = 0; i < NUMBERS.length; i++) {
            Assert.assertEquals("We have different elements in the database", dbElements[i], NUMBERS[i]);
        }
    }


    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorShouldThrowExceptionForMoreThan16Elements() throws OperationNotSupportedException {
        database = new Database(LARGE_ARRAY);
        }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorThrowsExceptionLessThanElements() throws OperationNotSupportedException {
        new Database(SMALL_ARRAY);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddThrowsExceptionWithNullArgument() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void testAddShouldAddElement() throws OperationNotSupportedException {
        database.add(6);
        Integer[] dbElements = database.getElements();
        Assert.assertEquals("The element isn't added.",dbElements.length, NUMBERS.length + 1);
        Assert.assertEquals("The last element isn't 6", dbElements[dbElements.length-1], Integer.valueOf(6));


    }

    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveShouldThrowException() throws OperationNotSupportedException {
        for (int i = 0; i < NUMBERS.length; i++) {
            database.remove();
        }
        database.remove();
    }

    @Test
    public void testRemoveShouldRemoveLastElement() throws OperationNotSupportedException {
        database.remove();
        Integer[] dbElements = database.getElements();
        Assert.assertEquals("Element isn't removed",NUMBERS.length-1,dbElements.length);
        Assert.assertEquals(Integer.valueOf(NUMBERS.length - 1), dbElements[dbElements.length - 1]);


    }
    }

