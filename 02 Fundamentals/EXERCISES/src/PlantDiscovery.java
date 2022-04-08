// Plant
// name -> Rose
// rarity -> 12
//ratings -> 8, 9, 15, 1

//Read n number of plants/rarity
// replace rarity of existing plants

// 3 commands
// Rate -> gives rating to a plant
// Update -> update plant's rarity
// Reset -> remove all ratings of a plant

//calculate average rating of a plant
//sort by 2 criteria and print the result

import java.util.*;

//1. Map<String, List<Integer>>
//2. Map<String, List<Integer>> + Map<String, List<Integer>
//3. Single Map + custom class Map<String, PlantData>
//4. List<PlantData>


public class PlantDiscovery {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int plantNumber = Integer.parseInt(scanner.nextLine());

        Map<String, PlantData> currentPlants = new LinkedHashMap<>();

        for (int i = 0; i < plantNumber; i++) {
            String plantText = scanner.nextLine();
            String[] plantParts = plantText.split("<->");

            String plantName = plantParts[0];
            int plantRarity = Integer.parseInt(plantParts[1]);

            PlantData newPlant = new PlantData(new ArrayList<>(), plantRarity);
            currentPlants.put(plantName, newPlant);
        }

        String command = scanner.nextLine();
        while (!command.equals("Exhibition")) {

            String[] commandParts = command.split(": | - "); // split type for "Update: {plant} - {new_rarity}"
            String commandName = commandParts[0];
            String plantName = commandParts[1];

            switch (commandName) {
                case "Rate":
                    int rating = Integer.parseInt(commandParts[2]);
                    currentPlants.get(plantName).addRating(rating);
                    break;
                case "Reset":
                    currentPlants.get(plantName).removeAllRatings();
                    break;
                case "Update":
                    int rarity = Integer.parseInt(commandParts[2]);
                    currentPlants.get(plantName).setRarity(rarity);
                    break;
                default:
                    System.out.println("error");
                    break;
            }

            command = scanner.nextLine();
        }

        System.out.println("Plants for the exhibition:");
        currentPlants.entrySet().stream().sorted((p1,p2) -> {
            //
            int result = Integer.compare(p2.getValue().getRarity(), p1.getValue().getRarity()); // DESC order

            if (result == 0) { // they have the same rarity, so we must sort them by rating
                result = Double.compare(p2.getValue().getAverageRating(), p1.getValue().getAverageRating()); // DESC order
            }
            return result;

        }).forEach(p -> System.out.printf("- %s; Rarity: %d; Rating: %.2f%n",
                p.getKey(),
                p.getValue().getRarity(),
                p.getValue().getAverageRating()));
    }

}

class PlantData {
    private List<Integer> ratings;
    private int rarity;

    public void addRating(int rating) {
        this.ratings.add(rating);

    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public void removeAllRatings() {
        this.ratings.clear();
    }

    public PlantData(List<Integer> ratings, int rarity) {
        this.ratings = ratings;
        this.rarity = rarity;
    }

    public int getRarity() {
        return rarity;
    }

    public double getAverageRating() {
        double sumOfAllRatings = 0;
        for (Integer rating : this.ratings) {
            sumOfAllRatings += rating;
        }
        if (sumOfAllRatings == 0){
            return 0;
        } else {
            return sumOfAllRatings / this.ratings.size();
        }
    }
}

