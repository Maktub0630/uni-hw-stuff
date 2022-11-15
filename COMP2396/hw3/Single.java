/**
 * a subclass of the Hand class and are used to model a hand of single
 */
public class Single extends Hand {
    /**
     * constructor to build an object of Single
     * @param player the specified player
     * @param cards the specified card
     */
    public Single(CardGamePlayer player, CardList cards){
        super(player, cards);
    }
    /**
     * check if this is a valid hand
     */
    public boolean isValid(){
        if (size()==1){ //single only contains one card of any suit or rank
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * return the name of the type
     */
    public String getType(){
        return "Single";
    }
}
