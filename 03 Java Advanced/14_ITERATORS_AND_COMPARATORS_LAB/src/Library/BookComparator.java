package Library;

import java.util.Comparator;

public class BookComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        int compared = o1.getTitle().compareTo(o2.getTitle());

        if (compared == 0){
            compared = Integer.compare(o1.getYear(), o2.getYear());
        }
        return compared;
        // сравни ги по заглавие и ако заглавието е равно ги сравни и по години

    }
}
