package ru.nsu.lavrenenkov.blackjack;

/**
 * Class for storing single card.
 * Contains 2 strings for rank and suit of card
 * And one int to determine blackjack value of a card.
 *
 *  @author vadim_lavrenenkov
 *
 *  @version 1.0
 *
 */
public class Card {
    /**
     * enum for card Rank.
     */
    public static enum Rank {
        TWO("2", 2),
        THREE("3", 3),
        FOUR("4", 4),
        FIVE("5", 5),
        SIX("6", 6),
        SEVEN("7", 7),
        EIGHT("8", 8),
        NINE("9", 9),
        TEN("10", 10),
        JACK("Валет", 10),
        QUEEN("Дама", 10),
        KING("Король", 10),
        ACE("Туз", 11);

        private final String rank;
        private final int value;

        Rank(String rank, int value) {
            this.rank = rank;
            this.value = value;
        }

        public String getRank() {
            return this.rank;
        }

        public int getValue() {
            return this.value;
        }

    }

    /**
     * Enum for card suit.
     */
    public static enum Suit {
        Diamonds("Бубны"),
        Hearts("Черви"),
        Spades("Пики"),
        Clubs("Трефы");

        private final String value;

        Suit(String number) {
            this.value = number;
        }

        public String getValue() {
            return this.value;
        }
    }

    private final Rank rank;
    private final Suit suit;
    private int value;

    /**
     * Card builder.
     *
     * @param rank - rank of card.
     *
     * @param suit - suit of card.
     *
     */
    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
        this.value = rank.getValue();
    }

    /**
     * Redefinition of toString method of card.
     *
     * @return formatted string
     */
    @Override
    public String toString() {
        return rank.getRank() + " " +  suit.getValue()  + "(" + value + ")";
    }

    /**
     * getter for rank.
     *
     * @return rank of a card
     */
    public String getRank() {
        return rank.getRank();
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
        return suit.getValue();
    }

    /**
     * Setter for value of card.
     *
     * @param i - value of card (1 - 11)
     */
    public void setValue(int i) {
        if (i >= 1 && i <= 11) {
            this.value = i;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
