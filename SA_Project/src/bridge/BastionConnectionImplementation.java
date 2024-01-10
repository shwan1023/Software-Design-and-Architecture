package bridge;

public class BastionConnectionImplementation extends AdvancedNetworkConnection {
    public BastionConnectionImplementation(ConnectionStrategy strategy) {
        super(strategy);
    }

    @Override
    public void connect() {
        System.out.println("通过Azure Bastion连接...");
        strategy.execute();
    }

    @Override
    public void advancedConnect() {
        System.out.println("执行高级Bastion连接操作...");
    }
}
