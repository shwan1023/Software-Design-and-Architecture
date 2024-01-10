package factory;

import flyweight.ComputeNodeFlyweight;
import flyweight.FlyweightFactory;

// Windows计算节点工厂
public class WindowsComputeNodeFactory extends ComputeNodeFactory {
    private FlyweightFactory flyweightFactory;

    public WindowsComputeNodeFactory(PoolResourceConnector resourceConnector) {
        super(resourceConnector);
        this.flyweightFactory = new FlyweightFactory();
    }

    @Override
    public ComputeNodeFlyweight createComputeNode(String config, String alloc) {
        return flyweightFactory.getFlyweight(config, alloc);
    }
}
