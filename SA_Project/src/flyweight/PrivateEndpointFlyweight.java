package flyweight;

// 私有终端享元类
public class PrivateEndpointFlyweight {
    private String endpointConfiguration;

    public PrivateEndpointFlyweight(String endpointConfiguration) {
        this.endpointConfiguration = endpointConfiguration;
    }

    public String getEndpointConfiguration() {
        return endpointConfiguration;
    }

    // 新增方法：连接到资源
    public void connectToResource(String resourceName) {
        // 模拟连接到指定资源的过程
        System.out.println("连接到资源 " + resourceName + " 使用配置: " + endpointConfiguration);
    }
}
