package flyweight;

import java.util.HashMap;
import java.util.Map;

public class PrivateEndpointFactory {
    private Map<String, PrivateEndpointFlyweight> endpoints = new HashMap<>();

    public PrivateEndpointFlyweight getEndpoint(String configuration) {
        endpoints.putIfAbsent(configuration, new PrivateEndpointFlyweight(configuration));
        return endpoints.get(configuration);
    }
}
