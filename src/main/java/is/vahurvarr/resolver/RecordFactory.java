package is.vahurvarr.resolver;

import is.vahurvarr.resolver.model.AuthorityAnswer;
import org.xbill.DNS.*;

import java.net.UnknownHostException;

public class RecordFactory {

    public static ARecord composeARecord(AuthorityAnswer ans) throws UnknownHostException, TextParseException {
        return new ARecord(ans.getNameFromName(), ans.getType(), ans.getTTL(), ans.getInetFromData());
    }

    public static CNAMERecord composeCnameRecord(AuthorityAnswer ans) throws TextParseException {
        return new CNAMERecord(ans.getNameFromName(), ans.getType(), ans.getTTL(), ans.getNameFromData());
    }

    public static AAAARecord composeAAAARecord(AuthorityAnswer ans) throws UnknownHostException, TextParseException {
        return new AAAARecord(ans.getNameFromName(), ans.getType(), ans.getTTL(), ans.getInetFromData());
    }

    public static MXRecord composeMxRecord(AuthorityAnswer ans) throws TextParseException {
        String priority = ans.getData().substring(0, ans.getData().indexOf(' '));
        String data = ans.getData().substring(ans.getData().indexOf(' ') + 1);
        return new MXRecord(
                ans.getNameFromName(), ans.getType(), ans.getTTL(), Integer.valueOf(priority), Name.fromString(data)
        );
    }

}
