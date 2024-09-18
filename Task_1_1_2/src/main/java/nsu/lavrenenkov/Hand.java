package nsu.lavrenenkov;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Hand class.
 * Cards are stored in ArrayList
 *
 *  @author vadim_lavrenenkov
 *
 *  @version 1.0
 */

public class Hand {
    private ArrayList<Card> cards;

    /**
     * Builder for hand.
     */
    public Hand() {
        this.cards = new ArrayList<>();
    }

    /**
     * Method for taking one card from the deck.
     *
     * @param deck - deck to take card from
     */
    public void takeCard(Deck deck) {
        cards.add(deck.deal());
    }

    /**
     * Method for summing hand`s cards value.
     * If sum of hand if more than 21, value of aces(if exists) switches to 1.
     *
     * @return sum of hand`s cards value
     */
    public int checkValues() {
        int sym = 0;
        for (Card card : cards) {
            sym += card.getValue();
        }
        if (sym > 21) {
            for (Card ace : cards) {
                if (Objects.equals(ace.getRang(), "Туз")) {
                    ace.setValue(1);
                    sym -= 10;
                }
            }
        }
        return sym;
    }

    /**
     * Redefinition of toString method of hand.
     *
     * @return formatted list of cards
     */
    @Override
    public String toString(){
        return this.cards.toString();
    }

    /**
     * method for getting N`s card as string.
     *
     * @param i - number of card in hand
     *
     * @return N card in String
     */
    public String getNCard(int i){
        if(i >= 0 && i < cards.size()){
            return cards.get(i).toString();
        }
        return "";
    }

    /**
     * Method for getting last card from hand.
     *
     * @return last card as string
     */
    public String getLastCard(){
        return cards.get(cards.size() - 1).toString();
    }

    /**
     * getter for hand size.
     *
     * @return size of hand
     */
    public int getSize() {
        return cards.size();
    }
}
