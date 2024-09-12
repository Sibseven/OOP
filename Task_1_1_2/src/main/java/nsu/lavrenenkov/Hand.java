package nsu.lavrenenkov;

import java.util.ArrayList;
import java.util.Objects;

public class Hand {
    ArrayList<Card> cards;

    public Hand(){
        this.cards = new ArrayList<>();
    }
    public void takeCard(Deck deck) {
        cards.add(deck.deal());
    }

    public int checkValues() {
        int sym = 0;
        for (Card card: cards) {
            sym += card.value;
        }
        if (sym > 21) {
            for (Card ace: cards) {
                if(Objects.equals(ace.rang, "Туз")) {
                    ace.value = 1;
                    sym -= 10;
                }
            }
        }
        return sym;
    }

}
