package exams.hotel;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    //•	name: String
//•	capacity: int
    private String name;
    private int capacity;
    private List<Person> roster;

    public Hotel(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        roster = new ArrayList<>();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Person> getRoster() {
        return roster;
    }

    public void setRoster(List<Person> roster) {
        this.roster = roster;
    }

    //Method add(Person person) - adds an entity to the roster if there is room for it
    public void add(Person person) {
        if (this.roster.size() < this.capacity) {
            this.roster.add(person);
        }

    }
    //Method remove(String name) -
    // removes a person by given name,
    // if such exists, and returns boolean
    public boolean remove(String name){
            return this.roster.removeIf(p -> p.getName().equals(name));
    }

    //Method getPerson(String name, String hometown)
    // – returns the roster with the given name
    // and hometown or null if there is no such person.
    public Person getPerson(String name, String hometown){
        return this.roster.stream()
                .filter(p -> p.getName().equals(name) && p.getHometown().equals(hometown))
                .findFirst().orElse(null);
    }

    //Getter getCount() – returns the number of people.

    public int getCount(){
        return this.roster.size();
    }

    //•	getStatistics() – returns a String in the following format:
    //o	"The people in the final_regular_exam_java_advanced.hotel {name} are:
    //{Person1}
    //{Person2}
    //(…)"
    public String getStatistics(){
        StringBuilder stringBuilder = new StringBuilder
                (String.format("The people in the final_regular_exam_java_advanced.hotel %s are:%n", this.name));
        this.roster.forEach(e -> stringBuilder.append(e).append(System.lineSeparator()));

        return stringBuilder.toString().trim();
    }




}
