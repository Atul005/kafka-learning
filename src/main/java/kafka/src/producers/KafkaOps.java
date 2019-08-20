package kafka.src.producers;
/*
 * @author atulyadav on 2019-06-14 01:54
 */

import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.atomic.AtomicBoolean;

public class KafkaOps {

    private static KafkaOps INSTANCE = null;
    private static final AtomicBoolean LOCK = new AtomicBoolean();

    public static KafkaOps getInstance() {
        if (null == INSTANCE) {
            synchronized (LOCK) {
                if (null == INSTANCE) {
                    INSTANCE = new KafkaOps();
                }
            }
        }
        return INSTANCE;
    }

    public void handleOnCompletionCallback(String id, RecordMetadata metadata, Exception exception) {
        RecordWrittenResponse recordWrittenResponse = new RecordWrittenResponse();
        recordWrittenResponse.setException(exception);
        recordWrittenResponse.setHasOffset(metadata.hasOffset());
        recordWrittenResponse.setOffset(metadata.offset());
        recordWrittenResponse.setPartition(metadata.partition());
        recordWrittenResponse.setTimeStamp(metadata.timestamp());
        recordWrittenResponse.setTopic(metadata.topic());
        Boolean success = InMemoryDatabase.getInstance().storeRecordResponse(id, recordWrittenResponse);
        ProducerServiceImpl.INSTANCE.saveRecordWrittenResponseFromBroker(recordWrittenResponse);
        if (success) {
            System.out.println("Processed Document stored in InMemoryDatabase, Response from Kafka " + recordWrittenResponse);
        } else {
            System.out.println("failed storing in InMemoryDatabase");
        }

    }

}
