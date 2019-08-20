package kafka.src.producers.mongo;
/*
 * @author atulyadav on 2019-07-31 18:36
 */

import com.mongodb.MongoClient;

public class MongoFactory {

    volatile static MongoClient mongoClient = null;

    public static MongoClient getMongoConnection() {
        return getSingletonMongoInstance();
    }

    private static MongoClient getSingletonMongoInstance() {
        if(null == mongoClient){
            synchronized (MongoFactory.class){
                if(null == mongoClient){
                    mongoClient = new MongoClient();
                    System.out.println("Connected to "+mongoClient.getConnectPoint());
                }
            }
        }
        return mongoClient;
    }

}
