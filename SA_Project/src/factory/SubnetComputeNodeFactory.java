package factory;

import flyweight.ComputeNodeFlyweight;
import flyweight.FlyweightFactory;
import flyweight.PrivateEndpointFlyweight;
import flyweight.PrivateEndpointFactory;

// 终端子网计算节点工厂
public class SubnetComputeNodeFactory extends ComputeNodeFactory {
    private FlyweightFactory flyweightFactory;
    private PrivateEndpointFactory endpointFactory;

    public SubnetComputeNodeFactory(PoolResourceConnector resourceConnector) {
        super(resourceConnector);
        this.flyweightFactory = new FlyweightFactory();
        this.endpointFactory = new PrivateEndpointFactory();
    }

    @Override
    public ComputeNodeFlyweight createComputeNode(String config, String alloc) {
        ComputeNodeFlyweight nodeFlyweight = flyweightFactory.getFlyweight(config, alloc);
        // 连接到私有终端
        PrivateEndpointFlyweight endpoint = endpointFactory.getEndpoint("DefaultEndpointConfig");
        resourceConnector.connectToManagedIdentity(endpoint.getEndpointConfiguration());
        return nodeFlyweight;
    }
}
