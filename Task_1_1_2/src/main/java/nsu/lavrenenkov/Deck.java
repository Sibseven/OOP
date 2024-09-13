package nsu.lavrenenkov;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Deck class.
 *
 * @author vadim_lavrenenkov
 *
 * @version 1.0
 */
public class Deck {
    List<Card> cardList;

    /**
     * Modified builder that fill deck on creation.
     */
    public Deck() {
        cardList = new ArrayList<>();
        String[] rangs = {"2", "3", "4", "5", "6", "7", "8", "9", "10",
                           "Валет", "Дама", "Король", "Туз"};
        String[] suits = {"Пики", "Трефы", "Черви", "Бубны"};
        int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
        for (String suit : suits) {
            int i = 0;
            for (String rang : rangs) {
                cardList.add(new Card(rang, suit, values[i]));
                i++;
            }
        }
    }

    /**
     * Method for shuffling deck.
     */
    public void shuffle() {
        Collections.shuffle(cardList);
    }

    /**
     * Method for deailng card by removing it from deck.
     *
     * @return card that removed from the deck
     */
    public Card deal() {
        if (cardList.isEmpty()) {
            return null;
        }
        return (cardList.removeLast());
    }


}
