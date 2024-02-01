package auditor;

/**
 * The Instrument enum represents musical instruments and their associated sounds.
 *
 *  @author Kilian Demont
 *  @author Julien Holzer
 */
public enum Instrument {
    piano("ti-ta-ti"),
    trumpet("pouet"),
    flute("trulu"),
    violin("gzi-gzi"),
    drum("boum-boum");

    private String sound;


    /**
     * Constructs an Instrument with the specified sound.
     *
     * @param sound The sound associated with the instrument.
     */
    Instrument(String sound){
        this.sound = sound;
    }

    /**
     * Gets the sound associated with the instrument.
     *
     * @return The sound of the instrument.
     */
    public String getSound() {
        return sound;
    }

    /**
     * Gets the Instrument associated with the specified sound.
     *
     * @param sound The sound for which to determine the associated instrument.
     * @return The Instrument associated with the specified sound, or null if not found.
     */
    public static Instrument fromSound(String sound) {
        for (Instrument instrument : Instrument.values()) {
            if (instrument.sound.equals(sound)) {
                return instrument;
            }
        }
        return null;
    }
}
