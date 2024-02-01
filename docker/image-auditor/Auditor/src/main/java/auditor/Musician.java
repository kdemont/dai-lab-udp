package auditor;

import java.util.UUID;

/**
 * The Musician class represents a musician with a unique identifier (UUID) and an associated musical instrument.
 *
 *  @author Kilian Demont
 *  @author Julien Holzer
 */
public class Musician {
    private UUID uuid;
    private Instrument instrument;

    /**
     * Default constructor for the Musician class.
     */
    public Musician(){

    }

    /**
     * Constructs a Musician with the specified instrument, generating a unique UUID for the musician.
     *
     * @param instrument The musical instrument associated with the musician.
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
     * Sets the UUID of the musician.
     *
     * @param uuid The UUID to set for the musician.
     */
    public void setUuid(UUID uuid){
        this.uuid = uuid;
    }

    /**
     * Gets the musical instrument associated with the musician.
     *
     * @return The musical instrument of the musician.
     */
    public Instrument getInstrument() {
        return instrument;
    }

    /**
     * Sets the musical instrument for the musician.
     *
     * @param instrument The musical instrument to set for the musician.
     */
    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    /**
     * Returns a string representation of the Musician object.
     *
     * @return A string representation of the Musician object, including UUID, instrument, and associated sound.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + ": {uuid: " + uuid + ", instrument: " + instrument + ", sound : " + instrument.getSound() + "}";
    }
}
