package randomArrayList;

import java.util.ArrayList;
import java.util.Random;

public class RandomArrayList<E> extends ArrayList<E> {
    public E getRandomElement(){
        // за да взема произволен елемент, трябва да взема някой на индексите от 0 до дължината на листа
         int start = 0;
         int end = super.size(); // взимам размера на листа от базовия клас

        Random rand  = new Random(); // създавам обект от базовия клас
        int randIndex =  rand.nextInt(end); // число което е до последния зададен индекс

        return remove(randIndex); // върни премахнатия елемент от случайния индекс
    }
}
