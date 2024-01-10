package proxy;

// 实现 PoolKeyVaultConnector 接口
public class PoolKeyVaultAPI implements PoolKeyVaultConnector {
    private IKeyVault keyVault;

    public PoolKeyVaultAPI(IKeyVault keyVault) {
        this.keyVault = keyVault;
    }

    @Override
    public void connectToKeyVault(String poolConfig, String key) {
        System.out.println("Pool [" + poolConfig + "] connecting to Key Vault with key: " + keyVault.getSecret(key));
    }
}
