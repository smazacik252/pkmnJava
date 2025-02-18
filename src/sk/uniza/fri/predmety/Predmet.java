package sk.uniza.fri.predmety;


public abstract class Predmet<T> {

    private String nazov;
    private int pocetnost;

    public Predmet(String nazov) {
        this.nazov = nazov;
        this.pocetnost = 0;
    }

    public abstract int pouzi(T o);
    public abstract String dajPopis();


    public String toString() {
        return this.nazov + " " + this.pocetnost + "x" + "\n";
    }

    public int getPocetnost() {
        return this.pocetnost;
    }

    public void zvysPocetnost() {
        this.pocetnost++;
    }

    public void znizPocetnost() {
        if (this.pocetnost > 0) {
            this.pocetnost--;
        }
    }

    public String getNazov() {
        return this.nazov;
    }
}
