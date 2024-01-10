import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// Azure Key Vault接口
interface IKeyVault {
    String getSecret(String key);
}

// Azure Key Vault的真实实现
class AzureKeyVault implements IKeyVault {
    private IKeyVault proxy; // 代理引用

    @Override
    public String getSecret(String key) {
        if (proxy == null) {
            // 如果没有通过代理访问，则抛出异常
            throw new IllegalStateException("必须通过代理访问Key Vault");
        }
        // 模拟从Azure Key Vault获取秘密
        return "SecretValueFor" + key;
    }

    // 获取代理实例的方法
    public IKeyVault getProxy(String authorizedUser) {
        if (proxy == null) {
            // 使用Java反射创建代理实例
            proxy = (IKeyVault) Proxy.newProxyInstance(
                    AzureKeyVault.class.getClassLoader(),
                    new Class<?>[] { IKeyVault.class },
                    new KeyVaultInvocationHandler(this, authorizedUser));
        }
        return proxy;
    }
}

// 代理的调用处理器
class KeyVaultInvocationHandler implements InvocationHandler {
    private AzureKeyVault realKeyVault; // 真实的Key Vault对象
    private String authorizedUser; // 授权用户

    public KeyVaultInvocationHandler(AzureKeyVault realKeyVault, String authorizedUser) {
        this.realKeyVault = realKeyVault;
        this.authorizedUser = authorizedUser;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 只有当用户为"Admin"时，允许访问
        if ("Admin".equals(authorizedUser)) {
            return method.invoke(realKeyVault, args);
        }
        throw new SecurityException("未授权访问Key Vault");
    }
}

// 与池配置交互的API
interface PoolKeyVaultConnector {
    void connectToKeyVault(String poolConfig, String key);
}

// 实现与池配置交互的API
class PoolKeyVaultAPI implements PoolKeyVaultConnector {
    private IKeyVault keyVault;

    public PoolKeyVaultAPI(IKeyVault keyVault) {
        this.keyVault = keyVault;
    }

    @Override
    public void connectToKeyVault(String poolConfig, String key) {
        System.out.println("Pool [" + poolConfig + "] connecting to Key Vault with key: " + keyVault.getSecret(key));
    }
}

// 测试代理模式的主类
public class Main2 {
    public static void main(String[] args) {
        AzureKeyVault keyVault = new AzureKeyVault();
        PoolKeyVaultConnector keyVaultAPI = new PoolKeyVaultAPI(keyVault.getProxy("wan"));

        // 通过代理作为Admin访问Key Vault
        try {
            keyVaultAPI.connectToKeyVault("LinuxPool", "ExampleKey");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // 尝试直接访问Key Vault（应该失败）
        try {
            System.out.println("直接访问Key Vault: " + keyVault.getSecret("ExampleKey"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // 尝试通过代理作为Guest访问Key Vault（应该失败）
        try {
            PoolKeyVaultConnector keyVaultAPIUnauthorized = new PoolKeyVaultAPI(keyVault.getProxy("Guest"));
            keyVaultAPIUnauthorized.connectToKeyVault("WindowsPool", "ExampleKey");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
