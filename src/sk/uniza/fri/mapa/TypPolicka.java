package sk.uniza.fri.mapa;

import sk.uniza.fri.Konstanty;


public enum TypPolicka {

    TRAVA(Konstanty.OBRAZOK_TRAVA, false, true),
    VODA(Konstanty.OBRAZOK_VODA, true, true),
    OHEN(Konstanty.OBRAZOK_OHEN, false, true),
    KAMEN(Konstanty.OBRAZOK_KAMEN, false, false);

    private String cesta;
    private boolean kolizia;
    private boolean generujuce;

    TypPolicka(String cesta, boolean kolizia, boolean generujuce) {
        this.cesta = cesta;
        this.kolizia = kolizia;
        this.generujuce = generujuce;
    }

    public String getCesta() {
        return this.cesta;
    }

    public boolean isKolizia() {
        return this.kolizia;
    }

    public boolean isGenerujuce() {
        return this.generujuce;
    }
}
