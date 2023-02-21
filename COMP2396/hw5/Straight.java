/**
 * a subclass of the Hand class and are used to model a hand of Straight
 */
public class Straight extends Hand {
    /**
     * constructor to build an object of Straight
     * @param player the specified player
     * @param cards the specified card
     */
    public Straight(CardGamePlayer player, CardList cards){
        super(player, cards);
    }
    /**
     * check if this is a valid hand
     */
    public boolean isValid(){
        if (size()!=5){
             return false;
        }
        for (int i=0;i<4;i++){
                if (BigTwoCard.BigTwoRankOrder(getCard(i).getRank())  + 1 != BigTwoCard.BigTwoRankOrder(getCard(i+1).getRank()) ){
                    return false;
                } 
            }
        return true;
    }
    /**
     * return the name of the type
     */
    public String getType(){
        return "Straight";
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
        if (hand.getType() != "Straight"){
            return false;
        }
            if (this.getTopCard().compareTo(hand.getTopCard()) == 1) {
                return true;
            }
            else {
                return false;
            }
        
    }
}
