package musician;

import java.util.UUID;

public class Musician {
    private UUID uuid;
    private String instrument;
    private String sound;

    // Constructor
    public Musician(String instrument) {
        this.uuid = UUID.randomUUID();
        this.instrument = instrument;
        this.sound = getSoundForInstrument(instrument);
    }

    // Getter methods
    public UUID getUuid() {
        return uuid;
    }

    public String getInstrument() {
        return instrument;
    }

    public String getSound() {
        return sound;
    }

    // Method to get the sound based on the instrument
    private String getSoundForInstrument(String instrument) {
        // Implement the logic to map instruments to sounds
        switch (instrument.toLowerCase()) {
            case "piano":
                return "ti-ta-ti";
            case "trumpet":
                return "pouet";
            case "flute":
                return "trulu";
            case "violin":
                return "gzi-gzi";
            case "drum":
                return "boum-boum";
            default:
                return "";
        }
    }
}
