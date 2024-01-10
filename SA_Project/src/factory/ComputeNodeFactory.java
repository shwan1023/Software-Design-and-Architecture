package factory;

import flyweight.ComputeNodeFlyweight;

// 抽象计算节点工厂类
public abstract class ComputeNodeFactory {
    protected PoolResourceConnector resourceConnector;

    public ComputeNodeFactory(PoolResourceConnector resourceConnector) {
        this.resourceConnector = resourceConnector;
    }

    public abstract ComputeNodeFlyweight createComputeNode(String config, String alloc);

    public void connectResources(String identity, String storage, String batch, String keyVaultSecret) {
        resourceConnector.connectToManagedIdentity(identity);
        resourceConnector.connectToStorageAccount(storage);
        resourceConnector.connectToBatchComponent(batch);
        resourceConnector.connectToKeyVault("PoolConfig", keyVaultSecret);
    }
}
