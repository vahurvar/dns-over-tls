package is.vahurvarr.resolver.client;

import com.google.gson.Gson;

public class JsonMapper {

    private static final Gson gson = new Gson();

    public static <T> T toObject(String data, Class<T> type) {
        return gson.fromJson(data, type);
    }

}
