package sk.uniza.fri.predmety;

import sk.uniza.fri.Hrac;
import sk.uniza.fri.pokemoni.Pokemon;

import javax.swing.JOptionPane;


public class PokeBall extends Predmet<Pokemon> {

    //private Inventar<Pokemon> inventar;
    private Hrac hrac;
    private static PokeBall instancia = null;

    /**
     * Vytvori Pokeball
     * @param hrac
     * @return instanciu PokeBallu
     */
    public static PokeBall dajInstanciu(Hrac hrac) {
        if (instancia == null) {
            instancia = new PokeBall(hrac);
        }
        return instancia;
    }

    private PokeBall(Hrac hrac) {
        super("PokeBall");
        this.hrac = hrac;
    }

    /**
     * Puzije pokeball na divokeho pokemona
     * @param pokemon -pokemon na ktoreho ma byt pokeball pouzity
     * @return - true ak je pokemon divoky, false ak nie je
     */
    @Override
    public int pouzi(Pokemon pokemon) {
        if (pokemon.isDivoky()) {
            this.hrac.pridajPokemona(pokemon);
            pokemon.setDivoky();
            super.znizPocetnost();
            JOptionPane.showMessageDialog(null, "Chytil si " + pokemon.getMeno());
            return super.getPocetnost();
        } else {
            JOptionPane.showMessageDialog(null, "Tohto pokemona si uz chytil");
            return -1;
        }
    }


    /**
     * Popis pokeballu
     * @return popis predmetu pokeball
     */
    @Override
    public String dajPopis() {
        return "Sluzi na chytenie pokemona";
    }
}
