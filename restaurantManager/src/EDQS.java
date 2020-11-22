import java.util.HashMap;
import java.util.Map;

public class EDQS {
    private final Map<Integer,String> employeeList = new HashMap<>();

    public EDQS(){
        int[] employeeIDs = new int[]{1,2,3};
        String[] passwords = {"password01","password02","password03"};
        for(int i =0;i<3;i++){
            employeeList.put(employeeIDs[i],passwords[i]);
        }
    }

    public boolean validateUser(int userID, String password){
        return employeeList.containsKey(userID) && employeeList.get(userID).equals(password);
    }
}
