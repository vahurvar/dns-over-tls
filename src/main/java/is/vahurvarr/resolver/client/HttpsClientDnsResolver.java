package is.vahurvarr.resolver.client;

import okhttp3.Dns;
import org.xbill.DNS.TextParseException;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class HttpsClientDnsResolver implements Dns {

    private DnsUdpClient dnsUdpClient = new DnsUdpClient();

    @Override
    public List<InetAddress> lookup(String hostname) throws UnknownHostException {
        try {
            return dnsUdpClient.resolve(hostname);
        } catch (TextParseException e) {
            throw new RuntimeException(e);
        }
    }

}
