package pool;

import factory.PoolResourceConnector;
import flyweight.PrivateEndpointFlyweight;
import flyweight.PrivateEndpointFactory;
import proxy.PoolKeyVaultConnector;

// 私有终端子网池
public class PrivateEndpointSubnetPool implements ComputeNodePool {
    private PrivateEndpointFactory endpointFactory;

    public PrivateEndpointSubnetPool(PrivateEndpointFactory endpointFactory, PoolResourceConnector resourceConnector,
                                     PoolKeyVaultConnector keyVaultConnector) {
        this.endpointFactory = endpointFactory;
    }

    @Override
    public void processData(String data) {
        System.out.println("Subnet Pool processing data: " + data);
        // 处理数据的逻辑...
    }

    @Override
    public void interactWithBatchService(String jobDetails) {
        PrivateEndpointFlyweight endpoint = endpointFactory.getEndpoint("BatchServiceEndpoint");
        endpoint.connectToResource(jobDetails);
    }

    @Override
    public void interactWithKeyVault(String secretKey) {
        PrivateEndpointFlyweight endpoint = endpointFactory.getEndpoint("KeyVaultEndpoint");
        endpoint.connectToResource(secretKey);
    }

    @Override
    public void interactWithStorage(String storageData) {
        PrivateEndpointFlyweight endpoint = endpointFactory.getEndpoint("StorageEndpoint");
        endpoint.connectToResource(storageData);
    }
}
