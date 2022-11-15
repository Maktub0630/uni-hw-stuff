/**
 * a subclass of the Hand class and are used to model a hand of Flush
 */
public class Flush extends Hand {
    /**
     * constructor to build an object of StraightFlush
     * @param player the specified player
     * @param cards the specified card
     */
    public Flush(CardGamePlayer player, CardList cards){
        super(player, cards); //constructor using super
    }
    /**
     * check if this is a valid hand
     */
    public boolean isValid(){
        if (size()!=5){
            return false;
        }
        else{
            int suit=getCard(0).getSuit();
            for (int i=1;i<5;i++){
                if (suit!=getCard(i).getSuit()){
                    return false;
                }
            }
            
        }
        return true;
    }
    /**
     * return the name of the type
     */
    public String getType(){
        return "Flush";
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
    //specification: Straight can be beaten by flush
    if (hand.getType() == "Straight"){
        return true;
    }
    else if (hand.getType() != "Flush"){
        return false;
    }
    //compare the suit first then the rank
    if (this.getTopCard().getSuit() > hand.getTopCard().getSuit())
    return true;
    else if (this.getTopCard().getSuit() < hand.getTopCard().getSuit()) 
    return false;
    else if (BigTwoCard.BigTwoRankOrder(this.getTopCard().getRank()) > BigTwoCard.BigTwoRankOrder(hand.getTopCard().getRank()))
    return true;
    else
    return false;
}
}

