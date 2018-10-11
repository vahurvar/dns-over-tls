package is.vahurvarr.server;

import is.vahurvarr.resolver.DnsResolver;
import org.xbill.DNS.Message;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ResponseHandler implements Runnable {

    private final ResponseHelper responseHelper;
    private final DatagramSocket socket;
    private final DnsResolver dnsResolver;

    public ResponseHandler(ResponseHelper responseHelper, DatagramSocket socket, DnsResolver dnsResolver) {
        this.responseHelper = responseHelper;
        this.socket = socket;
        this.dnsResolver = dnsResolver;
    }

    @Override
    public void run() {
        try {
            Message response = dnsResolver.answerQuery(new Message(responseHelper.getRequestData()));
            DatagramPacket reply = new DatagramPacket(
                    response.toWire(), response.numBytes(), responseHelper.getIp(), responseHelper.getPort()
            );

            socket.send(reply);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
