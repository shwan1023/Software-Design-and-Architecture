package adapter;

public class TableStorageAdapter implements StorageService {
    private AzureTableStorage azureTableStorage;

    public TableStorageAdapter(AzureTableStorage azureTableStorage) {
        this.azureTableStorage = azureTableStorage;
    }

    @Override
    public void storeData(String data) {
        azureTableStorage.uploadTableData(data);
    }

    @Override
    public String retrieveData(String key) {
        return azureTableStorage.downloadTableData(key);
    }
}
