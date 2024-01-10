package factory;

import flyweight.ComputeNodeFlyweight;
import flyweight.FlyweightFactory;

// Linux计算节点工厂
public class LinuxComputeNodeFactory extends ComputeNodeFactory {
    private FlyweightFactory flyweightFactory;

    public LinuxComputeNodeFactory(PoolResourceConnector resourceConnector) {
        super(resourceConnector);
        this.flyweightFactory = new FlyweightFactory();
    }

    @Override
    public ComputeNodeFlyweight createComputeNode(String config, String alloc) {
        return flyweightFactory.getFlyweight(config, alloc);
    }
}
