package Generix_Box;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Box <T extends Comparable <T> > { // Box <Integer>
    List <T> values; // List <Integer>

    public Box() {
        this.values = new ArrayList<>();
    }// constructor

    public void add(T element) {// T - преизползвам дженерика
        values.add(element);
    }

    public void swap(int firstIndex, int secondIndex){
    //T temp = values.get(firstIndex); // временна променлива, която съхранява резутата на първата променлива

    //values.set(firstIndex, values.get(secondIndex));
    //values.set(secondIndex,temp);
        //разменяне на две променливи с помощта на трета
        Collections.swap(values, firstIndex, secondIndex);

    }

    public int countOfGreaterItems(T element){
      return  (int) values.stream().filter(itemFromBox -> itemFromBox.compareTo(element)> 0).count();
   // остават само тези, които са по-големи от дадения елемент след като минат през филтъра

    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (T element : values){
            sb.append(String.format("%s: %s",element.getClass().getName(),
                    element.toString())).append(System.lineSeparator());

        }
        return sb.toString();
    }
}

