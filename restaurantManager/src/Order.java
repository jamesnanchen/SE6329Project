import java.util.ArrayList;

public class Order {

    private int orderID;
    private ArrayList<OrderLineItem> orderList = new ArrayList<>();
    private boolean isComplete = false;

    public Order(int orderID){
        this.orderID=orderID;
    }

    public void makeLineItem(int itemID){
        orderList.add(new OrderLineItem(itemID));
    }

    public int getOrderID(){
        return this.orderID;
    }

    public void sentToQueue(){
        isComplete = true;
    }

    public boolean inQueue(){
        return isComplete;
    }
}
