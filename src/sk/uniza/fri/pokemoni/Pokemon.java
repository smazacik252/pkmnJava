package sk.uniza.fri.pokemoni;

import sk.uniza.fri.Konstanty;
import sk.uniza.fri.Obrazok;

import java.awt.Graphics2D;

/**
 * Hlavna trieda pre pokemonov, dedia z nej vsetci pokemoni
 */
public abstract class Pokemon {

    private String meno;
    private double hp;
    private double hpZaL;
    private double maxHP;
    private int sila;
    private int silaZaL;
    private int obrana;
    private int obranaZaL;
    private int lvl;
    private int skusenosti;
    private int potrebneSkusenosti;
    private String slabyProti;
    private String silnyProti;
    private TypPokemona typ;
    private Obrazok obrazok;
    private boolean divoky;

    public Pokemon(String meno, int lvl, TypPokemona typ, String cesta, int silaZaL, int obranaZaL, double hpZaL) {
        this.typ = typ;
        this.meno = meno;
        this.maxHP = Konstanty.HP_POKEMONOV + (lvl * hpZaL);
        this.hp = this.maxHP;
        this.hpZaL = hpZaL;
        this.sila = this.typ.getSila() + (lvl * silaZaL);
        this.silaZaL = silaZaL;
        this.obrana = this.typ.getObrana() + (lvl * obranaZaL);
        this.obranaZaL = obranaZaL;
        this.lvl = lvl;
        this.potrebneSkusenosti = this.lvl * 10;
        this.skusenosti = 0;
        this.slabyProti = this.typ.getSlabyProti();
        this.silnyProti = this.typ.getSilnyProti();
        this.divoky = true;
        this.obrazok = new Obrazok(cesta);

    }

    /**
     * Vrati reprezentaciu typovej vyhody ako integer
     * @param pokemon - pokemon na ktoreho sa utoci;
     * @return 1 = pokemon ktory utoci ma typovu vyhodu,
     *        -1 = pokemon ktory utoci ma typovu nevyhodu,
     *         0 = pokemon nema ani vyhodu ani nevyhodu;
    */
    public int dajTypovuZavislost(Pokemon pokemon) {
        if (this.typ.name().equals(pokemon.getSlabyProti())) {
            return 1;
        } else if (this.typ.name().equals(pokemon.getSilnyProti())) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * Vypocita silu utoku a nasledne ju odoberie ako HP pokemonovy na ktoreho sa utoci.
     * @param pokemon - pokemon na ktoreho sa utoci;
     * @return Stringovy retazec ktory informuje o efektivnosti utoku
     */
    public String utok(Pokemon pokemon) {
        double silaUtoku;
        double nasobic = this.sila / pokemon.getObrana();
        String s = "";
        int i = this.dajTypovuZavislost(pokemon);
        if (i == 1) {
            silaUtoku = this.sila * 1.5 * nasobic;
            pokemon.uberHp(silaUtoku);
            s += this.meno + " zautocil na " + pokemon.getMeno() + "\n" + "Utok bol velmi efektivny";
            return s;

        } else if (i == -1) {
            silaUtoku = this.sila * 0.5 * nasobic;
            pokemon.uberHp(silaUtoku);
            s += this.meno + " zautocil na " + pokemon.getMeno() + "\n" + "Utok nebol velmi efektivny";
            return s;
        } else {
            silaUtoku = this.sila * nasobic;
            pokemon.uberHp(silaUtoku);
            s += this.meno + " zautocil na " + pokemon.getMeno() + "\n";
            return s;
        }
    }

    public void setSkusenosti(int skusenosti) {
        this.skusenosti = skusenosti;
    }

    /**
     * Zvysi lvl pokemona,zvysi silu,obranu,HP a potrebne skusenosti na dalsi lvl,nastavi momentalne skusenosti na 0
     */
    public void zvysLvl() {
        if (this.lvl < 5) {
            this.lvl++;
            this.sila += this.silaZaL;
            this.obrana += this.obranaZaL;
            this.maxHP += this.hpZaL;
            this.potrebneSkusenosti += 10;
            this.setSkusenosti(0);
        }
    }

    /**
     * Obnovi HP pokemonovy o hodnotu zadanu parametrom, ak je hodnota vacsia ako maximalne HP nastavi maximalne HP
     * @param kolko - ciselna hodnota kolko HP sa ma pokemonovy obnovit
     */
    public void obnovHP(int kolko) {
        if (this.hp + kolko >= this.maxHP) {
            this.hp = this.maxHP;
        } else {
            this.hp += kolko;
        }
    }

    public int getLvl() {
        return this.lvl;
    }

    public String getMeno() {
        return this.meno;
    }

    public void uberHp(double kolko) {
        this.hp -= kolko;
    }

    public double getHp() {
        return this.hp;
    }


    public int getObrana() {
        return this.obrana;
    }


    public void pridajSkusenosti(int kolko) {
        this.skusenosti += kolko;
    }

    public String getSlabyProti() {
        return this.slabyProti;
    }

    public String getSilnyProti() {
        return this.silnyProti;
    }

    /**
     * Vypisuje pocet potrebnych skusenosti na dosiahnutie dalsieho lvlu
     * @return Stringovy retazec vo formate skusenosti/potrebneSkusenosti,
     *         ak pokemon dosial max lvl tak sa vypise to
     */
    public String retazecSkusenosti() {
        if (this.lvl < 5) {
            return this.skusenosti + "/" + this.potrebneSkusenosti;
        } else {
            return "Pokemon dosiahol maximalny lvl";
        }
    }

    public void setDivoky() {
        this.divoky = false;
    }

    public boolean isDivoky() {
        return this.divoky;
    }

    /**
     * Vypisuje pocet momentalnych hp pokemona
     * @return Stringovy retazec vo formate momentalneHP/maximalneHP
     */
    public String retazecHP() {
        return this.hp + "/" + this.maxHP;
    }

    /**
     * Vypise vsetky potrebne informacie o pokemonovy
     * @return meno,hp,typ,sila,obrana,lvl a skusenosti pokemona
     */
    public String dajPopis() {
        return this.meno + " HP:" + this.retazecHP()  + " Typ:" + this.typ + " Sila:" + this.sila +
               " Obrana:" + this.obrana + " lvl:" + this.lvl + " Skusenosti:" + this.retazecSkusenosti();
    }

    public String toString() {
        return this.meno + " " + this.typ;
    }

    /**
     * Nakresli pokemona na subojovomPaneli
     * @param g2 - komponent ktorym sa nakresli
     * @param x - x-kova suradnica
     * @param y - y-nova surdnica
     * @param vyska - vyska obrazku
     * @param sirka - sirka obrazku
     */
    public void nakresli(Graphics2D g2, int x, int y, int vyska, int sirka) {
        this.obrazok.nakresli(g2, x, y, vyska, sirka);
        g2.drawString(this.meno + " LVL:" + this.lvl, x + 60, y );
        g2.drawString("HP:" + this.retazecHP(), x + 60, y + 10);
        g2.drawString("Skusenosti:" + this.retazecSkusenosti(), x + 60, y + 20 );

    }

    /**
     * Dlhsi popis pokemona
     * @return vrati popis pokemona s omackou
     */
    public abstract String dlhyPopis();
}


