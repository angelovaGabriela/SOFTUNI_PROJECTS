package computers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ComputerManagerTests {
    private Computer computer;
    private ComputerManager computerManager;
    @Before
    public void prepare() {
        computerManager = new ComputerManager();
        computer = new Computer("ASUS", "ZenBook", 1230.40);
        computerManager.addComputer(computer);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addExistingComputerThrowsException() {
        computerManager.addComputer(computer);
    }

    @Test
    public void getComputersReturnUnmodifiableList() {
        List<Computer> computers = new ArrayList<>();
        computers.add(this.computer);


      List<Computer> expected = Collections.unmodifiableList(computers);
       List<Computer> actual = Collections.unmodifiableList(computerManager.getComputers());

        Assert.assertEquals("Returns different collection.", expected, actual);

    }

    @Test
    public void getCountShouldReturnOne() {
        int actual = computerManager.getCount();
        Assert.assertEquals("Not valid count!", 1, actual);
    }

    @Test
    public void removingComputer() {
        int before = this.computerManager.getCount();
        this.computerManager.removeComputer("ASUS", "ZenBook");

        int after = this.computerManager.getCount();
        Assert.assertNotEquals("Computer isn't removed", before, after);
    }

    @Test(expected = IllegalArgumentException.class)
    public void computerCannotBeNull() {
        computerManager.getComputer(null, null);
    }


    @Test
    public void priceMustBe123040 () {
        double price = computer.getPrice();
        Assert.assertEquals("Invalid price!", 1230.40, price, 0.0);
    }

}