package kafka.src.producers;
/*
 * @author atulyadav on 2019-06-14 01:54
 */

import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.atomic.AtomicBoolean;

public class KafkaOps {

    private static KafkaOps INSTANCE = null;
    private static final AtomicBoolean LOCK = new AtomicBoolean();
    private static final ProducerService instance = ProducerServiceImpl.getInstance();

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
        recordWrittenResponse.setId(id);
        recordWrittenResponse.setException(exception);
        recordWrittenResponse.setHasOffset(metadata.hasOffset());
        recordWrittenResponse.setOffset(metadata.offset());
        recordWrittenResponse.setPartition(metadata.partition());
        recordWrittenResponse.setTimeStamp(metadata.timestamp());
        recordWrittenResponse.setTopic(metadata.topic());
        instance.saveRecordWrittenResponseFromBroker(recordWrittenResponse);
    }

}
