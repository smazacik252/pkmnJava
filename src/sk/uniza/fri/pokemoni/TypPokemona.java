package sk.uniza.fri.pokemoni;


public enum TypPokemona {

    OHNIVY(25, 10, "VODNY", "TRAVNATY"),
    VODNY(10, 25, "TRAVNATY", "OHNIVY"),
    TRAVNATY(15, 15, "OHNIVY" , "VODNY");

    private int sila;
    private int obrana;
    private String slabyProti;
    private String silnyProti;

    TypPokemona(int sila, int obrana, String slabyProti, String silnyProti) {
        this.sila = sila;
        this.obrana = obrana;
        this.slabyProti = slabyProti;
        this.silnyProti = silnyProti;
    }

    public int getSila() {
        return this.sila;
    }

    public int getObrana() {
        return this.obrana;
    }

    public String getSlabyProti() {
        return this.slabyProti;
    }

    public String getSilnyProti() {
        return this.silnyProti;
    }
}
