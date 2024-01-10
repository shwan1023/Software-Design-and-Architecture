package adapter;

public interface StorageService {
    void storeData(String data);
    String retrieveData(String key);
}
