package auditor;

import java.util.UUID;

public class Musician {
    private UUID uuid;
    private Instrument instrument;

    // Constructor
    public Musician(){

    }

    public Musician(Instrument instrument) {
        this.uuid = UUID.randomUUID();
        this.instrument = instrument;
    }

    // Getter methods
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid){
        this.uuid = uuid;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public String getSound() {
        return instrument.getSound();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": {uuid: " + uuid + ", instrument: " + instrument + ", sound : " + instrument.getSound() + "}";
    }

}
