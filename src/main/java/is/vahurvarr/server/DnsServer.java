package is.vahurvarr.server;

import is.vahurvarr.resolver.DnsResolver;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.ExecutorService;

public class DnsServer {

    private final int port;
    private final ExecutorService executorService;
    private final DnsResolver dnsResolver;

    public DnsServer(int port, ExecutorService executorService, DnsResolver dnsResolver) {
        this.port = port;
        this.executorService = executorService;
        this.dnsResolver = dnsResolver;
    }

    public void start() {
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            byte[] buffer = new byte[512];
            DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);

            while (true) {
                datagramSocket.receive(incoming);
                ResponseHelper responseHelper = new ResponseHelper(
                        incoming.getData(), incoming.getAddress(), incoming.getPort()
                );
                executorService.submit(new ResponseHandler(responseHelper, datagramSocket, dnsResolver));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
