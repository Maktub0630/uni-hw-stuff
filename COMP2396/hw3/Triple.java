/**
 * a subclass of the Hand class and are used to model a hand of pair
 */
public class Triple extends Hand {
    /**
     * constructor to build an object of Triple
     * @param player the specified player
     * @param cards the specified card
     */
    public Triple(CardGamePlayer player, CardList cards){
        super(player, cards);
    }
    /**
     * check if this is a valid hand
     */
    public boolean isValid(){
        if (size()==3 && (getCard(0).getRank()==getCard(1).getRank() && getCard(0).getRank()==getCard(2).getRank())){
            return true;
        }
        return false;
    }
    /**
     * return the name of the type
     */
    public String getType(){
        return "Triangle";
    }
}
