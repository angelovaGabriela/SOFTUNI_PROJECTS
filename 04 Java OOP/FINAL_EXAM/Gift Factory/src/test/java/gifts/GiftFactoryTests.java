package gifts;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class GiftFactoryTests {

    private Gift gift;
    private GiftFactory giftFactory;

    @Before
    public void prepare() {
        giftFactory = new GiftFactory();
        gift = new Gift("PLUSH", 4.7);


    }

    @Test
    public void giftFactoryShouldHaveOneElement() {

       giftFactory.createGift(gift);
       int actualCount = giftFactory.getCount();

        Assert.assertEquals(1, actualCount);
    }

    @Test (expected = IllegalArgumentException.class)
    public void sameGiftTypesAreNotAllowed() {
        Gift newGift = new Gift("PLUSH", 8.9);
        giftFactory.createGift(gift);
        giftFactory.createGift(newGift);
    }
    @Test (expected = NullPointerException.class)
    public void canNotRemoveWhenGiftTypeNull() {
        giftFactory.removeGift(null);
    }
    @Test
    public void removeGiftTest() {
        giftFactory.createGift(gift);
        Assert.assertTrue(giftFactory.removeGift("PLUSH"));
    }

    @Test
    public void giftWithLessMagicShouldBePug() {
        Gift pugGift = new Gift("PUG", 2.1);
        giftFactory.createGift(gift);
        giftFactory.createGift(pugGift);

        Gift actualGift = giftFactory.getPresentWithLeastMagic();
        Assert.assertEquals(pugGift, actualGift);
    }

    @Test
    public void getGiftByTypeTest() {
        giftFactory.createGift(gift);
        Gift actualGift = giftFactory.getPresent("PLUSH");

        Assert.assertEquals(gift, actualGift);
    }

    @Test
    public void getAllGiftsMustBeEqualToExpectedCollection() {
     Collection<Gift> expected = new ArrayList<>();
     expected.add(gift);
     Collection<Gift> gifts = Collections.unmodifiableCollection(expected);

     giftFactory.createGift(gift);

     Collection<Gift> actual = this.giftFactory.getPresents();

     Assert.assertTrue(gifts.containsAll(actual));
    }
}
