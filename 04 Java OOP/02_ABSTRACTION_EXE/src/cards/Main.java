package cards;

public class Main {
    public static void main(String[] args) {
        System.out.println("cardRank.Card Suits: ");
//итерирам през енумерацията и принтирам
       for (CardSuits cardSuit : CardSuits.values()){ // .values() връща всички елементи на енумерацията
           System.out.printf("Ordinal value: %d; Name value: %s%n", cardSuit.ordinal(), cardSuit.name());
       }
    }
}
