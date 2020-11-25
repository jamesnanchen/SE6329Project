import java.util.ArrayList;

public class RestaurantSystemController {
    private static final CategoryList cateList = new CategoryList();
    private static final EDQS edqs = new EDQS();
    private static final TDQS tdqs = new TDQS();
    private static Order order;
    private static int orderID = 1;
    private final OrderQueue orderQueue = new OrderQueue();

    public static boolean login(int userID, String password){
        boolean loginStatus = false;
        try {
            return edqs.validateUser(userID,password);
        } catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
    public static ArrayList<Integer> getTableList(){
        return tdqs.getTableList();
    }

    public static String selectTable(int tableID) throws Exception {
        return tdqs.getTable(tableID);
    }

    public static void makeNewOrder(){
        order = new Order(orderID++);
    }

    public static ArrayList<Integer> getCategoryList(){
        return cateList.getCategoryList();
    }

    public static int[] selectCategory(int cateID){
        return cateList.getItemList(cateID);
    }

    public static void selectDesiredItem(int itemID){
        order.makeLineItem(itemID);
    }

    public void finishOrder(){
        orderQueue.addOrderIntoOrderQueue(order);
        order.sentToQueue();
        orderQueue.showQueue();
    }
}
