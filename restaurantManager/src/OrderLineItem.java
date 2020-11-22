public class OrderLineItem {
    private final int itemID;

    public OrderLineItem(int itemID){
        this.itemID=itemID;
    }

    @Override
    public String toString() {
        return "itemID=" + itemID;
    }
}
