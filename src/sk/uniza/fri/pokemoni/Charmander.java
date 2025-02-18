package sk.uniza.fri.pokemoni;

import sk.uniza.fri.Konstanty;


public class Charmander extends Pokemon {

    /**
     * Vytvori instanciu pokemona
     * @param lvl - level pokemona
     */
    public Charmander(int lvl) {
        super("Charmander", lvl, TypPokemona.OHNIVY,  Konstanty.OBRAZOK_CHARMANDER, 5, 2, 10);
    }


    /**
     * Dlhsi popis pokemona
     * @return vrati popis pokemona s omackou
     */
    @Override
    //https://www.pokemongodex.cz/pokedex/charmander/
    public String dlhyPopis() {
        return super.dajPopis() + "\n" +
                "Obtiažne ovládateľný pokémon, odporúčaný na použitie skúsenejším trénerom.\n" +
                " Plamienok na jeho chvoste horí od narodenia az jeho veľkosti možno usudzovať na aktuálnu Charmanderovu silu.\n" +
                " Dáva prednosť teplu a neznáša dážď.\n";
    }
}
