import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class Deck {
    ArrayList<Card> deck = new ArrayList<>();

    /**class builder, if paramter is true the deck is full, else it's empty
     * @param parameter
     */
    public Deck(boolean parameter) {
        if (parameter == true) {
            for (shape value : shape.values()) {
                for (int i = 1; i <= 13; i++) {
                    Card add_card = new Card(i, value);
                    deck.add(add_card);
                }
            }
        }
    }

    /**
     * @return last card of the deck
     */
    public Card getLastCard() {
        return deck.get(deck.size() - 1);
    }

    /** adds card to the top of the deck
     * @param card
     */
    public void addCard(Card card) {
        deck.add(deck.size(), card);
    }

    public Card removeTopCard() {
        if (deck.isEmpty() != true) {
            Card temp = deck.get(deck.size() - 1);
            deck.remove(deck.size() - 1);
            return temp;
        } else
            return null;
    }

    /**
     * @return whether the deck is empty
     */
    public boolean isEmpty() {
        return deck.isEmpty();
    }

    /**
     * shuffles randomly the deck
     */
    public void shuffle() {
        for (int i = 0; i < 50; i++) {
            int index1 = Main.rnd.nextInt(deck.size());
            int index2 = Main.rnd.nextInt(deck.size());
            Collections.swap(deck,index1,index2);
//            deck.add(index1, deck.get(index2));
//            Card temp1 = deck.remove(index1 + 1);
//            deck.add(index2, deck.get(index1));
//            Card temp2 = deck.remove(index2 + 1);
        }
    }
}
