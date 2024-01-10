package bridge;

import utility.DNSPrivateResolver;

public class DirectVPNConnectionStrategy implements ConnectionStrategy {
    private DNSPrivateResolver dnsResolver;

    public DirectVPNConnectionStrategy(DNSPrivateResolver dnsResolver) {
        this.dnsResolver = dnsResolver;
    }

    @Override
    public void execute() {
        System.out.println("配置并启动VPN服务...");
        dnsResolver.resolve("vpn.example.com");
        System.out.println("通过VPN连接到Azure Batch服务，并使用SSH或RDP客户端进行作业监控。");
    }
}
