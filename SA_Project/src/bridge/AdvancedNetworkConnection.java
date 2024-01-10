package bridge;

public abstract class AdvancedNetworkConnection extends BasicNetworkConnection {
    public AdvancedNetworkConnection(ConnectionStrategy strategy) {
        super(strategy);
    }

    public abstract void advancedConnect();
}
