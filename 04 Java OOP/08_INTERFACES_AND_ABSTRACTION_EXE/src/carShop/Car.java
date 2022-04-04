package carShop;

import java.io.Serializable;

public interface Car extends Serializable { // така го правим Serializable
    int TIRES = 4;


    // никога не пишем тяло на метод в интерфейс, всички са абстрактни
    // всички са публични в повечето случаи, защото се изпозват на вън
    // protected - забразено
    // може да е без access modifier - default == public
    // Трябва да правиш това, не ме интерсува КАК и КОЙ точно ще го прави
    String getModel();
    String getColor();
    Integer getHorsePower();
    String countryProduced();


}
