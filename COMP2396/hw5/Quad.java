/**
 * a subclass of the Hand class and are used to model a hand of Quad
 */
public class Quad extends Hand {
    /**
     * constructor to build an object of quad
     * @param player the specified player
     * @param cards the specified card
     */
    public Quad(CardGamePlayer player, CardList cards){
        super(player, cards);
    }
    /**
     * check if this is a valid hand
     */
    public boolean isValid(){
        if (size()!=5){
            return false;
        }
        
            int four=0;
            int rank=getCard(0).getRank();
            for (int i=1;i<size();i++){
                if (rank==getCard(i).getRank()){
                    four++;
                }
            }
            if (four==4){
                return true;
            }
            else {
                return false;
            }
        
    }
    /**
     * return the name of the type
     */
    public String getType(){
        return "Quad";
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
        //specifying for quad hand: only Strightflush can beat quad
        if (hand.getType()=="straight" || hand.getType()=="Flush" || hand.getType()=="FullHouse")
            return true;
        else if(hand.getType()=="StraightFlush")
            return false;
            if (this.getTopCard().compareTo(hand.getTopCard()) == 1) {
                return true;
            }
            else {
                return false;
            }
        
    }
}
