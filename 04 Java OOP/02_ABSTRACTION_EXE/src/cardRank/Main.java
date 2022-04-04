package cardRank;

import javax.smartcardio.Card;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // прочитам го от конзолата и го променям в енумерация
        CardRanks cardRank = CardRanks.valueOf(scanner.nextLine());
        // прочитам го от конзолата и го променям в енумерация
        CardSuits cardSuit = CardSuits.valueOf(scanner.nextLine());

        cardRank.Card card = new cardRank.Card(cardSuit, cardRank);
        System.out.printf("Card name: %s of %s; Card power: %d", cardRank, cardSuit, card.getPower());
    }

}
