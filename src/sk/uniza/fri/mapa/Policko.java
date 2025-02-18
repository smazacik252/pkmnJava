package sk.uniza.fri.mapa;

import sk.uniza.fri.Konstanty;
import sk.uniza.fri.Obrazok;


import java.awt.Graphics2D;



public class Policko {

    private Obrazok policko;
    private boolean kolizia;
    private int riadok;
    private int stlpec;
    private int x;
    private int y;
    private TypPolicka typ;
    private boolean generuje;

    /**
     * Vytvori policko
     * @param x - suradnica x
     * @param y - suradnica y
     * @param typ - typ policka
     */
    public Policko(int x, int y, TypPolicka typ) {
        this.policko = new Obrazok(typ.getCesta());
        this.kolizia = typ.isKolizia();
        this.generuje = typ.isGenerujuce();
        this.x = x;
        this.y = y;
        this.typ = typ;
    }

    public boolean isGeneruje() {
        return this.generuje;
    }

    public boolean isKolizia() {
        return this.kolizia;
    }

    /**
     * Nakresli policko
     * @param g2 -kresliaci komponent
     */
    public void nakresli(Graphics2D g2) {
        this.policko.nakresli(g2, this.x, this.y, Konstanty.VELKOST_POLICKA, Konstanty.VELKOST_POLICKA);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setSuradnice(int riadok, int stlpec) {
        this.riadok = riadok;
        this.stlpec = stlpec;
    }

    public int getRiadok() {
        return this.riadok;
    }

    public int getStlpec() {
        return this.stlpec;
    }

    public TypPolicka getTyp() {
        return this.typ;
    }
}
