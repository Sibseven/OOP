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
        assertTrue(card.getValue() == 125 && card.getRang().equals("туз") && card.getSuit().equals("треф"));
    }

    @Test
    void checkToStringOverride() {
        Card card = new Card("туз", "треф", 125);
        assertTrue(card.toString().equals("туз треф(125)"));
    }
}
