package ru.nsu.lavrenenkov.blackjack;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Class for testing deck class.
 */
public class DeckTest {
    @Test
    void checkBuilder() {
        Deck deck = new Deck();
        assertTrue(deck.getSize() == 52);
    }

    @Test
    void checkDeal() {
        Deck deck = new Deck();
        Card card = deck.deal();
        assertTrue(deck.getSize() == 51);
    }

    @Test
    void checkShuffle() {
        Deck deck = new Deck();
        Deck deckShuffled = new Deck();
        deckShuffled.shuffle();
        assert !deck.equals(deckShuffled);
    }
}
