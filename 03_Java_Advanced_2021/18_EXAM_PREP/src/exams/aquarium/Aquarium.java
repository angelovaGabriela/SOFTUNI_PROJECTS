package exams.aquarium;

import java.util.LinkedHashSet;

public class Aquarium {
    private String name;
    private int capacity;
    private int size; // volume of the pool
    private LinkedHashSet<Fish> fishInPool;

    public Aquarium(String name, int capacity, int size) {
        this.name = name;
        this.capacity = capacity;
        this.size = size;
        this.fishInPool = new LinkedHashSet<>();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }

    public int getFishInPool() {
        return this.fishInPool.size();
        //returns the number of fish in the current pool
    }

    public void add(Fish fish) {
        if (capacity > fishInPool.size() && !(fishInPool.contains(fish))) {
            this.fishInPool.add(fish);
            // използвам сет, няма да добави рибата ако името и вече е там
        }
    }
    public boolean remove(String name){
        int sizeBeforeRemove = fishInPool.size(); // проверявам размера преди премахването

        this.fishInPool.removeIf(e -> e.getName().equals(name));

        return sizeBeforeRemove != fishInPool.size(); // ако е премахнат връща true ако не е false
    }

    public Fish findFish(String name){

        for (Fish fish : fishInPool) {
            if (fish.getName().equals(name)){
                return fish;
            }
        }
        return null;
    }

    public String report(){

        StringBuilder builder = new StringBuilder();
        builder.append("Aquarium: ")
                .append(this.name).append(" ^ ").append("Size: ").append(this.size).append(System.lineSeparator());
        fishInPool.forEach(fish -> builder.append(fish).append(System.lineSeparator()));

        return builder.toString();
    }
}
