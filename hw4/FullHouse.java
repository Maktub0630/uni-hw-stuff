/**
 * a subclass of the Hand class and are used to model a hand of FullHouse
 */
public class FullHouse extends Hand {
    /**
     * constructor to build an object of FullHouse
     * @param player the specified player
     * @param cards the specified card
     */
    public FullHouse(CardGamePlayer player, CardList cards){
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
            int three=0,two=0;
            Card card1=getCard(0); //3 and 2
            Card card2=getCard(3);
            if (getCard(1).getRank()!=getCard(2).getRank()){ //2 and 3
                card1=getCard(3);
                card2=getCard(0);
            }
            
            
            for (int i = 0; i < 5; i++){
                if (card2.getRank()==getCard(i).getRank()){
                    two++;
                }
                else if (card1.getRank()==getCard(i).getRank()){
                    three++;
                }  
            }
            if (three==3 && two==2){
                return true;
            }
            else {
                return false;
            }
        }
    }
    /**
     * return the name of the type
     */
    public String getType(){
        return "FullHouse";
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
        //fullhouse can beat straight and flush
        if (hand.getType()=="Straight" || hand.getType()=="Flush"){
            return true;
        }
        else if (hand.getType()!="FullHouse"){
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
