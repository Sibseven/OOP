package ru.nsu.lavrenenkov.blackjack;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Class for testing hand class.
 */
public class HandTest {

    @Test
    void checkTakeCard() {
        Hand hand = new Hand();
        Deck deck = new Deck();

        hand.takeCard(deck);
        hand.takeCard(deck);

        assertTrue(hand.getSize() == 2);
    }
}
