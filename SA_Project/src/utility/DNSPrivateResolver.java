package utility;

public class DNSPrivateResolver extends DNSComponent {
    @Override
    public void resolve(String domain) {
        System.out.println("DNS私有解析器解析: " + domain);
    }
}
