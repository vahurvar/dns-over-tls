package is.vahurvarr.resolver.model;

import org.xbill.DNS.Name;
import org.xbill.DNS.TextParseException;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class AuthorityAnswer {

    private String name;
    private int type;
    private int TTL;
    private String data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTTL() {
        return TTL;
    }

    public void setTTL(int TTL) {
        this.TTL = TTL;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Name getNameFromName() throws TextParseException {
        return Name.fromString(name);
    }

    public Name getNameFromData() throws TextParseException {
        return Name.fromString(data);
    }

    public InetAddress getInetFromData() throws UnknownHostException {
        return InetAddress.getByName(data);
    }

    @Override
    public String toString() {
        return "AuthorityAnswer{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", TTL=" + TTL +
                ", data='" + data + '\'' +
                '}';
    }

}
