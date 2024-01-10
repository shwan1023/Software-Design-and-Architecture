package bridge;

public abstract class BasicNetworkConnection {
    protected ConnectionStrategy strategy;

    public BasicNetworkConnection(ConnectionStrategy strategy) {
        this.strategy = strategy;
    }

    public abstract void connect();
}
