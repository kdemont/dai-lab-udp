package musician;

import java.util.UUID;

/**
 * The Musician class represents a musician with a unique identifier (UUID) and a musical instrument.
 *
 *  @author Kilian Demont
 *  @author Julien Holzer
 */
public class Musician {
    private UUID uuid;
    private Instrument instrument;

    /**
     * Constructor for the Musician class.
     *
     * @param instrument The musical instrument played by the musician.
     */
    public Musician(Instrument instrument) {
        this.uuid = UUID.randomUUID();
        this.instrument = instrument;
    }

    /**
     * Gets the UUID of the musician.
     *
     * @return The UUID of the musician.
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Gets the musical instrument played by the musician.
     *
     * @return The musical instrument.
     */
    public Instrument getInstrument() {
        return instrument;
    }


    /**
     * Gets the sound produced by the musician's instrument.
     *
     * @return The sound produced by the instrument.
     */
    public String getSound() {
        return instrument.getSound();
    }

    /**
     * Overrides the toString method to provide a string representation of the Musician object.
     *
     * @return A string representation of the Musician object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + ": {uuid: " + uuid + ", instrument: " + instrument + ", sound : " + instrument.getSound() + "}";
    }

}
