package adapter;

import java.util.HashMap;
import java.util.Map;

public class AzureBlobStorage {
    private Map<String, String> storage = new HashMap<>();

    public void uploadBlob(String data) {
        String key = "blob_" + System.currentTimeMillis();
        storage.put(key, data);
        System.out.println("Data uploaded to Azure Blob Storage with key: " + key);
    }

    public String downloadBlob(String key) {
        return storage.getOrDefault(key, "No data found for key: " + key);
    }
}
