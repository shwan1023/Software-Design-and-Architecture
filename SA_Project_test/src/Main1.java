import java.util.HashMap;
import java.util.Map;

// 创建计算节点享元
class ComputeNodeFlyweight {
    private String configuration;
    private String allocation;

    public ComputeNodeFlyweight(String configuration, String allocation) {
        this.configuration = configuration;
        this.allocation = allocation;
    }

    public String getConfiguration() {
        return configuration;
    }

    public String getAllocation() {
        return allocation;
    }
}
// 创建计算节点享元工厂
class FlyweightFactory {
    private Map<String, ComputeNodeFlyweight> flyweights = new HashMap<>();

    public ComputeNodeFlyweight getFlyweight(String config, String alloc) {
        String key = config + ":" + alloc;
        flyweights.putIfAbsent(key, new ComputeNodeFlyweight(config, alloc));
        return flyweights.get(key);
    }
}

// 创建私有链接享元
class PrivateEndpointFlyweight {
    private String endpointConfiguration;

    public PrivateEndpointFlyweight(String endpointConfiguration) {
        this.endpointConfiguration = endpointConfiguration;
    }

    public String getEndpointConfiguration() {
        return endpointConfiguration;
    }
}

// 创建私有链接享元工厂
class PrivateEndpointFactory {
    private Map<String, PrivateEndpointFlyweight> endpoints = new HashMap<>();

    public PrivateEndpointFlyweight getEndpoint(String configuration) {
        endpoints.putIfAbsent(configuration, new PrivateEndpointFlyweight(configuration));
        return endpoints.get(configuration);
    }
}


// 创建抽象工厂以生产流水线。这里还要符合共享API的IO设计
abstract class ComputeNodeFactory {
    protected BranchSubnetResourceConnector resourceConnector = new PoolResourceConnector();

    public abstract ComputeNodeFlyweight createComputeNode(String config, String alloc);

    protected void connectResources(String identity, String storage, String batch) {
        resourceConnector.connectToManagedIdentity(identity);
        resourceConnector.connectToStorageAccount(storage);
        resourceConnector.connectToBatchComponent(batch);
    }
}


// 创建具体工厂方法
//Linux工厂
class LinuxComputeNodeFactory extends ComputeNodeFactory {
    private FlyweightFactory flyweightFactory = new FlyweightFactory();

    @Override
    public ComputeNodeFlyweight createComputeNode(String config, String alloc) {
        return flyweightFactory.getFlyweight(config, alloc);
    }
}

//Windows工厂
class WindowsComputeNodeFactory extends ComputeNodeFactory {
    private FlyweightFactory flyweightFactory = new FlyweightFactory();

    @Override
    public ComputeNodeFlyweight createComputeNode(String config, String alloc) {
        return flyweightFactory.getFlyweight(config, alloc);
    }
}

//私有终端子网工厂
class SubnetComputeNodeFactory extends ComputeNodeFactory {
    private FlyweightFactory flyweightFactory = new FlyweightFactory();
    private PrivateEndpointFactory endpointFactory = new PrivateEndpointFactory();

    @Override
    public ComputeNodeFlyweight createComputeNode(String config, String alloc) {
        ComputeNodeFlyweight nodeFlyweight = flyweightFactory.getFlyweight(config, alloc);
        PrivateEndpointFlyweight endpointFlyweight = endpointFactory.getEndpoint("DefaultEndpointConfig");
        return nodeFlyweight;
    }
}

//共享API，包括一个面向分支资源组件的连接接口和它的实现类
interface BranchSubnetResourceConnector {
    void connectToManagedIdentity(String identityDetails);
    void connectToStorageAccount(String accountDetails);
    void connectToBatchComponent(String batchDetails);
}

//实现类需要分别连接三个组件，即Azure Key Vault、Azure Storage Account以及Azure Batch
class PoolResourceConnector implements BranchSubnetResourceConnector {
    @Override
    public void connectToManagedIdentity(String identityDetails) {
        System.out.println("Connecting to Managed Identity: " + identityDetails);
    }

    @Override
    public void connectToStorageAccount(String accountDetails) {
        System.out.println("Connecting to Storage Account: " + accountDetails);
    }

    @Override
    public void connectToBatchComponent(String batchDetails) {
        System.out.println("Connecting to Batch Component: " + batchDetails);
    }
}

// 测试程序
public class Main1 {
    public static void main(String[] args) {
        ComputeNodeFactory linuxFactory = new LinuxComputeNodeFactory();
        ComputeNodeFlyweight linuxNode = linuxFactory.createComputeNode("LinuxConfig", "HighAllocation");
        linuxFactory.connectResources("LinuxIdentity", "LinuxStorage", "LinuxBatch");
        System.out.println("Linux Node Config: " + linuxNode.getConfiguration());
        System.out.println("Linux Node Allocation: " + linuxNode.getAllocation());

        ComputeNodeFactory windowsFactory = new WindowsComputeNodeFactory();
        ComputeNodeFlyweight windowsNode = windowsFactory.createComputeNode("WindowsConfig", "MediumAllocation");
        windowsFactory.connectResources("WindowsIdentity", "WindowsStorage", "WindowsBatch");
        System.out.println("Windows Node Config: " + windowsNode.getConfiguration());
        System.out.println("Windows Node Allocation: " + windowsNode.getAllocation());

        ComputeNodeFactory subnetFactory = new SubnetComputeNodeFactory();
        ComputeNodeFlyweight subnetNode = subnetFactory.createComputeNode("SubnetConfig", "StandardAllocation");
        subnetFactory.connectResources("SubnetIdentity", "SubnetStorage", "SubnetBatch");
        System.out.println("Subnet Node Config: " + subnetNode.getConfiguration());
        System.out.println("Subnet Node Allocation: " + subnetNode.getAllocation());
    }
}
