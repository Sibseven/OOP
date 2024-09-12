package nsu.lavrenenkov;

import org.junit.jupiter.api.Test;

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
