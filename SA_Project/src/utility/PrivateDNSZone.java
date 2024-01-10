package utility;

public class PrivateDNSZone extends DNSComponent {
    @Override
    public void resolve(String domain) {
        System.out.println("私有DNS区域解析: " + domain);
    }

    // 添加额外的私有DNS区域相关功能
    public void addRecord(String domain, String ipAddress) {
        System.out.println("添加记录：" + domain + " -> " + ipAddress);
    }
}
