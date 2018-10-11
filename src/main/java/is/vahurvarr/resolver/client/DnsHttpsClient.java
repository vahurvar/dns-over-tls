package is.vahurvarr.resolver.client;

import is.vahurvarr.resolver.model.DnsReply;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import java.io.IOException;

@SuppressWarnings("ConstantConditions")
public class DnsHttpsClient {

    private static final String GOOGLE_DNS_URL = "https://dns.google.com/resolve";

    private final OkHttpClient client = new OkHttpClient()
            .newBuilder()
            .dns(new HttpsClientDnsResolver())
            .build();

    public DnsReply getDnsReply(String domain, int type) {
        HttpUrl url = buildUrl(domain, type);
        Request request = buildRequest(url);

        try {
            ResponseBody response = client.newCall(request)
                    .execute()
                    .body();

            return JsonMapper.toObject(response.string(), DnsReply.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpUrl buildUrl(String domain, int type) {
        return HttpUrl.parse(GOOGLE_DNS_URL)
                    .newBuilder()
                    .addQueryParameter("name", domain)
                    .addQueryParameter("type", String.valueOf(type))
                    .build();
    }

    private Request buildRequest(HttpUrl url) {
        return new Request.Builder()
                .header("accept", "application/json")
                .header("user-agent", "")
                .url(url)
                .build();
    }

}
