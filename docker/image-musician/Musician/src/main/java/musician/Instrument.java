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
}
