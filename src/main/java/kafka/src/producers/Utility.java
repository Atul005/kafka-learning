package kafka.src.producers;
/*
 * @author atulyadav on 2019-06-18 01:17
 */

import com.google.gson.Gson;

public class Utility {
    public static String getJsonForObject(Object o) {
        Gson gson = new Gson();
        return gson.toJson(o);
    }
}
