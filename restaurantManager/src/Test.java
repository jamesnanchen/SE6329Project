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

        String tableName;
        try {
            tableName = rsc.selectTable(1);
        } catch (Exception e) {
            System.out.println("ERROR: Unable to get table id.");
        }

        int cateID = 1001;
        int[] cate = rsc.selectCategory(cateID);
        System.out.println("Here are the items in category: "+cateID+"\n"+Arrays.toString(cate));

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
