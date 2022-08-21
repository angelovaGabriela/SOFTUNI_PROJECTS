package football;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FootballTeamTests {

    private Footballer footballer;
    private FootballTeam team;

    @Before
    public void prepare() {
        footballer = new Footballer("Daniel");
        team = new FootballTeam("Puggcandia", 4);
        team.addFootballer(footballer);
    }

    @Test
    public void createNewTeamShouldBeSuccessful() {
       team = new FootballTeam("Puggcandia", 4);
    }

    @Test
    public void getNameEqualsPuggcandia() {
        String actual = team.getName();
        Assert.assertEquals("Puggcandia", actual);
    }

    @Test(expected = NullPointerException.class)
    public void setNameNullThrowsException() {
        team = new FootballTeam(null, 0);
    }

    @Test
    public void getVacantPositionsEquals4() {
        int actual = team.getVacantPositions();
        Assert.assertEquals(4, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void vacantPositionsLessThan0ThrowsException() {
     team = new FootballTeam("Puggia", -1);
    }


    @Test
    public void countReturns1() {
        int actual = team.getCount();
        Assert.assertEquals(1, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addMoreThan4Players() {
        Footballer player = new Footballer("Danny");
        Footballer player2 = new Footballer("Mon");
        Footballer player3 = new Footballer("Bob");
        Footballer player4 = new Footballer("Loren");

        team.addFootballer(player);
        team.addFootballer(player2);
        team.addFootballer(player3);
        team.addFootballer(player4);

    }

    @Test
    public void removePlayerReturnsEmptyTeam() {
        team.removeFootballer("Daniel");
        Assert.assertEquals(0, team.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeInvalidPlayerThrowsException() {
        team.removeFootballer("Gabi");
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidFootballerForSaleThrowsException() {
        team.footballerForSale("Vincent");
    }

    @Test
    public void saleFootballer() {
        team.footballerForSale("Daniel");
    }
    @Test
    public void statistics() {
        String statistics = team.getStatistics();
        Assert.assertEquals("The footballer Daniel is in the team Puggcandia.", statistics);
    }

    @Test
    public void playerIsActive() {
        FootballTeam footballTeam = new FootballTeam("MouseHouse", 1);
        Footballer footballer = new Footballer("Kitten");
        footballTeam.addFootballer(footballer);
        footballTeam.footballerForSale("Kitten");

        Assert.assertFalse(footballer.isActive());
    }


}
