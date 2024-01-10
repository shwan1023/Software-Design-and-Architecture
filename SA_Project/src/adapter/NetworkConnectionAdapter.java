package adapter;

import bridge.AdvancedNetworkConnection;
import bridge.NetworkConnectionInterface;

public class NetworkConnectionAdapter implements NetworkConnectionInterface {
    private AdvancedNetworkConnection networkConnection;

    public NetworkConnectionAdapter(AdvancedNetworkConnection networkConnection) {
        this.networkConnection = networkConnection;
    }

    @Override
    public void executeConnection() {
        networkConnection.connect();
        networkConnection.advancedConnect();
    }
}
