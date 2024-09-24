package nsu.lavrenenkov;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Class for testing card class.
 */
public class CardTest {
    @Test
    void checkBuilder() {
        Card card = new Card(Card.Rank.ACE, Card.Suit.Clubs);
        assertTrue(card.getValue() == 11
                   && card.getRank().equals("Туз")
                   && card.getSuit().equals("Трефы"));
    }

    @Test
    void checkToStringOverride() {
        Card card = new Card(Card.Rank.ACE, Card.Suit.Clubs);
        assertTrue(card.toString().equals("Туз Трефы(11)"));
    }

    @Test
    void checkGetters() {
        Card card = new Card(Card.Rank.ACE, Card.Suit.Clubs);
        assertTrue(card.getValue() == 11);
        assertTrue(card.getRank() == "Туз");
        assertTrue(card.getSuit() == "Трефы");
    }

    @Test
    void checkSetters() {
        Card card = new Card(Card.Rank.ACE, Card.Suit.Clubs);
        card.setValue(2);
        assertTrue(card.getValue() == 2);
    }
}
