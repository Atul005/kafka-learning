package kafka.src.producers.consumer;
/*
 * @author atulyadav on 2019-07-31 12:01
 */

import java.util.Properties;

public class ConsumerProps {

    private final static String BOOTSTRAP_SERVERS = "bootstrap.servers";
    private final static String KEY_DESERIALIZER = "key.deserializer";
    private final static String VALUE_DESERIALIZER = "value.deserializer";
    private final static String GROUP_ID = "group.id";
    private final static String WAIT_MAX_MS = "fetch.max.wait.ms";
    private final static String FETCH_MAX_BYTES = "fetch.min.bytes";
    private final static String AUTO_COMMIT = "enable.auto.commit";
    private final static String AUTO_COMMIT_INTERVAL = "auto.commit.interval.ms";


    public static Properties getConsumerProps() {
        Properties props = new Properties();
        props.setProperty(BOOTSTRAP_SERVERS, "localhost:9092");
        props.setProperty(KEY_DESERIALIZER, "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty(VALUE_DESERIALIZER, "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty(GROUP_ID, "customer");
        props.setProperty(FETCH_MAX_BYTES, "5120");
        props.setProperty(WAIT_MAX_MS, "200");
        props.setProperty(AUTO_COMMIT, "false");
        return props;
    }
}
