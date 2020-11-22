import java.util.Arrays;

public class Test {
    public static void main(String[] args){
        RestaurantSystemController rsc = new RestaurantSystemController();
        boolean logIn = rsc.login(2,"password02");
        if(logIn){
            System.out.println("you are logged in");
        }
        else{
            System.out.println("logged in failed");
        }
        int[] cate = rsc.selectCategory(1001);
        System.out.println(Arrays.toString(cate));

        rsc.makeNewOrder();

        rsc.selectDesiredItem(101);
        rsc.selectDesiredItem(102);
        rsc.selectDesiredItem(103);
        rsc.finishOrder();

        rsc.makeNewOrder();
        rsc.selectDesiredItem(104);
        rsc.finishOrder();
    }
}
