package is.vahurvarr.resolver;

import is.vahurvarr.resolver.client.DnsHttpsClient;
import is.vahurvarr.resolver.client.DnsUdpClient;
import is.vahurvarr.resolver.model.AuthorityAnswer;
import is.vahurvarr.resolver.model.DnsReply;
import org.xbill.DNS.Message;
import org.xbill.DNS.Section;

import java.util.List;

public class DnsResolver {

    private final RecordService recordService;
    private final DnsHttpsClient dnsHttpsClient;
    private final DnsUdpClient dnsUdpClient;

    public DnsResolver(RecordService recordService, DnsHttpsClient dnsHttpsClient, DnsUdpClient dnsUdpClient) {
        this.recordService = recordService;
        this.dnsHttpsClient = dnsHttpsClient;
        this.dnsUdpClient = dnsUdpClient;
    }

    public Message answerQuery(Message message) {
        String name = message.getQuestion().getName().toString(true);
        int type = message.getQuestion().getType();

        System.out.println(name + " " + type);

        return isApplicableToHttps(type) ? composeDnsOverHttpsResponse(message, name, type) :
                dnsUdpClient.resolve(message.toWire());
    }

    private boolean isApplicableToHttps(int type) {
        return recordService.getSupportedTypes().contains(type);
    }

    private Message composeDnsOverHttpsResponse(Message message, String name, int type) {
        DnsReply dnsReply = dnsHttpsClient.getDnsReply(name, type);

        addFields(message, dnsReply.getAnswer(), Section.ANSWER);
        addFields(message, dnsReply.getAuthority(), Section.AUTHORITY);

        return message;
    }

    private void addFields(Message message, List<AuthorityAnswer> answer, int section) {
        if (answer != null) {
            answer.stream().map(recordService::getRecord).forEach(record -> message.addRecord(record, section));
        }
    }

}
