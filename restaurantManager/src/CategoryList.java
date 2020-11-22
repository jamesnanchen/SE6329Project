import java.util.Map;

public class CategoryList {
    private Map<Integer,int[]> itemLists;
    int[] itemList1 = new int[]{101,102,103,104,105,106,107,108,109,110};
    int[] itemList2 = new int[]{201,202,203,204,205,206,207,208,209,210};
    public CategoryList(){
        int cateID1 = 1001;
        int cateID2 = 1002;
        itemLists.put(cateID1,itemList1);
        itemLists.put(cateID2,itemList2);
    }

    public int[] getItemList(int cateID){
        return (int[])itemLists.get(cateID);
    }
}
