package nsu.lavrenenkov;

/**
 * Class for storing single card.
 * Contains 2 strings for rang and suit of card
 * And one int to determine blackjack value of a card.
 *
 *  @author vadim_lavrenenkov
 *
 *  @version 1.0
 *
 */
public class Card {
    String rang;
    String suit;
    int value;

    /**
     * Card builder.
     *
     * @param rang - rang of card.
     *
     * @param suit - suit of card.
     *
     * @param value - blackjack value of card.
     */
    public Card(String rang, String suit, int value) {
        this.rang = rang;
        this.suit = suit;
        this.value = value;
    }

    /**
     * Redefinition of toString method of card.
     *
     * @return formatted string
     */
    @Override
    public String toString() {
        return rang + " " +  suit  + "(" + value + ")";
    }
}
