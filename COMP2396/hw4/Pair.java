/**
 * a subclass of the Hand class and are used to model a hand of pair
 */
public class Pair extends Hand{
    /**
     * constructor to build an object of pair
     * @param player the specified player
     * @param cards the specified card
     */
    public Pair(CardGamePlayer player, CardList cards){
        super(player, cards);
    }
    /**
     * check if this is a valid hand
     */
    public boolean isValid(){
        if (size()==2 && getCard(0).getRank()==getCard(1).getRank()){
            return true;
        }
        return false;
    }
    /**
     * return the name of the type
     */
    public String getType(){
        return "Pair";
    }
}
