package kafka.src.producers;

import java.util.Properties;

public class ProducerProps {

    private static final String BOOTSTRAP_SERVERS = "bootstrap.servers";
    private static final String VALUE_SERIALIZER = "value.serializer";
    private static final String KEY_SERIALIZER = "key.serializer";
    private static final String ACKS = "acks";
    private static final String RETRIES = "1";

    public static Properties getProducerProps() {
        Properties producerProps = new Properties();
        producerProps.put(BOOTSTRAP_SERVERS, "localhost:9092");
        producerProps.put(KEY_SERIALIZER, "org.apache.kafka.common.serialization.StringSerializer");
        producerProps.put(VALUE_SERIALIZER, "org.apache.kafka.common.serialization.StringSerializer");
        producerProps.put(ACKS, "all");
        return producerProps;
    }

}