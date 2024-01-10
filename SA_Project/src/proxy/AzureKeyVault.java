package proxy;

import java.lang.reflect.Proxy;
import factory.PoolResourceConnector;

public class AzureKeyVault implements IKeyVault {
    private IKeyVault proxy; // 代理引用
    private PoolResourceConnector resourceConnector;

    public AzureKeyVault(PoolResourceConnector resourceConnector) {
        this.resourceConnector = resourceConnector;
    }

    @Override
    public String getSecret(String key) {
        if (proxy == null) {
            throw new IllegalStateException("必须通过代理访问Key Vault");
        }
        return "SecretValueFor" + key;
    }

    public IKeyVault getProxy(String authorizedUser) {
        if (proxy == null) {
            proxy = (IKeyVault) Proxy.newProxyInstance(
                    AzureKeyVault.class.getClassLoader(),
                    new Class<?>[] { IKeyVault.class },
                    new KeyVaultInvocationHandler(this, authorizedUser));
        }
        return proxy;
    }

    public void connectPool(String poolConfig, String key) {
        String secret = getSecret(key);
        resourceConnector.connectToKeyVault(poolConfig, secret);
    }
}
