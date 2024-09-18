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
    private final String rang;
    private final String suit;
    private int value;

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

    /**
     * getter for rang.
     *
     * @return rang of a card
     */
    public String getRang() {
        return rang;
    }

    /**
     * getter for value.
     *
     * @return value of a card
     */
    public int getValue() {
        return value;
    }

    /**
     * getter for suit.
     *
     * @return suit of a card
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Setter for value of card.
     *
     * @param i - value of card (1 - 11)
     */
    public void setValue(int i) {
        if(i >= 1 && i <= 11) {
            this.value = i;
        }
    }
}
