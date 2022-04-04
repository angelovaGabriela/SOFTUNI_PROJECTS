package java_advanced_training.groomingSalon;

import java.util.ArrayList;
import java.util.List;

public class GroomingSalon {
    private List<Pet> data;
    private int capacity;

    public GroomingSalon(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public void add(Pet pet) {
        if (data.size() < this.capacity) {
            this.data.add(pet);
        }
    }

    public boolean remove(String name) {
        int sizeBeforeRemove = data.size();
        this.data.removeIf(e -> e.getName().equals(name));
        return sizeBeforeRemove != data.size(); // ако е различно връща true, ако е същия размер връща false
    }

    public Pet getPet(String name, String owner) {
        for (Pet pet : data) {
            if (pet.getName().equals(name) && pet.getOwner().equals(owner)) {
                return pet;
            }
        }
        return null;
    }

    public int getCount() {
        return data.size();
    }

    public String getStatistics() {
        StringBuilder builder = new StringBuilder();
        builder.append("The grooming salon has the following clients:").append(System.lineSeparator());
        data.forEach(pet -> builder.append(pet.getName()).append(" ").append(pet.getOwner()).append(System.lineSeparator()));

        return builder.toString();
    }
}
