package musician;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


public class MusicianSerializer {
    private static final Gson gson = new Gson();

    // Serialize a Musician object to JSON
    public static String serialize(Musician musician) {
        JsonObject json = new JsonObject();
        json.addProperty("uuid", String.valueOf(musician.getUuid()));
        json.addProperty("sound", musician.getSound());
        return gson.toJson(json);
    }
}