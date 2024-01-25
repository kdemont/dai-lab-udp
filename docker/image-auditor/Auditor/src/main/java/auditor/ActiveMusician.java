package auditor;

import java.util.UUID;

public class ActiveMusician extends Musician {
    private long lastActivity;

    public ActiveMusician(){
        super();
    }

    public ActiveMusician(UUID uuid, Instrument instrument, long lastActivity) {
        super(instrument);
        this.lastActivity = lastActivity;
    }

    public long getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(long lastActivity) {
        this.lastActivity = lastActivity;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": {uuid: " + getUuid() + ", instrument: " + getInstrument() + "}";
    }

}
