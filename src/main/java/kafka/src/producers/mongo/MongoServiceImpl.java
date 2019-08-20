package kafka.src.producers.mongo;
/*
 * @author atulyadav on 2019-08-20 18:12
 */

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import kafka.src.producers.Utility;
import org.bson.Document;

public class MongoServiceImpl implements MongoService {

    private static MongoClient mongoClient = MongoFactory.getMongoConnection();
    private static MongoService mongoServiceInstance = new MongoServiceImpl();

    public static MongoService getMongoServiceInstance() {
        if (null == mongoServiceInstance) {
            synchronized (MongoFactory.class) {
                if (null == mongoServiceInstance) {
                    mongoServiceInstance = new MongoServiceImpl();
                }
            }
        }
        return mongoServiceInstance;
    }

    @Override
    public boolean insertDocument(String databaseName, String collectionName, Object documentJson) {
        try {
            if (databaseName != null && databaseName.length() != 0 && collectionName != null && collectionName.length() != 0 && documentJson != null) {
                MongoDatabase mongoDatabase = mongoClient.getDatabase(databaseName);
                MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
                Document document = Document.parse(Utility.getJsonForObject(documentJson));
                collection.insertOne(document);
                System.out.println("Document inserted");
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Document insertion failed" + ex);
        }
        return false;
    }
}
