package auditor;

/**
 * The ActiveMusician class extends the Musician class and includes information about the last activity time.
 *
 *  @author Kilian Demont
 *  @author Julien Holzer
 */
public class ActiveMusician extends Musician {
    private long lastActivity;

    /**
     * Default constructor for the ActiveMusician class.
     */
    public ActiveMusician(){
        super();
    }

    /**
     * Constructs an ActiveMusician with the specified instrument and last activity time.
     *
     * @param instrument    The musical instrument associated with the musician.
     * @param lastActivity  The timestamp of the last activity of the musician.
     */
    public ActiveMusician(Instrument instrument, long lastActivity) {
        super(instrument);
        this.lastActivity = lastActivity;
    }

    /**
     * Gets the timestamp of the last activity of the musician.
     *
     * @return The timestamp of the last activity.
     */
    public long getLastActivity() {
        return lastActivity;
    }

    /**
     * Sets the timestamp of the last activity of the musician.
     *
     * @param lastActivity The timestamp of the last activity to set.
     */
    public void setLastActivity(long lastActivity) {
        this.lastActivity = lastActivity;
    }

    /**
     * Returns a string representation of the ActiveMusician object.
     *
     * @return A string representation of the ActiveMusician object, including UUID, instrument, and last activity.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + ": {uuid: " + getUuid() + ", instrument: " + getInstrument() + "}";
    }
}
