package sk.uniza.fri.inventar;

import sk.uniza.fri.Konstanty;
import sk.uniza.fri.pokemoni.Pokemon;

import javax.swing.JOptionPane;
import java.util.Collections;


public class InventarPokemoni extends Inventar<Pokemon> {

    private int kapacita;

    /**
     * Vytvori inventar pre pokemonov
     */
    public InventarPokemoni() {
        super("Inventar pokemonov", Konstanty.KAPACITA_POKEMONOV);
        this.kapacita = Konstanty.KAPACITA_POKEMONOV;
    }


    /**
     * Prida polozku ale iba ked ma volnu kapacitu,maximalna kapacita je 6
     * @param polozka ktoru ma pridat
     */
    @Override
    public void pridajPolozku(Pokemon polozka) {
        if (!super.obsahujePolozku(polozka)) {
            if (this.kapacita != 0) {
                super.pridajPolozku(polozka);
                this.kapacita--;
            } else {
                JOptionPane.showMessageDialog(null, "Kapacita pokemonov je plna");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pokemon uz je v inventari");
        }
    }


    /**
     * Odstrani polozku,pokial je polozka posledna v inventari tak ju neodstrani
     * @param polozka ktoru ma odstranit
     * @return vrati true false podla toho ci sa mu to podarilo
     */
    @Override
    public boolean odstranPolozku(Pokemon polozka) {
        if (this.kapacita < 5) {
            this.kapacita++;
            return super.odstranPolozku(polozka);
        } else {
            JOptionPane.showMessageDialog(null, "Minimalny pocet pokemonov je 1");
            return false;
        }
    }


    /**
     * Prehodi miesta dvoch poloziek
     * @param index1 prva polozka
     * @param index2 druha polozka
     */
    @Override
    public void prehodPolozky(int index1, int index2) {
        if (super.getSize() > 1) {
            Collections.swap(super.dajList(), index1, index2);
        } else {
            JOptionPane.showMessageDialog(null, "Nedostatocny pocet pokemonov");
        }
    }


}
