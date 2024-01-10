package pool;

import adapter.StorageService;
import proxy.PoolKeyVaultConnector;
import factory.PoolResourceConnector;

// Linux计算节点池
public class LinuxComputeNodePool implements ComputeNodePool {
    private StorageService storageService;
    private PoolResourceConnector resourceConnector;
    private PoolKeyVaultConnector keyVaultConnector;

    public LinuxComputeNodePool(StorageService storageService, PoolResourceConnector resourceConnector,
                                PoolKeyVaultConnector keyVaultConnector) {
        this.storageService = storageService;
        this.resourceConnector = resourceConnector;
        this.keyVaultConnector = keyVaultConnector;
    }

    @Override
    public void processData(String data) {
        System.out.println("Linux Pool processing data: " + data);
        storageService.storeData(data);
    }

    @Override
    public void interactWithBatchService(String jobDetails) {
        resourceConnector.connectToBatchComponent(jobDetails);
    }

    @Override
    public void interactWithKeyVault(String secretKey) {
        keyVaultConnector.connectToKeyVault("LinuxPool", secretKey);
    }

    @Override
    public void interactWithStorage(String storageData) {
        storageService.storeData(storageData);
    }
}
