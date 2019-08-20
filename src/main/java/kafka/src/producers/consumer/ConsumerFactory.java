package kafka.src.producers.consumer;/*
 * @author atulyadav on 2019-07-31 11:58
 */

import kafka.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class ConsumerFactory {

    public static KafkaConsumer<String, Object> getConsumer(){
        return new KafkaConsumer<String, Object>(ConsumerProps.getConsumerProps());
    }
}
