import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class EDQS {
//    private final Map<Integer,String> employeeList = new HashMap<>();
//
//    public EDQS(){
//        int[] employeeIDs = new int[]{1,2,3};
//        String[] passwords = {"password01","password02","password03"};
//        for(int i =0;i<3;i++){
//            employeeList.put(employeeIDs[i],passwords[i]);
//        }
//    }

    public boolean validateUser(int userID, String password) throws Exception{
        URL url = new URL("https://ceysk6d6f4.execute-api.us-east-1.amazonaws.com/v1/edqs/validateuser");
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();

        httpConnection.setRequestMethod("POST");
        httpConnection.setRequestProperty("Accept", "application/json");
        httpConnection.setRequestProperty("Content-Type", "application/json");

        httpConnection.setDoOutput(true);
        OutputStream outStream = httpConnection.getOutputStream();
        OutputStreamWriter outStreamWriter = new OutputStreamWriter(outStream, "UTF-8");
        outStreamWriter.write(String.format("{\"username\": %d, \"password\": \"%s\"}", userID, password));
        outStreamWriter.flush();
        outStreamWriter.close();
        outStream.close();
        BufferedReader br = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line+"\n");
        }
        br.close();

        System.out.println(sb.toString());

        System.out.println(httpConnection.getResponseCode());
        System.out.println(httpConnection.getResponseMessage());
        // InputStream xml = httpConnection.getInputStream();

        if (httpConnection.getResponseCode() == 200) {
            return sb.substring(25,29).equals("true");
        }
        return false;
    }
}
