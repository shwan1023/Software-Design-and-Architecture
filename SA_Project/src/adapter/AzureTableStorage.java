package adapter;

import java.util.HashMap;
import java.util.Map;

public class AzureTableStorage {
    private Map<String, String> tableStorage = new HashMap<>();

    public void uploadTableData(String data) {
        String key = "table_" + System.currentTimeMillis();
        tableStorage.put(key, data);
        System.out.println("Data uploaded to Azure Table Storage with key: " + key);
    }

    public String downloadTableData(String key) {
        return tableStorage.getOrDefault(key, "No data found for key: " + key);
    }
}
