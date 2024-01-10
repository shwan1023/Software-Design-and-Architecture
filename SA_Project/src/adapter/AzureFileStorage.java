package adapter;

import java.util.HashMap;
import java.util.Map;

public class AzureFileStorage {
    private Map<String, String> fileStorage = new HashMap<>();

    public void uploadFileData(String data) {
        String key = "file_" + System.currentTimeMillis();
        fileStorage.put(key, data);
        System.out.println("Data uploaded to Azure File Storage with key: " + key);
    }

    public String downloadFileData(String key) {
        return fileStorage.getOrDefault(key, "No data found for key: " + key);
    }
}
