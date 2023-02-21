public class BigTwoDeckTester {
    public static void main(String[] args){
        Deck deck = new BigTwoDeck();
        System.out.println("-----");
        System.out.println(
            "Cards in a Deck (E:52): " + deck.size()
        );
        System.out.println("-----");
        System.out.println(
            "Cards using now (E:BigTwoCard): " + deck.getCard(0).getClass()
        );
        System.out.println("-----");
        deck.print();
    }
}
