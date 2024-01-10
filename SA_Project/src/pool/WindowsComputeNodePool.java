package pool;

import adapter.StorageService;
import proxy.PoolKeyVaultConnector;
import factory.PoolResourceConnector;

// Windows计算节点池
public class WindowsComputeNodePool implements ComputeNodePool {
    private StorageService storageService;
    private PoolResourceConnector resourceConnector;
    private PoolKeyVaultConnector keyVaultConnector;

    public WindowsComputeNodePool(StorageService storageService, PoolResourceConnector resourceConnector,
                                  PoolKeyVaultConnector keyVaultConnector) {
        this.storageService = storageService;
        this.resourceConnector = resourceConnector;
        this.keyVaultConnector = keyVaultConnector;
    }

    @Override
    public void processData(String data) {
        System.out.println("Windows Pool processing data: " + data);
        storageService.storeData(data);
    }

    @Override
    public void interactWithBatchService(String jobDetails) {
        resourceConnector.connectToBatchComponent(jobDetails);
    }

    @Override
    public void interactWithKeyVault(String secretKey) {
        keyVaultConnector.connectToKeyVault("WindowsPool", secretKey);
    }

    @Override
    public void interactWithStorage(String storageData) {
        storageService.storeData(storageData);
    }

}
