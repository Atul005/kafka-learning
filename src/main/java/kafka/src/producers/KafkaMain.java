package kafka.src.producers;

import kafka.src.producers.myExecutorService.MyExecutorService;
import kafka.src.producers.myExecutorService.MyExecutorServiceImpl;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

import static kafka.src.producers.ProducerFactory.getProducer;
import static kafka.src.producers.Utility.getJsonForObject;

public class KafkaMain {
    public static void main(String[] args) {
        Producer<String, Object> producer = getProducer();

        try {
            while(true){
                produceRecordAndSubmitToBroker(producer);
                Thread.sleep(10);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void produceRecordAndSubmitToBroker(Producer<String, Object> producer) throws InterruptedException, java.util.concurrent.ExecutionException {
        Customer customer = CustomerService.getInstance().getCustomer();
        MyExecutorService myExecutorService = MyExecutorServiceImpl.getInstance();
        ExecutorService executorService = myExecutorService.getExecutorService();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    producer.send(new ProducerRecord<>(Topics.CUSTOMER.getName(), customer.getId(), getJsonForObject(customer)), new Callback() {
                        @Override
                        public void onCompletion(RecordMetadata metadata, Exception exception) {
                            KafkaOps.getInstance().handleOnCompletionCallback(customer.getId(), metadata, exception);
                        }
                    }).get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
