package is.vahurvarr.resolver.client;

import org.xbill.DNS.*;

import java.net.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DnsUdpClient {

    private static final int DNS_PORT = 53;
    private static final String GOOGLE_DNS_IP = "8.8.8.8";

    public Message resolve(byte[] data) {
        try (DatagramSocket socket = new DatagramSocket()) {
            DatagramPacket requestPacket = new DatagramPacket(data, data.length,InetAddress.getByName(GOOGLE_DNS_IP), DNS_PORT);

            socket.send(requestPacket);
            byte[] dnsReply = new byte[1024];

            DatagramPacket dnsReplyPacket = new DatagramPacket(dnsReply, dnsReply.length);
            socket.receive(dnsReplyPacket);

            return new Message(dnsReply);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<InetAddress> resolve(String hostname) throws UnknownHostException, TextParseException {
        Lookup lookup = new Lookup(hostname, 1);
        lookup.setResolver(new SimpleResolver(GOOGLE_DNS_IP));

        return Arrays.stream(lookup.run())
                .map(this::getAddress)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<InetAddress> getAddress(Record record) {
        InetAddress address = null;
        if (record instanceof ARecord) {
            address = ((ARecord) record).getAddress();
        } else if (record instanceof AAAARecord) {
            address = ((AAAARecord) record).getAddress();
        }
        return Optional.ofNullable(address);
    }

}
