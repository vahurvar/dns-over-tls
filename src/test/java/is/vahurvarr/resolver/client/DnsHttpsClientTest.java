package is.vahurvarr.resolver.client;

import is.vahurvarr.resolver.model.DnsReply;
import org.junit.Test;

public class DnsHttpsClientTest {

    @Test
    public void name() {
        DnsHttpsClient dnsHttpsClient = new DnsHttpsClient();
        DnsReply dnsReply = dnsHttpsClient.getDnsReply("google.com", 12);
        System.out.println(dnsReply);
    }

}