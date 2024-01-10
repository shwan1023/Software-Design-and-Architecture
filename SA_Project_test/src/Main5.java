import java.util.HashMap;
import java.util.Map;

// 存储系统接口
interface StorageService {
    void storeData(String data);
    String retrieveData(String key);
}

// 适配器模式 - 适配器
class StorageAdapter implements StorageService {
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

// Azure Blob存储实现
class AzureBlobStorage {
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

class TableStorageAdapter implements StorageService {
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

// Azure Table存储实现
class AzureTableStorage {
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

class QueueStorageAdapter implements StorageService {
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

// Azure Queue存储实现
class AzureQueueStorage {
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

class FileStorageAdapter implements StorageService {
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

// Azure File存储实现
class AzureFileStorage {
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

// 计算节点池接口
interface ComputeNodePool {
    void processData(String data);
}

// Linux计算节点池
class LinuxComputeNodePool implements ComputeNodePool {
    private StorageService storageService;

    public LinuxComputeNodePool(StorageService storageService) {
        this.storageService = storageService;
    }

    @Override
    public void processData(String data) {
        System.out.println("Linux Pool processing data: " + data);
        storageService.storeData(data);
    }
}

// Windows计算节点池
class WindowsComputeNodePool implements ComputeNodePool {
    private StorageService storageService;

    public WindowsComputeNodePool(StorageService storageService) {
        this.storageService = storageService;
    }

    @Override
    public void processData(String data) {
        System.out.println("Windows Pool processing data: " + data);
        storageService.storeData(data);
    }
}

// 主类 - 测试适配器模式和I/O处理
public class Main5 {
    public static void main(String[] args) {
        AzureBlobStorage blobStorage = new AzureBlobStorage();
        AzureTableStorage tableStorage = new AzureTableStorage();
        AzureQueueStorage queueStorage = new AzureQueueStorage();
        AzureFileStorage fileStorage = new AzureFileStorage();

        StorageAdapter blobAdapter = new StorageAdapter(blobStorage);
        TableStorageAdapter tableAdapter = new TableStorageAdapter(tableStorage);
        QueueStorageAdapter queueAdapter = new QueueStorageAdapter(queueStorage);
        FileStorageAdapter fileAdapter = new FileStorageAdapter(fileStorage);

        ComputeNodePool linuxPool = new LinuxComputeNodePool(blobAdapter);
        ComputeNodePool windowsPool = new WindowsComputeNodePool(tableAdapter);
        ComputeNodePool queuePool = new LinuxComputeNodePool(queueAdapter);
        ComputeNodePool filePool = new WindowsComputeNodePool(fileAdapter);

        // 处理和存储数据
        linuxPool.processData("Linux pool blob data");
        windowsPool.processData("Windows pool table data");
        queuePool.processData("Queue pool data");
        filePool.processData("File pool data");
    }
}
