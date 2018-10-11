package is.vahurvarr.resolver;

import is.vahurvarr.resolver.model.AuthorityAnswer;
import org.xbill.DNS.Record;

import java.util.Arrays;
import java.util.List;

public class RecordService {

    private final List<Integer> supportedTypes = Arrays.asList(1, 5, 15, 16, 28);

    public Record getRecord(AuthorityAnswer authorityAnswer) {
        try {
            switch (authorityAnswer.getType()) {
                case 1:
                    return RecordFactory.composeARecord(authorityAnswer);
                case 5:
                    return RecordFactory.composeCnameRecord(authorityAnswer);
                case 28:
                    return RecordFactory.composeAAAARecord(authorityAnswer);
                case 15:
                    return RecordFactory.composeMxRecord(authorityAnswer);
                case 16:
                default: throw new RuntimeException("Not yet implemented");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> getSupportedTypes() {
        return supportedTypes;
    }

}
