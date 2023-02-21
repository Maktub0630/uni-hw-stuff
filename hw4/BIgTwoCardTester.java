public class BIgTwoCardTester {
    public static void main(String[] args){
        System.out.println();

        BigTwoCard _3d = new BigTwoCard(0,2) ;
        BigTwoCard _Kh = new BigTwoCard(2,12) ;
        BigTwoCard _Ac = new BigTwoCard(1,0) ;
        BigTwoCard _2s = new BigTwoCard(3,1) ;
        System.out.println("-----");
        System.out.println(
            "Is 3d stronger than 2s (E:-1)" +
            _3d.compareTo(_2s)
        );
        System.out.println("-----");
        System.out.println(
            "Is 3d stronger than 2s (E:0)" + 
            _3d.compareTo(_3d)
        );
        System.out.println("-----");
        System.out.println(
            "Is 3d stronger than Kh (E:1)" + 
            _3d.compareTo(_Kh)
        );
        System.out.println("-----");
        System.out.println(
            "Is Kc stronger than Ac (E:-1)" + 
            _Kh.compareTo(_Ac)
        );
    }
}
