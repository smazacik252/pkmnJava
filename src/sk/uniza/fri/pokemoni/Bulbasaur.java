package sk.uniza.fri.pokemoni;

import sk.uniza.fri.Konstanty;


public class Bulbasaur extends Pokemon {

    /**
     * Vytvori instanciu pokemona
     * @param lvl - level pokemona
     */
    public Bulbasaur(int lvl) {
        super("Bulbasaur", lvl,  TypPokemona.TRAVNATY, Konstanty.OBRAZOK_BULBASAUR, 3 , 3, 30);

    }

    /**
     * Dlhsi popis pokemona
     * @return vrati popis pokemona s omackou
     */
    @Override
    //https://www.pokemongodex.cz/pokedex/bulbasaur/
    public String dlhyPopis() {
        return super.dajPopis() + "\n" +
                "Cibuľka na chrbte tohto trávneho pokémona naznačuje,\n" +
                " že má bližšie k rastline, než by sa mohlo na prvý pohľad zdať.\n" +
                " Je plná semienok a rastie spolu s Bulbasaurom.\n" +
                " Slúži mu tiež ako zásobáreň energie a úkryt pre dve vystreľovacie zelené liány. \n" +
                "Obľúbený to Bulbasaurov nástroj.\n";
    }
}
