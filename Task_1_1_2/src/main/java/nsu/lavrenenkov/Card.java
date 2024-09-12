package nsu.lavrenenkov;

public class Card {
    String rang;
    String suit;
    int value;

    public Card(String rang, String suit, int value) {
        this.rang = rang;
        this.suit = suit;
        this.value = value;
    }

    @Override
    public String toString() {
        return rang + " " +  suit  + "(" + value + ")";
    }
}
