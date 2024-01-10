package bridge;

public class BastionJumpboxConnectionStrategy implements ConnectionStrategy {
    @Override
    public void execute() {
        System.out.println("使用Azure Bastion连接到Jumpbox...");
        System.out.println("登录Azure账户，使用CLI或工具提交Batch作业到Azure Batch服务。");
        // 执行上行链路传输
        uplinkTransfer("Bastion上行链路传输作业批处理");
    }

    private void uplinkTransfer(String task) {
        System.out.println(task);
    }
}
