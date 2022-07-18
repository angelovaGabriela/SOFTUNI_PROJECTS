package p04_BubbleSort;

import org.junit.Assert;
import org.junit.Test;
import p04_BubbleSortTest.Bubble;

public class BubbleTest {

    @Test
    public void testBubbleSort() {
        int[] numbers = {1,5,3,8,0,-5};
        Bubble.sort(numbers);
        int[] expectedSortedArray = {-5,0,1,3,5,8};

        Assert.assertArrayEquals("The sorting isn't correct.",expectedSortedArray, numbers);

    }
}
