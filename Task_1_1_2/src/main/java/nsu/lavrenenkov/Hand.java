package nsu.lavrenenkov;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Hand class.
 * Cards are stored in ArrayList
 *
 *  @author vadim_lavrenenkov
 *
 *  @version 1.0
 */

public class Hand {
    ArrayList<Card> cards;

    /**
     * Builder for hand.
     */
    public Hand() {
        this.cards = new ArrayList<>();
    }

    /**
     * Method for taking one card from the deck.
     *
     * @param deck - deck to take card from
     */
    public void takeCard(Deck deck) {
        cards.add(deck.deal());
    }

    /**
     * Method for summing hand`s cards value.
     * If sum of hand if more than 21, value of aces(if exists) switches to 1.
     *
     * @return sum of hand`s cards value
     */
    public int checkValues() {
        int sym = 0;
        for (Card card: cards) {
            sym += card.value;
        }
        if (sym > 21) {
            for (Card ace: cards) {
                if(Objects.equals(ace.rang, "Туз")) {
                    ace.value = 1;
                    sym -= 10;
                }
            }
        }
        return sym;
    }

}
