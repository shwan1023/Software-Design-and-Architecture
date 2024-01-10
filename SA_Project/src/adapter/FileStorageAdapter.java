package adapter;

public class FileStorageAdapter implements StorageService {
    private AzureFileStorage azureFileStorage;

    public FileStorageAdapter(AzureFileStorage azureFileStorage) {
        this.azureFileStorage = azureFileStorage;
    }

    @Override
    public void storeData(String data) {
        azureFileStorage.uploadFileData(data);
    }

    @Override
    public String retrieveData(String key) {
        return azureFileStorage.downloadFileData(key);
    }
}
