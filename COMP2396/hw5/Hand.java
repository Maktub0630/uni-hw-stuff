/**
 * The Hand class is a subclass of the CardList class and is used to model a hand of cards.
 */
public abstract class Hand extends CardList {
    /**
     * a constructor for building a hand with the specified player and list of cards.
     * @param player the specified player
     * @param cards the specified lists of cards
     */
    public Hand(CardGamePlayer player, CardList cards){
        this.player=player; //set player
        int num = cards.size();
        for (int i = 0; i < num;i++ ){
            Card cardnow =  cards.getCard(i);
            this.addCard(cardnow); //set cards
        }
        sort(); //sort the cards
    }

    private CardGamePlayer player;

    /**
     * a method for retrieving the player of this hand.
     * @return CardGamePlayer player: the player who plays this hand.
     */
    public CardGamePlayer getPlayer(){
        return this.player;
    }

    /**
     *  a method for retrieving the top card of this hand.
     * @return top card of player's hand
     */
    public Card getTopCard(){
        Card topCard = getCard(0);
        for (int i = 1;i<size();i++){
            if(topCard.compareTo(getCard(i))==1){
                topCard=getCard(i);
            }
        }
        return topCard;
    }
    /**
     * a method for checking if this hand beats a specified hand.
     * @param hand the specified Hand
     * @return true if can beat, false for otherwise
     */
    public boolean beats(Hand hand){
        if (hand.size()!=this.size()){
            return false;
        }
            if (this.getTopCard().compareTo(hand.getTopCard()) == 1) {
                return true;
            }
            else {
                return false;
            }
        
    }
    /**
     * a method for checking if this is a valid hand
     * @return truth value
     */
    public abstract boolean isValid();
    /**
     * a method for returning a string specifying the type of this hand
     * @return type of this hand
     */
    public abstract String getType();
}
