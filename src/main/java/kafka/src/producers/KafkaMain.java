package kafka.src.producers;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;


import static kafka.src.producers.ProducerFactory.getProducer;
import static kafka.src.producers.Utility.getJsonForObject;

public class KafkaMain {

    private final static KafkaOps instance = KafkaOps.getInstance();

    public static void main(String[] args) {
        Producer<String, Object> producer = getProducer();

        try {
            while (true) {
                produceRecordAndSubmitToBroker(producer);
                Thread.sleep(10);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void produceRecordAndSubmitToBroker(Producer<String, Object> producer) throws InterruptedException, java.util.concurrent.ExecutionException {
        Customer customer = CustomerService.getInstance().getCustomer();
        producer.send(new ProducerRecord<>(Topics.CUSTOMER.getName(), customer.getId(), getJsonForObject(customer)), new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                instance.handleOnCompletionCallback(customer.getId(), metadata, exception);
            }
        });
    }

}
