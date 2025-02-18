package sk.uniza.fri.inventar;

import java.util.ArrayList;

/**
 * Hlavna trieda pre Inventar,dedia z nej inventarove triedy
 */
public abstract class Inventar <T> {

    private String nazov;
    private ArrayList<T> polozky;

    public Inventar(String nazov) {
        this.nazov = nazov;
        this.polozky = new ArrayList<>();
    }

    public Inventar(String nazov, int kapacita) {
        this.nazov = nazov;
        this.polozky = new ArrayList<>(kapacita);
    }

    public T dajPolozku(int index) {
        return this.polozky.get(index);
    }

    /**
     * Zisti ci inventar obsahuje polozku
     * @param polozka ktoru ma najst
     * @return vrati true/false podla toho ci polozku nasiel
     */
    public boolean obsahujePolozku(T polozka) {
        boolean obsahuje = false;
        for (T p : this.polozky) {
            if (p == polozka) {
                obsahuje = true;
                break;
            }
        }
        return obsahuje;
    }

    public void pridajPolozku(T polozka) {
        this.polozky.add(polozka);
    }

    public boolean odstranPolozku(T polozka) {
        return this.polozky.remove(polozka);
    }


    protected ArrayList dajList() {
        return this.polozky;
    }

    public abstract void prehodPolozky(int index1, int index2);

    public int getSize() {
        return this.polozky.size();
    }

    public void odstranPolozku(int index) {
        this.polozky.remove(index);
    }

}
