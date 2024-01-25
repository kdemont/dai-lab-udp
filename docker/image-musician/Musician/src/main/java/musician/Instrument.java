package musician;

public enum Instrument {
    piano("ti-ta-ti"),
    trumpet("pouet"),
    flute("trulu"),
    violin("gzi-gzi"),
    drum("boum-boum");

    private String sound;

    Instrument(String sound){
        this.sound = sound;
    }

    public String getSound() {
        return sound;
    }

    public static Instrument fromSound(String sound) {
        for (Instrument instrument : Instrument.values()) {
            if (instrument.sound.equals(sound)) {
                return instrument;
            }
        }
        return null;
    }
}
