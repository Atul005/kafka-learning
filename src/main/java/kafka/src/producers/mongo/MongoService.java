package kafka.src.producers.mongo;/*
 * @author atulyadav on 2019-08-20 18:11
 */

public interface MongoService {

    boolean insertDocument(String database, String collection, Object document);

}
