package is.vahurvarr.server;

import java.net.InetAddress;

public class ResponseHelper {

    private final byte[] requestData;
    private final InetAddress ip;
    private final int port;

    public ResponseHelper(byte[] requestData, InetAddress ip, int port) {
        this.requestData = requestData;
        this.ip = ip;
        this.port = port;
    }

    public byte[] getRequestData() {
        return requestData;
    }

    public InetAddress getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

}
