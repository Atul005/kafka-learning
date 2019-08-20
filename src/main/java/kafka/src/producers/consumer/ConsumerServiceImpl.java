package kafka.src.producers.consumer;
/*
 * @author atulyadav on 2019-08-20 17:05
 */

import kafka.src.producers.Customer;
import kafka.src.producers.Utility;
import kafka.src.producers.mongo.MongoService;
import kafka.src.producers.mongo.MongoServiceImpl;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class ConsumerServiceImpl implements ConsumerService {

    private static final String PROCESSED_WRITTEN_RECORDS = "processedWrittenRecords";
    private static final String RAW_RECORDS = "rawRecords";
    private static final ConsumerService instance = new ConsumerServiceImpl();
    private static final String DATABASE = "kafkaProject";
    private static final MongoService mongoService = MongoServiceImpl.getMongoServiceInstance();
    private static final String CUSTOMER = "customer";

    public static ConsumerService getInstance(){
        return instance;
    }

    @Override
    public void processRecord(ConsumerRecord<String, Object> record) {
        mongoService.insertDocument(DATABASE, RAW_RECORDS, record);
        Customer value = (Customer) Utility.getObjectFromJson(record.value().toString(), Customer.class);
        mongoService.insertDocument(DATABASE, CUSTOMER, value);
    }

}
