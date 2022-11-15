/**
 * a subclass of the Hand class and are used to model a hand of StraightFlush
 */
public class StraightFlush extends Hand {
    /**
     * constructor to build an object of StraightFlush
     * @param player the specified player
     * @param cards the specified card
     */
    public StraightFlush(CardGamePlayer player, CardList cards){
        super(player, cards);
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
            for (int i=0;i<5-1;i++){
                if (getCard(i).getRank() + 1 != getCard(i+1).getRank()){
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
        return "StraightFlush";
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
        if(hand.getType()!="StraightFlush"){
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

