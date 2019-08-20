package kafka.src.producers;
/*
 * @author atulyadav on 2019-08-02 23:44
 */

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import kafka.src.producers.mongo.MongoFactory;
import kafka.src.producers.mongo.MongoService;
import kafka.src.producers.mongo.MongoServiceImpl;
import org.bson.Document;

import java.util.List;

public class ProducerServiceImpl implements ProducerService {

    public static final ProducerService INSTANCE = new ProducerServiceImpl();
    private static final MongoClient mongoClient = MongoFactory.getMongoConnection();
    private static final String DATABASE = "kafkaProject";
    private static final String RECORD_WRITTEN_RESPONSE = "recordWrittenResponse";
    private static final MongoService mongoService = MongoServiceImpl.getMongoServiceInstance();


    public static ProducerService getInstance() {
        return INSTANCE;
    }

    @Override
    public void saveRecordWrittenResponseFromBroker(RecordWrittenResponse recordWrittenResponse) {
        mongoService.insertDocument(DATABASE, RECORD_WRITTEN_RESPONSE, recordWrittenResponse);
    }

    @Override
    public List<RecordWrittenResponse> getRecordResponse() {
        MongoCursor<Document> unprocessedRecords = mongoClient.getDatabase(DATABASE).getCollection(RECORD_WRITTEN_RESPONSE).find(Filters.eq("processed", false)).batchSize(1000).iterator();
        return (List<RecordWrittenResponse>) unprocessedRecords.next();
    }


}
