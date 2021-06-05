public class Player {
    public String name;
    public Deck game_deck;
    public Deck win_deck;

    public Player(String name) {
        this.name = name;
        this.game_deck = new Deck(false);
        this.win_deck = new Deck(false);
    }

    public String getName() {
        return this.name;
    }

    public void addCardToGameDeck(Card card) {
        game_deck.addCard(card);
    }

    public void addCardToWinDeck(Card card) {
        win_deck.addCard(card);
    }

    public Card drawCard() {
        return game_deck.removeTopCard();
    }

    public boolean outOfCards() {
        if (win_deck.isEmpty() == true && game_deck.isEmpty() == true)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
