package bridge;

public class VPNConnectionImplementation extends AdvancedNetworkConnection {
    public VPNConnectionImplementation(ConnectionStrategy strategy) {
        super(strategy);
    }

    @Override
    public void connect() {
        System.out.println("通过VPN连接...");
        strategy.execute();
    }

    @Override
    public void advancedConnect() {
        System.out.println("执行高级VPN连接操作...");
    }
}
