public class WarGame {
    Player player1;
    Deck player1_deck;
    Player player2;
    Deck Player2_deck;

    public WarGame(String name1, String name2) {
        player1 = new Player(name1);
        player2 = new Player(name2);
        player1_deck = new Deck(false);
        Player2_deck = new Deck(false);
    }

    public void initializeGame() {
        Deck cards = new Deck(true);
        cards.shuffle();
        if (player1.getName().compareTo(player2.getName()) > 0) {
            for (int i = 0; i < 52; i++) {
                if (i % 2 == 1)
                    this.player1_deck.addCard(cards.removeTopCard());
                else
                    this.Player2_deck.addCard(cards.removeTopCard());
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

    private boolean checkIfRemainCardsInCardsGame(Player player1) {
        if (player1.game_deck.isEmpty() == true) {
            if (player1.win_deck.isEmpty() == true)
                return false;
            else {
                player1.win_deck.shuffle();
                while (player1.win_deck.isEmpty() == false)
                    player1.game_deck.addCard(player1.win_deck.removeTopCard());
                //player1.game_deck = player1.win_deck;
                //player1.win_deck = new Deck(false);
                return true;
            }
        }
        return true;
    }

    public void printDrewSystem() {
        if (player1.getName().compareTo(player2.getName()) < 0) {
            System.out.println(player1.getName() + " drew " + this.player1_deck.getLastCard().toString());
            System.out.println(player2.getName() + " drew " + this.Player2_deck.getLastCard().toString());
        } else {
            System.out.println(player2.getName() + " drew " + this.Player2_deck.getLastCard().toString());
            System.out.println(player1.getName() + " drew " + this.player1_deck.getLastCard().toString());
        }
    }

    public void war() {
        System.out.println("Starting a war...");
        for (int i = 0; i < 3 && checkIfRemainCardsInCardsGame(player1) == true &&
                checkIfRemainCardsInCardsGame(player2) == true; i++) {
            if (i <= 1) {
                if (player1.getName().compareTo(player2.getName()) < 0) {
                    System.out.println(player1.getName() + " drew a war card");
                    System.out.println(player2.getName() + " drew a war card");
                } else {
                    System.out.println(player2.getName() + " drew a war card");
                    System.out.println(player1.getName() + " drew a war card");
                }
            } else {
                printDrewSystem();
            }
            this.player1_deck.addCard(this.player1.drawCard());
            this.Player2_deck.addCard(this.player2.drawCard());
        }
        int win = this.player1_deck.getLastCard().compare(this.Player2_deck.getLastCard());
        if (win == 0)
            war();
        else {
            if (win > 0) {
                System.out.println(player1.getName() + " won the war");

                if (player1.getName().compareTo(player2.getName()) > 0) {
                    while (player1_deck.isEmpty() == true)
                        player1.win_deck.addCard(this.player1_deck.removeTopCard());
                    while (this.Player2_deck.isEmpty() == true)
                        player1.win_deck.addCard(this.Player2_deck.removeTopCard());
                } else {
                    while (this.Player2_deck.isEmpty() == true)
                        player1.win_deck.addCard(this.Player2_deck.removeTopCard());
                    while (player1_deck.isEmpty() == true)
                        player1.win_deck.addCard(this.player1_deck.removeTopCard());
                }
            } else {
                System.out.println(player2.getName() + " won the war");
                while (this.player1_deck.isEmpty() == true) {
                    if (player2.getName().compareTo(player2.getName()) > 0) {
                        player2.win_deck.addCard(this.player1_deck.removeTopCard());
                        player2.win_deck.addCard(this.Player2_deck.removeTopCard());
                    } else {
                        player2.win_deck.addCard(this.Player2_deck.removeTopCard());
                        player2.win_deck.addCard(this.player1_deck.removeTopCard());
                    }
                }
            }
        }

    }

    public String start() {
        System.out.println("Initializing the game...");
        initializeGame();
        int num_round = 1;
        while (checkIfRemainCardsInCardsGame(player1) == true &&
                checkIfRemainCardsInCardsGame(player2) == true) {
            System.out.println("-------------------------Round" + num_round + "-------------------------");
            player1_deck.addCard(player1.drawCard());
            Player2_deck.addCard(player2.drawCard());
            printDrewSystem();
            int win = this.player1_deck.getLastCard().compare(this.Player2_deck.getLastCard());
            if (win > 0) {
                System.out.println(player1.getName() + " won");
                if (player1.getName().compareTo(player2.getName()) < 0) {
                    player1.win_deck.addCard(this.player1_deck.removeTopCard());
                    player1.win_deck.addCard(this.Player2_deck.removeTopCard());

                } else {
                    player1.win_deck.addCard(this.Player2_deck.removeTopCard());
                    player1.win_deck.addCard(this.player1_deck.removeTopCard());
                }
            } else {
                if (win < 0) {
                    System.out.println(player2.getName() + " won");
                    if (player1.getName().compareTo(player2.getName()) < 0) {
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
            return player1.getName();
        else
            return player2.getName();
    }
}

