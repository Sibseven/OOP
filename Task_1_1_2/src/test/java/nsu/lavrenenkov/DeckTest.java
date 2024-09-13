package nsu.lavrenenkov;

import org.junit.jupiter.api.Test;

public class DeckTest {
    @Test
    void checkBuilder() {
        Deck deck = new Deck();
        assert deck.cardList.size() == 52;
    }

    @Test
    void checkDeal() {
        Deck deck = new Deck();
        Card card = deck.deal();
        assert deck.cardList.size() == 51;
    }

    @Test
    void checkShuffle() {
        Deck deck = new Deck();
        Deck deckShuffled = new Deck();
        deckShuffled.shuffle();
        assert !deck.equals(deckShuffled);
    }
}
