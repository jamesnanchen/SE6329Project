import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class TDQS {

    private HashMap<Integer, String> tables;
    public TDQS() {
        tables = new HashMap<>();
        tables.put(1,"table01");
        tables.put(2,"table02");
        tables.put(3,"table03");
        tables.put(4,"table04");
    }

    public String getTable(int tableID) throws Exception {
        URL url = new URL("https://ceysk6d6f4.execute-api.us-east-1.amazonaws.com/v1/tdqs/gettable/");
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();

        httpConnection.setRequestMethod("POST");
        httpConnection.setRequestProperty("Accept", "application/json");
        httpConnection.setRequestProperty("Content-Type", "application/json");

        httpConnection.setDoOutput(true);
        OutputStream outStream = httpConnection.getOutputStream();
        OutputStreamWriter outStreamWriter = new OutputStreamWriter(outStream, "UTF-8");
        outStreamWriter.write(String.format("{\"tableId\":%d}",tableID));
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
        return url.getPath();
    }
}
