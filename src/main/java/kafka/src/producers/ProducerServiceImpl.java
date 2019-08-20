package kafka.src.producers;
/*
 * @author atulyadav on 2019-08-02 23:44
 */

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import kafka.src.producers.mongo.MongoFactory;
import org.bson.Document;

import java.util.List;

public class ProducerServiceImpl implements ProducerService {

    public static final ProducerService INSTANCE = new ProducerServiceImpl();
    private static final MongoClient mongoClient = MongoFactory.getMongoConnection();
    private static final String DATABASE = "kafkaProject";
    private static final String RECORD_WRITTEN_RESPONSE = "recordWrittenResponse";
    private static final String PROCESSED_WRITTEN_RECORDS = "processedWrittenRecords";

    @Override
    public ProducerService getInstance() {
        return INSTANCE;
    }

    @Override
    public void saveRecordWrittenResponseFromBroker(RecordWrittenResponse recordWrittenResponse) {
        MongoDatabase kafkaProject = mongoClient.getDatabase(DATABASE);
//        kafkaProject.createCollection("recordWrittenResponse");
        Document document = Document.parse(Utility.getJsonForObject(recordWrittenResponse));
        MongoCollection<Document> collection = kafkaProject.getCollection(RECORD_WRITTEN_RESPONSE);
        collection.insertOne(document);
        System.out.println("Document inserted");
    }

    @Override
    public List<RecordWrittenResponse> getRecordResponse() {
        MongoCursor<Document> unprocessedRecords = mongoClient.getDatabase(DATABASE).getCollection(RECORD_WRITTEN_RESPONSE).find(Filters.eq("processed", false)).batchSize(1000).iterator();
        return (List<RecordWrittenResponse>) unprocessedRecords.next();
    }


}
