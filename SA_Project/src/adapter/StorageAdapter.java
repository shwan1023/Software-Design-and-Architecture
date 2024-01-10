package adapter;

public class StorageAdapter implements StorageService {
    private AzureBlobStorage azureBlobStorage;

    public StorageAdapter(AzureBlobStorage azureBlobStorage) {
        this.azureBlobStorage = azureBlobStorage;
    }

    @Override
    public void storeData(String data) {
        azureBlobStorage.uploadBlob(data);
    }

    @Override
    public String retrieveData(String key) {
        return azureBlobStorage.downloadBlob(key);
    }
}
