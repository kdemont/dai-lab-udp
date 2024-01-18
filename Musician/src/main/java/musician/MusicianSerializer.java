package musician;

import com.google.gson.Gson;

public class MusicianSerializer {
    private static final Gson gson = new Gson();

    // Serialize a Musician object to JSON
    public static String serialize(Musician musician) {
        return gson.toJson(musician);
    }
}