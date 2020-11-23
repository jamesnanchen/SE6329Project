import java.util.Map;

public class TDQS {

    private Map<Integer, String> tables;
    public TDQS() {
        this.tables.put(1,"table01");
        this.tables.put(2,"table02");
        this.tables.put(3,"table03");
        this.tables.put(4,"table04");
    }

    public String getTable(int tableID){
        return this.tables.get(tableID);
    }
}
