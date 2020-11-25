import java.util.Arrays;

public class RestaurantSystemController {
    private static final CategoryList cateList = new CategoryList();
    private static final EDQS edqs = new EDQS();
    private static final TDQS tdqs = new TDQS();
    private Order order;
    private int orderID = 1;
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

    public static String selectTable(int tableID) throws Exception {
        return tdqs.getTable(tableID);
    }

    public void makeNewOrder(){
        this.order = new Order(orderID++);
    }

    public static int[] selectCategory(int cateID){
        return cateList.getItemList(cateID);
    }

    public void selectDesiredItem(int itemID){
        this.order.makeLineItem(itemID);
    }

    public void finishOrder(){
        orderQueue.addOrderIntoOrderQueue(this.order);
        this.order.sentToQueue();
        orderQueue.showQueue();
    }
}
