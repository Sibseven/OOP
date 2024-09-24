package ru.nsu.lavrenenkov.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.Nullable;



/**
 * Deck class.
 *
 * @author vadim_lavrenenkov
 *
 * @version 1.0
 */
public class Deck {
    private final List<Card> cardList;

    /**
     * Modified builder that fill deck on creation.
     */
    public Deck() {
        cardList = new ArrayList<>();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                cardList.add(new Card(rank, suit));
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
     * Method for dealing card by removing it from deck.
     *
     * @return card that removed from the deck
     */

    public @Nullable Card deal() {
        if (cardList.isEmpty()) {
            return null;
        }
        return (cardList.remove(cardList.size() - 1));
    }

    /**
     * getter for deck size.
     *
     * @return size of a deck
     */
    public int getSize() {
        return this.cardList.size();
    }

}
