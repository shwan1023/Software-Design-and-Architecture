package factory;

// 资源连接器接口
public interface PoolResourceConnector {
    void connectToManagedIdentity(String identityDetails);
    void connectToStorageAccount(String accountDetails);
    void connectToBatchComponent(String batchDetails);
    void connectToKeyVault(String poolConfig, String secret);
}
