package sk.uniza.fri.predmety;

import sk.uniza.fri.pokemoni.Pokemon;

import javax.swing.JOptionPane;


public class Potion extends Predmet<Pokemon> {

    private static Potion instancia = null;


    /**
     * Vytvori instanciu Potiona
     * @return instanciu Potiona
     */
    public static Potion dajInstanciu() {
        if (instancia == null) {
            instancia = new Potion();
        }
        return instancia;
    }

    private Potion() {
        super("Potion");
    }

    /**
     * Obnovi pokemonovy HP
     * @param pokemon - pokemon na ktoreho ma byt potion pouzity
     * @return - true ak nie je pokemon divoky, false inak.
     */
    @Override
    public int pouzi(Pokemon pokemon) {
        if (!pokemon.isDivoky()) {
            pokemon.obnovHP(pokemon.getLvl() * 10);
            super.znizPocetnost();
            JOptionPane.showMessageDialog(null, pokemon.getMeno() + " sa obnovilo " + pokemon.getLvl() * 10 + " HP");
            return super.getPocetnost();

        } else {
            JOptionPane.showMessageDialog(null, "Potion mozes pouzit len na svojho pokemona");
            return -1;
        }
    }

    /**
     * Vrati popit Potionu
     * @return Kratky popis funckie potionu.
     */
    @Override
    public String dajPopis() {
        return "Obnovi tvojmu pokemonovy 10 * lvl HP";
    }
}
