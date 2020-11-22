public class RestaurantSystemController {

    private final CategoryList cateList = new CategoryList();
    private final EDQS edqs = new EDQS();
    private Order order;
    private int orderID = 0;
    private final OrderQueue orderQueue = new OrderQueue();

    public boolean login(int userID, String password){
        return edqs.validateUser(userID,password);
    }

    public void makeNewOrder(){
        order = new Order(orderID++);
    }

    public int[] selectCategory(int cateID){
        return cateList.getItemList(cateID);
    }

    public void selectDesiredItem(int itemID){
        order.makeLineItem(itemID);
    }

    public void finishOrder(Order order){
        orderQueue.addOrderIntoOrderQueue(order);
        order.sentToQueue();
    }
}
