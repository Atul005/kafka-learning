package kafka.src.producers;
/*
 * @author atulyadav on 2019-06-14 02:15
 */

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class InMemoryDatabase {

    public static InMemoryDatabase INSTANCE = null;
    public static final AtomicBoolean LOCK = new AtomicBoolean();

    Map<String, RecordWrittenResponse> recordWrittenResponseMap = new HashMap<>();

    public static InMemoryDatabase getInstance() {
        if (null == INSTANCE) {
            synchronized (LOCK) {
                if (null == INSTANCE) {
                    INSTANCE = new InMemoryDatabase();
                }
            }
        }
        return INSTANCE;
    }

    public Boolean storeRecordResponse(String id, RecordWrittenResponse response){
        return recordWrittenResponseMap.put(id, response) == null;
    }
}
