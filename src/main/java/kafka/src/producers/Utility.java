package kafka.src.producers;
/*
 * @author atulyadav on 2019-06-18 01:17
 */

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Utility {

    private static final Gson gson = new Gson();

    public static String getJsonForObject(Object o) {
        return gson.toJson(o);
    }

    public static Object getObjectFromJson(String json, Class klass){
        return gson.fromJson(json, klass);
    }
}
