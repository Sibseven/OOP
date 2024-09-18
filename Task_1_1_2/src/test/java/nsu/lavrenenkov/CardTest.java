package nsu.lavrenenkov;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Class for testing card class.
 */
public class CardTest {
    @Test
    void checkBuilder() {
        Card card = new Card("туз", "треф", 125);
        assertTrue(card.getValue() == 125
                   && card.getRang().equals("туз")
                   && card.getSuit().equals("треф"));
    }

    @Test
    void checkToStringOverride() {
        Card card = new Card("туз", "треф", 125);
        assertTrue(card.toString().equals("туз треф(125)"));
    }

    @Test
    void checkGetters() {
        Card card = new Card("a", "b", 1);
        assertTrue(card.getValue() == 1);
        assertTrue(card.getRang() == "a");
        assertTrue(card.getSuit() == "b");
    }

    @Test
    void checkSetters() {
        Card card = new Card("a", "b", 1);
        card.setValue(2);
        assertTrue(card.getValue() == 2);
    }
}
