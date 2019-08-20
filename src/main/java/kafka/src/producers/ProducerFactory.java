package kafka.src.producers;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

public class ProducerFactory {

    public static Producer<String, Object> getProducer() {
        return new KafkaProducer<String, Object>(ProducerProps.getProducerProps());
    }

}