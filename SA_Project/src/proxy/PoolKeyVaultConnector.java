package proxy;

public interface PoolKeyVaultConnector {
    void connectToKeyVault(String poolConfig, String key);
}
