package musician;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * The MusicianSerializer class provides functionality to serialize Musician objects to JSON format.
 *
 * @author Kilian Demont
 * @author Julien Holzer
 */
public class MusicianSerializer {
    private static final Gson gson = new Gson();

    /**
     * Serializes a Musician object to JSON format.
     *
     * @param musician The Musician object to be serialized.
     * @return A JSON string representing the serialized Musician object.
     */
    public static String serialize(Musician musician) {
        JsonObject json = new JsonObject();
        json.addProperty("uuid", String.valueOf(musician.getUuid()));
        json.addProperty("sound", musician.getSound());
        return gson.toJson(json);
    }
}