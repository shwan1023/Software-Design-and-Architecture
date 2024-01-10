package adapter;

import java.util.HashMap;
import java.util.Map;

public class AzureQueueStorage {
    private Map<String, String> queueStorage = new HashMap<>();

    public void enqueueData(String data) {
        String key = "queue_" + System.currentTimeMillis();
        queueStorage.put(key, data);
        System.out.println("Data enqueued to Azure Queue Storage with key: " + key);
    }

    public String dequeueData(String key) {
        return queueStorage.getOrDefault(key, "No data found for key: " + key);
    }
}
