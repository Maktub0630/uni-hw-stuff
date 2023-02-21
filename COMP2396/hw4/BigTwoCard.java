/**
 * The BigTwoCard class is a subclass of the Card class and is used to model a card used in a Big Two card game.
 */
public class BigTwoCard extends Card {
    /**
     * constructor for building a card with the specified suit and rank. 
     * suit is an integer between 0 and 3, and rank is an integer between 0 and 12.
     * @param suit
     * @param rank
     */
    public BigTwoCard(int suit, int rank){
        super(suit, rank); //constructor using super
    }

    /**
     * a method for comparing the order of this card with the specified card.
     * @param card 
     * @return a negative integer, zero, or a positive integer when this card is less than, equal to, or greater than the specified card.
     */
    public int compareTo(Card card){
        int THIS=BigTwoRankOrder(this.getRank()); //convert the normal card to big two card digit
        int SPEC=BigTwoRankOrder(card.getRank());
        if (THIS<SPEC) {
            return -1;
        }
        else {
            if (THIS>SPEC){
                        return 1;
            }
            else {
                if (THIS<SPEC) return -1;
                if (this.getSuit()>card.getSuit()) return 1;
                else if (this.getSuit()<card.getSuit()){
                    return -1;
                }
            }   
        }
        return 0;
    }
    /**
     * A method to translate card order into BigTwo version
     * @param rank org rank of the card
     * @return translated rank
     */
    public static int BigTwoRankOrder(int rank){
        if (rank>=2 && rank <=12)
            return (rank-2);
        else if (rank==1)
            return 12;
        else return 11;
    }
}
