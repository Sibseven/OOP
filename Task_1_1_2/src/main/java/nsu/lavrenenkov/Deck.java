package nsu.lavrenenkov;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    List<Card> cardList;

    public Deck() {
        cardList = new ArrayList<>();
        String[] rangs = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Валет", "Дама", "Король", "Туз"};
        String[] suits = {"Пики", "Трефы", "Черви", "Бубны"};
        int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
        for(String suit: suits) {
            int i = 0;
            for(String rang: rangs) {
                cardList.add(new Card(rang, suit, values[i]));
                i++;
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cardList);
    }

    public Card deal() {
        if (cardList.isEmpty()) {
            return null;
        }
        return (cardList.removeLast());
    }


}
