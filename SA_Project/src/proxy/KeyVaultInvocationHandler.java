package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class KeyVaultInvocationHandler implements InvocationHandler {
    private AzureKeyVault realKeyVault; // 真实的Key Vault对象
    private String authorizedUser; // 授权用户

    public KeyVaultInvocationHandler(AzureKeyVault realKeyVault, String authorizedUser) {
        this.realKeyVault = realKeyVault;
        this.authorizedUser = authorizedUser;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("Admin".equals(authorizedUser)) {
            return method.invoke(realKeyVault, args);
        }
        throw new SecurityException("未授权访问Key Vault");
    }
}
