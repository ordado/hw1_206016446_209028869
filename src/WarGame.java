public class WarGame {
    Player player1;
    Deck player1_deck;
    Player player2;
    Deck Player2_deck;

    /**
     * class builder consiss two player objects and two deck objects
     *
     * @param name1
     * @param name2
     */
    public WarGame(String name1, String name2) {
        player1 = new Player(name1);
        player2 = new Player(name2);
        player1_deck = new Deck(false);
        Player2_deck = new Deck(false);
    }

    /**
     * initializes the game (so it's ready to begin)
     */
    public void initializeGame() {
        Deck cards = new Deck(true);
        cards.shuffle();
        if (player1.getName().compareTo(player2.getName()) > 0) {
            for (int i = 0; i < 52; i++) {
                if (i % 2 == 1)
                    this.player1.game_deck.addCard(cards.removeTopCard());
                else
                    this.player2.game_deck.addCard(cards.removeTopCard());
            }
        } else {
            for (int i = 0; i < 52; i++) {
                if (i % 2 == 0)
                    this.player1.game_deck.addCard(cards.removeTopCard());
                else
                    this.player2.game_deck.addCard(cards.removeTopCard());
            }
        }
    }

    /**
     * @param player_3
     * @return whether the player has cards remaining
     */
    private boolean checkIfRemainCardsInCardsGame(Player player_3) {
        if (player_3.game_deck.isEmpty() == true) {
            if (player_3.win_deck.isEmpty() == true)
                return false;
            else {
                player_3.win_deck.shuffle();
                player_3.game_deck = player_3.win_deck;
                player_3.win_deck = new Deck(false);
            }
            return true;
        }
        return true;
    }

    /**
     * prints the player and the card he drew
     */
    public void printDrewSystem() {
        if (player1.getName().compareTo(player2.getName()) < 0) {
            System.out.println(player1.getName() + " drew " + this.player1_deck.getLastCard().toString());
            System.out.println(player2.getName() + " drew " + this.Player2_deck.getLastCard().toString());
        } else {
            System.out.println(player2.getName() + " drew " + this.Player2_deck.getLastCard().toString());
            System.out.println(player1.getName() + " drew " + this.player1_deck.getLastCard().toString());
        }
    }

    /**
     * sets a war according to the game's rules
     */
    public void war() {
        System.out.println("Starting a war...");
        for (int i = 0; i < 3; i++) {

            if (i <= 1) {
                if (player1.getName().compareTo(player2.getName()) < 0) {
                    System.out.println(player1.getName() + " drew a war card");
                    System.out.println(player2.getName() + " drew a war card");
                } else {
                    System.out.println(player2.getName() + " drew a war card");
                    System.out.println(player1.getName() + " drew a war card");
                }
            }
            if (checkIfRemainCardsInCardsGame(player1) == false)
                return;
            this.player1_deck.addCard(this.player1.drawCard());
            if (checkIfRemainCardsInCardsGame(player2) == false)
                return;
            this.Player2_deck.addCard(this.player2.drawCard());
            if (i == 2)
                printDrewSystem();
        }
        int win = this.player1_deck.getLastCard().compare(this.Player2_deck.getLastCard());
        if (win == 0) {
            war();
        } else {
            if (win > 0) {
                System.out.println(player1.getName() + " won the war");
                if (player1.getName().compareTo(player2.getName()) < 0) {
                    int j = 0;
                    while (this.player1_deck.isEmpty() == false || this.Player2_deck.isEmpty() == false) {
                        if (j % 2 == 0)
                            this.player1.addCardToWinDeck(this.Player2_deck.removeTopCard());
                        else
                            this.player1.addCardToWinDeck(this.player1_deck.removeTopCard());
                        j++;
                    }
                } else {
                    int j = 0;
                    while (this.player1_deck.isEmpty() == false || this.Player2_deck.isEmpty() == false) {
                        if (j % 2 == 0)
                            this.player1.addCardToWinDeck(this.player1_deck.removeTopCard());
                        else
                            this.player1.addCardToWinDeck(this.Player2_deck.removeTopCard());
                        j++;
                    }
                }

            } else {
                System.out.println(player2.getName() + " won the war");
                if (player1.getName().compareTo(player2.getName()) < 0) {
                    int j = 0;
                    while (this.player1_deck.isEmpty() == false || this.Player2_deck.isEmpty() == false) {
                        if (j % 2 == 0)
                            this.player2.addCardToWinDeck(this.Player2_deck.removeTopCard());
                        else
                            this.player2.addCardToWinDeck(this.player1_deck.removeTopCard());
                        j++;
                    }
                } else {
                    int j = 0;
                    while (this.player1_deck.isEmpty() == false || this.Player2_deck.isEmpty() == false) {
                        if (j % 2 == 0)
                            this.player2.addCardToWinDeck(this.player1_deck.removeTopCard());
                        else
                            this.player2.addCardToWinDeck(this.Player2_deck.removeTopCard());
                        j++;
                    }
                }
            }
        }

    }

    /**
     * runs the game
     *
     * @return the name of the winner
     */
    public String start() {
        System.out.println("Initializing the game...");
        initializeGame();
        int num_round = 1;
        while (true) {
            if (player1.getName().compareTo(player2.getName()) > 0) {
                if (checkIfRemainCardsInCardsGame(player2) == false)
                    break;
                Player2_deck.addCard(player2.drawCard());
                if (checkIfRemainCardsInCardsGame(player1) == false)
                    break;
                player1_deck.addCard(player1.drawCard());
            } else {
                if (checkIfRemainCardsInCardsGame(player1) == false)
                    break;
                player1_deck.addCard(player1.drawCard());
                if (checkIfRemainCardsInCardsGame(player2) == false)
                    break;
                Player2_deck.addCard(player2.drawCard());
            }
            System.out.println("------------------------- Round number " + num_round + " -------------------------");
            printDrewSystem();
            int win = this.player1_deck.getLastCard().compare(this.Player2_deck.getLastCard());
            if (win > 0) {
                System.out.println(player1.getName() + " won");
                if (player1.getName().compareTo(player2.getName()) > 0) {
                    player1.win_deck.addCard(this.player1_deck.removeTopCard());
                    player1.win_deck.addCard(this.Player2_deck.removeTopCard());
                } else {
                    player1.win_deck.addCard(this.Player2_deck.removeTopCard());
                    player1.win_deck.addCard(this.player1_deck.removeTopCard());
                }
            } else {
                if (win < 0) {
                    System.out.println(player2.getName() + " won");
                    if (player1.getName().compareTo(player2.getName()) > 0) {
                        player2.win_deck.addCard(this.player1_deck.removeTopCard());
                        player2.win_deck.addCard(this.Player2_deck.removeTopCard());
                    } else {
                        player2.win_deck.addCard(this.Player2_deck.removeTopCard());
                        player2.win_deck.addCard(this.player1_deck.removeTopCard());
                    }
                } else
                    war();
            }
            num_round++;
        }
        if (player1.outOfCards() == true)
            return player2.getName();
        else
            return player1.getName();
    }
}

