package CustomList;

import java.util.Collections;

public class Sorter {
    public static <T extends Comparable<T>> void sort(CustomList <T> customList){
        for (int i = 0; i < customList.size(); i++) {

            T element = customList.get(i);
            for (int j = i+1; j <  customList.size(); j++) {
                T nextElement = customList.get(j);
                if (element.compareTo(nextElement) > 0){
                    customList.swap(i,j);
                }
            }
        }
    }
}
