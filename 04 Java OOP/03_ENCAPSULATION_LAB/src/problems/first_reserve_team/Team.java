package problems.first_reserve_team;

import problems.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Team {
    private String name;
    private List<Person> firstTeam;
    private List<Person> reserveTeam;


    public Team(String name) {
        this.setName(name);
        this.firstTeam = new ArrayList<>();
        this.reserveTeam = new ArrayList<>();
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getName(){
       return name;
    }

    public void addPlayer(Person person){
        if (person.getAge() < 40){
            this.firstTeam.add(person);
        } else {
            this.reserveTeam.add(person);
        }
    }
    public List<Person> getFirstTeam(){
        // лист, който не може да бъде модифициран след печатането
      return Collections.unmodifiableList(firstTeam);
    }
    public List<Person> getReserveTeam(){
        return Collections.unmodifiableList(reserveTeam);
    }
}
