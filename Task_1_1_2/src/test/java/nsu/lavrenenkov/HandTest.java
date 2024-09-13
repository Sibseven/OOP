package nsu.lavrenenkov;

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

        assert hand.cards.size() == 2;
    }
}
