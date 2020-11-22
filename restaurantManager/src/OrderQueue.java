import java.util.LinkedList;
import java.util.Queue;

public class OrderQueue {
    Queue<Order> q = new LinkedList<>();

    public void addOrderIntoOrderQueue(Order o){
        q.add(o);
    }

}
