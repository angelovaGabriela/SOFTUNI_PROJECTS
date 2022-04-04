package java_advanced_training.vetClinic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Clinic {
    private int capacity;
    private List<Pet> data;

    public Clinic(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();

    }

    public void add(Pet pet) {
        if (this.data.size() < this.capacity){
            this.data.add(pet);
        }
    }
    public boolean remove(String name){
        int size = data.size();

        data.removeIf(pet -> pet.getName().equals(name));
        return size != data.size();

    }
    public Pet getPet(String name, String owner){
    for (Pet pet : data) {
        if (pet.getName().equals(name) && pet.getOwner().equals(owner)){
            return pet;
        }
    }
    return null;
    }
    public Pet getOldestPet(){
        return this.data.stream().max(Comparator.comparingInt(Pet::getAge)).get();
    }
    public int getCount(){
        return data.size();
    }
    public String getStatistics(){
        StringBuilder builder = new StringBuilder();
        builder.append("The clinic has the following patients:").append(System.lineSeparator());
        data.forEach(pet -> builder.append(pet.getName()).append(" ").append(pet.getOwner()).append(System.lineSeparator()));

        return builder.toString();
    }

}
