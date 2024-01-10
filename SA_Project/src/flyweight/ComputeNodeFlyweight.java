package flyweight;

public class ComputeNodeFlyweight {
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
