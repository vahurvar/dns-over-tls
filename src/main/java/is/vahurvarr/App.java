package is.vahurvarr;

import is.vahurvarr.resolver.DnsResolver;
import is.vahurvarr.resolver.RecordService;
import is.vahurvarr.resolver.client.DnsHttpsClient;
import is.vahurvarr.resolver.client.DnsUdpClient;
import is.vahurvarr.server.DnsServer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Integer.parseInt;

public class App {

    private static final int DNS_PORT = 53;

    public static void main(String[] args) {
        int serverPort = args.length > 0  ? parseInt(args[0]) : DNS_PORT;
        ExecutorService executorService = Executors.newCachedThreadPool();
        DnsResolver dnsResolver = new DnsResolver(new RecordService(), new DnsHttpsClient(), new DnsUdpClient());
        new DnsServer(serverPort, executorService, dnsResolver).start();
    }

}
