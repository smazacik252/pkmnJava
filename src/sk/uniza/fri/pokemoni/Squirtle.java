package sk.uniza.fri.pokemoni;

import sk.uniza.fri.Konstanty;


public class Squirtle extends Pokemon {


    /**
     * Vytvori instanciu pokemona
     * @param lvl - level pokemona
     */
    public Squirtle(int lvl) {
        super("Squirtle", lvl, TypPokemona.VODNY, Konstanty.OBRAZOK_SQUIRTLE, 2 , 5, 20);
    }


    /**
     * Dlhsi popis pokemona
     * @return vrati popis pokemona s omackou
     */
    @Override
    //https://www.pokemongodex.cz/pokedex/squirtle/
    public String dlhyPopis() {
        return super.dajPopis() + "\n" +
                "Jeden z troch základných pokémonov, ponukanych na začiatku hry.\n" +
                " Jeho špeciálnou schopnosťou je vodné delo, nepretržitý prúd vody prýštiaci z tlamičky.\n" +
                " Vo chvíli hroziaceho nebezpečenstva sa dokáže úplne ukryť do svojho panciera.\n";
    }
}
