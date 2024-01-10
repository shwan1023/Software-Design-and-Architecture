package flyweight;

import java.util.HashMap;
import java.util.Map;

public class FlyweightFactory {
    private Map<String, ComputeNodeFlyweight> flyweights = new HashMap<>();

    public ComputeNodeFlyweight getFlyweight(String config, String alloc) {
        String key = config + ":" + alloc;
        flyweights.putIfAbsent(key, new ComputeNodeFlyweight(config, alloc));
        return flyweights.get(key);
    }
}
