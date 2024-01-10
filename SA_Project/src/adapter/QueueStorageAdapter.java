package adapter;

public class QueueStorageAdapter implements StorageService {
    private AzureQueueStorage azureQueueStorage;

    public QueueStorageAdapter(AzureQueueStorage azureQueueStorage) {
        this.azureQueueStorage = azureQueueStorage;
    }

    @Override
    public void storeData(String data) {
        azureQueueStorage.enqueueData(data);
    }

    @Override
    public String retrieveData(String key) {
        return azureQueueStorage.dequeueData(key);
    }
}
