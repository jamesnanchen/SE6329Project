import java.util.Arrays;

public class RestaurantSystemController {
    private final CategoryList cateList = new CategoryList();
    private final EDQS edqs = new EDQS();
    private final TDQS tdqs = new TDQS();
    private Order order;
    private int orderID = 1;
    private final OrderQueue orderQueue = new OrderQueue();

    public boolean login(int userID, String password){
        return edqs.validateUser(userID,password);
    }

    public void getTables(){
        System.out.println("Which table do you want to select?\n"+ Arrays.toString(tdqs.getTable()));
    }
    public void makeNewOrder(){
        this.order = new Order(orderID++);
    }

    public int[] selectCategory(int cateID){
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
