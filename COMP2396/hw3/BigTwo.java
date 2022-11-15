import java.util.ArrayList;
/**
 * The BigTwo class implements the CardGame interface and is used to model a Big Two card game. 
 * It has private instance variables for storing the number of players, a deck of cards, a list of players, a list of hands played on the table, an index of the current player, and a user interface. 
 */
public class BigTwo implements CardGame{
    /**
     *  a  constructor  for  creating  a  Big  Two  card  game.
     */
    public BigTwo(){
        this.numOfPlayers=4;
        for (int i = 0;i<4;i++){
            CardGamePlayer player = new CardGamePlayer();
            playerList.add(player); 
        }
        ui = new BigTwoUI(this);
    }
//private variables
private int numOfPlayers;
private Deck deck;
private ArrayList<CardGamePlayer> playerList = new ArrayList<CardGamePlayer>();
private ArrayList<Hand> handsOnTable =  new ArrayList<Hand>();
private int currentPlayerIdx;
private BigTwoUI ui;
/**
 * a method for getting the number of players.
 * @return numOfPlayers
 */
public int getNumOfPlayers(){
    return this.numOfPlayers;
    }

/**
 * a method for retrieving the deck of cards being used
 * @return deck
 */
public Deck getDeck(){
    return this.deck;
}

/**
 * a method for retrieving the list of players
 * @return playerList
 */
public ArrayList<CardGamePlayer> getPlayerList(){
    return this.playerList;
}

/**
 * a method for retrieving the list of hands played on the table
 * @return handsOnTable
 */
public ArrayList<Hand> getHandsOnTable(){
    return this.handsOnTable;
}

/**
 * a method for retrieving the index of the current player
 * @return currentPlayerIdx
 */
public int getCurrentPlayerIdx(){
    return this.currentPlayerIdx;
}

/**
 * a method for starting/restarting the game with a given shuffled deck of cards.
 * @param deck a deck of cards.
 */
public void start(Deck deck){
    handsOnTable.clear();//(i) remove all the cards from the players as well as from the table
    for(int i = 0; i<numOfPlayers; i++){
        playerList.get(i).removeAllCards();
    }
     //(ii) distribute the cards to the players; 
    int playerCardIdx = 0;
    for (int cardIdx = 0; cardIdx<deck.size();cardIdx++){
        playerList.get(playerCardIdx).addCard(deck.getCard(cardIdx));
        playerCardIdx = (playerCardIdx+1)%numOfPlayers;
    }
    Card threeOfDiamond = new Card(0,2);//identify the player who holds the Three of Diamonds
    for (int i = 0; i < this.playerList.size(); i++){ 
        if (playerList.get(i).getCardsInHand().contains(threeOfDiamond)){
            currentPlayerIdx=i;
            break;
        }
    }
    while (endOfGame()==false){
        ui.setActivePlayer(currentPlayerIdx);
        playerList.get(currentPlayerIdx).sortCardsInHand();
        int whoIsPlaying = currentPlayerIdx;
        ui.repaint();//(v) call the repaint
        while (currentPlayerIdx != (whoIsPlaying+1)%numOfPlayers){
            ui.promptActivePlayer();// call  the  promptActivePlayer()
        }
    }
    //ending game
    ui.setActivePlayer(4);
    ui.repaint();
    System.out.println();
    System.out.println("Game ends");
    for (int i = 0; i<numOfPlayers;i++){
        if (playerList.get(i).getNumOfCards()!=0){
            System.out.println("Player " +i+" has "+playerList.get(i).getNumOfCards()+" cards in hand.");
        }
        else{
            System.out.println("Player " +i+ " wins the game.");
        }
    }
}
/**
 * method for checking a move made by a player.
 * @param playerIdx the specifed player who is making a move
 * @param cardIdx the int array that stores the idx of cards one is making the move with
 */
public void makeMove(int playerIdx, int[] cardIdx){
    checkMove(playerIdx, cardIdx);//checkmove
} 

/**
 * method for checking a move made by a player.
 * @param playerIdx the specifed player who is making a move
 * @param cardIdx the int array that stores the idx of cards one is making the move with
 */
public void checkMove(int playerIdx, int[] cardIdx){
    if (cardIdx != null){//check if the input is valid or not
        CardList tempCardList = new CardList();
        for (int i = 0; i<cardIdx.length;i++){
            if(cardIdx[i] < 0 || cardIdx[i] >= playerList.get(playerIdx).getNumOfCards()) {
                System.out.println("not a legal move!!!");
                return;
            }
            Card tempCard = playerList.get(playerIdx).getCardsInHand().getCard(cardIdx[i]);
            tempCardList.addCard(tempCard);//if the input is valid, put card in a temp cardlist
        }
        Hand tempHand = composeHand(playerList.get(playerIdx), tempCardList);//use the card list to compose a hand for the player
        if (tempHand != null){//check if it is valid hand or not: only when it is in the three situation below can one play their hands, not a legal hand otherwise
            if (handsOnTable.isEmpty() || tempHand.beats(handsOnTable.get(handsOnTable.size()-1)) || handsOnTable.get(handsOnTable.size() - 1).getPlayer() == playerList.get(playerIdx) ){
                handsOnTable.add(tempHand);//since all constraints are valid, the hand made by player is also valid and can be added on the table
                playerList.get(playerIdx).removeCards(tempCardList);//remove the cards of that hand in player's cardlist
                System.out.print("{"+tempHand.getType()+"} ");//print the hand
                tempHand.print();
                System.out.println();
                currentPlayerIdx = (currentPlayerIdx + 1) % numOfPlayers;//tranverse the player idx
            }
            else{//invalid hand
                System.out.println("not a legal move!!!");
                return;
            }
        }
        else{//null hand for non empty cardlist
            System.out.println("not a legal move!!!");
            return;
        }
    }
    else {//empty cardlist can be either a pass or invalid hand
        if (handsOnTable.isEmpty()  || handsOnTable.get(handsOnTable.size() - 1).getPlayer() == playerList.get(playerIdx)){
            System.out.println("not a legal move!!!");
            return;
        }
        else {
            System.out.println("{pass}");
            System.out.println();
            currentPlayerIdx = (currentPlayerIdx+1)%numOfPlayers;
            return;
        }
    }
    
}
/**
 * a method for checking if the game ends
 * @return true if the game ends, false if not end yet
 */
public boolean endOfGame(){
    for (int i = 0;i<playerList.size();i++){
        if (playerList.get(i).getNumOfCards()==0){
            return true;
        }
    }
    return false;
}
/**
 * a method for starting a Big Two card game.
 * @param args not applicable
 */
public static void main(String[] args){
    CardGame game=new BigTwo(); // (i) create  a  Big  Two  card  game
    Deck deck = new BigTwoDeck(); 
    deck.shuffle();// (ii)  create  and  shuffle  a  deck  of  cards
    game.start(deck); // (iii)  start  the game with the deck of cards
}
/**
 * a method for returning a valid hand from the specified list of cards of the player.
 * @param player specified player
 * @param cards the cards of the player
 * @return valid hand
 */
public static Hand composeHand(CardGamePlayer player, CardList cards){
    //here the type of all hands are checked one by one
    Hand single =new Single(player, cards); 
    if (single.isValid()) return single;
    Hand pair =new Pair(player, cards);
    if (pair.isValid()) return pair;
    Hand triple =new Triple(player, cards);
    if (triple.isValid()) return triple;
    Hand straightFlush = new StraightFlush(player, cards);
    if(straightFlush.isValid()) return straightFlush;
    Hand straight = new Straight(player, cards);
    if(straight.isValid()) return straight;
    Hand flush = new Flush(player, cards);
    if(flush.isValid()) return flush;
    Hand fullHouse = new FullHouse(player, cards);
    if(fullHouse.isValid()) return fullHouse;
    Hand quad = new Quad(player, cards);
    if(quad.isValid()) return quad;
    return null; // if none of them is fulfilled return null
}
}