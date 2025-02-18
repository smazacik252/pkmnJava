package sk.uniza.fri;

import sk.uniza.fri.inventar.Inventar;
import sk.uniza.fri.inventar.InventarPokemoni;
import sk.uniza.fri.inventar.InventarPredmety;
import sk.uniza.fri.mapa.Policko;
import sk.uniza.fri.pokemoni.Pokemon;
import sk.uniza.fri.predmety.Predmet;
import java.awt.Graphics2D;


public class Hrac {

    private String meno;
    private String smer;
    private Obrazok obrazok;
    private Obrazok hore;
    private Obrazok dole;
    private Obrazok vpravo;
    private Obrazok vlavo;
    private int x;
    private int y;
    private int riadok;
    private int stlpec;
    private int riadokSmer;
    private int stlpecSmer;
    private Inventar<Predmet> predmetInventar;
    private Inventar<Pokemon> pokemonInventar;


    public Hrac(String meno, int riadok, int stlpec, Pokemon starter) {
        this.meno = meno;
        this.smer = "dole";
        this.riadok = riadok;
        this.stlpec = stlpec;
        this.predmetInventar = new InventarPredmety();
        this.pokemonInventar = new InventarPokemoni();
        this.pridajPokemona(starter);
        this.inicializujObrazky();
        this.nastavRiadokStlpecSmer();
    }

    /**
     * Nahra si hracove obrazky pre pohyb
     */
    public void inicializujObrazky() {
        this.dole = new Obrazok(Konstanty.OBRAZOK_HRAC_DOLE);
        this.hore = new Obrazok(Konstanty.OBRAZOK_HRAC_HORE);
        this.vlavo = new Obrazok(Konstanty.OBRAZOK_HRAC_VLAVO);
        this.vpravo = new Obrazok(Konstanty.OBRAZOK_HRAC_VPRAVO);
    }

    /**
     * Posunie hraca na policko ktore je nad nim
     * @param policko - policko na ktore sa posuva(horne);
     */
    public void posunHore(Policko policko) {
        if (this.smer.equals("hore") && !policko.isKolizia()) {
            this.nastavPoziciu(policko);
        } else {
            this.smer = "hore";
        }
    }

    /**
     * Posunie hraca na policko je je pod nim
     * @param policko - policko na ktore sa posuva(dole);
     */
    public void posunDole(Policko policko) {
        if (this.smer.equals("dole") && !policko.isKolizia()) {
            this.nastavPoziciu(policko);
        } else {
            this.smer = "dole";
        }
    }

    /**
     * Posunie hraca na policko vedla neho do lava
     * @param policko - policko na ktore sa posuva(vlavo)
     */
    public void posunVlavo(Policko policko) {
        if (this.smer.equals("vlavo") && !policko.isKolizia()) {
            this.nastavPoziciu(policko);
        } else {
            this.smer = "vlavo";
        }
    }

    /**
     * Posunie hraca na policko vedla neho do prava
     * @param policko - policko na ktore sa posuva(vpravo)
     */
    public void posunVpravo(Policko policko) {
        if (this.smer.equals("vpravo") && !policko.isKolizia()) {
            this.nastavPoziciu(policko);
        } else {
            this.smer = "vpravo";
        }
    }

    /**
     * Nakresli hraca
     * @param g2 komponent na vykreslenie
     */
    public void nakresli(Graphics2D g2) {
        switch (this.smer) {
            case "dole":
                this.obrazok = this.dole;
                break;
            case "hore":
                this.obrazok = this.hore;
                break;
            case "vpravo":
                this.obrazok = this.vpravo;
                break;
            case "vlavo":
                this.obrazok = this.vlavo;
                break;
        }
        this.obrazok.nakresli(g2, this.x, this.y, Konstanty.VELKOST_POLICKA, Konstanty.VELKOST_POLICKA);

    }

    /**
     * Nastavi hracovy poziciu danu parametrom, trosku centruje Y hracovho obrazku aby vyzeral ze je viac v strede
     * @param policko - na ktore sa nastavi hracova pozicia
     */
    public void nastavPoziciu(Policko policko) {
        this.riadok = policko.getRiadok();
        this.stlpec = policko.getStlpec();
        this.nastavRiadokStlpecSmer();
        this.x = policko.getX();
        this.y = policko.getY() - 16 / 2;
    }

    public int getRiadok() {
        return this.riadok;
    }

    public int getStlpec() {
        return this.stlpec;
    }

    public void pridajPredmet(Predmet predmet) {
        this.predmetInventar.pridajPolozku(predmet);
    }

    public void pridajPokemona(Pokemon pokemon) {
        pokemon.setDivoky();
        this.pokemonInventar.pridajPolozku(pokemon);
    }


    public Predmet dajPredmet(int index) {
        return this.predmetInventar.dajPolozku(index);
    }

    public Pokemon dajPokemona(int index) {
        return this.pokemonInventar.dajPolozku(index);
    }

    public int getVelkostPredmetInventara() {
        return this.predmetInventar.getSize();
    }

    public int getVelkostPokemonInventara() {
        return this.pokemonInventar.getSize();
    }

    public void odstranPokemona(int index) {
        this.pokemonInventar.odstranPolozku(index);
    }

    public void odstranPredmet(int index) {
        this.predmetInventar.odstranPolozku(index);
    }

    public Inventar<Pokemon> getPokemonInventar() {
        return this.pokemonInventar;
    }

    //tuto by som mohol dat popup a mohlo by to byt bez parametricke
    //skontrolovat co vracia dajPolozku;
    public void pouziPredmet(int index, Object na) {
        Predmet p = this.predmetInventar.dajPolozku(index);
        int pocet = p.pouzi(na);
        if (pocet == 0) {
            this.odstranPredmet(index);
        }
    }

    public int riadokSmer() {
        return this.riadokSmer;
    }

    public int stlpecSmer() {
        return this.stlpecSmer;
    }

    /**
     * nastavi riadok a stlpec o jedno policko dopredu v smere otocenia obrazku
     */
    public void nastavRiadokStlpecSmer() {
        switch (this.smer) {
            case "hore":
                this.riadokSmer = this.riadok + 1;
                this.stlpecSmer = this.stlpec;
                break;
            case "dole":
                this.riadokSmer = this.riadok - 1;
                this.stlpecSmer = this.stlpec;
                break;
            case "vlavo":
                this.riadokSmer = this.riadok;
                this.stlpecSmer = this.riadok + 1;
                break;
            case("vpravo"):
                this.riadokSmer = this.riadok;
                this.stlpecSmer = this.stlpec - 1;
                break;
        }
    }

    public void prehodPokemonov(int i, int i1) {
        this.pokemonInventar.prehodPolozky(i, i1);
    }
}
