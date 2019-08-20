package kafka.src.producers.consumer;
/*
 * @author atulyadav on 2019-08-20 17:05
 */

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface ConsumerService {

    void processRecord(ConsumerRecord<String, Object> record);
}
