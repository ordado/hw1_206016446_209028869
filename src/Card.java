public class Card {
    final int number;
    final shape shape;
    static final int EVEN = 0;
    static final int BIGER = 1;
    static final int LOWER = -1;
    static final int KING = 13;
    static final int QUEEN = 12;
    static final int JACK = 11;
    static final int ACE = 1;


    public Card(int number, shape shape) {
        this.number = number;
        this.shape = shape;
    }

    public int getNumber() {
        return this.number;
    }

    public shape getShape() {
        return this.shape;
    }

    public int compare(Card other) {
        if (this.number == other.getNumber())
            return EVEN;
        else {
            if (this.number < other.getNumber())
                return LOWER;
            else
                return BIGER;
        }
    }

    @Override
    public String toString() {
        if (this.number == KING)
            return "King of " + this.shape.toString();
        if (this.number == QUEEN)
            return "Queen of " + this.shape.toString();
        if (this.number == JACK)
            return "Jack of " + this.shape.toString();
        if (this.number == ACE)
            return "Ace of " + this.shape.toString();

        return this.number + " of " + this.shape.toString();
    }
}
