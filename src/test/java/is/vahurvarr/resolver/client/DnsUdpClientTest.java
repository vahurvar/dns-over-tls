package is.vahurvarr.resolver.client;

import org.junit.Test;
import org.xbill.DNS.TextParseException;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import static org.junit.Assert.*;

public class DnsUdpClientTest {

    @Test
    public void shouldReturnAtLeastOneIp() throws UnknownHostException, TextParseException {
        DnsUdpClient client = new DnsUdpClient();
        List<InetAddress> result = client.resolve("google.com");
        assertFalse(result.isEmpty());
    }

}