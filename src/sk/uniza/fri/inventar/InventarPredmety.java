package sk.uniza.fri.inventar;

import sk.uniza.fri.predmety.Predmet;

import javax.swing.JOptionPane;


public class InventarPredmety extends Inventar<Predmet> {

    /**
     * Vytvori inventar pre predmety
     */
    public InventarPredmety() {
        super("Inventar predmetov");
    }

    /**
     * Prida polozku do inventara
     * @param polozka - polozka ktoru chceme pridat
     */
    @Override
    public void pridajPolozku(Predmet polozka) {
        if (!super.obsahujePolozku(polozka)) {
            super.pridajPolozku(polozka);
            polozka.zvysPocetnost();
        } else {
            if (polozka.getPocetnost() < 99) {
                polozka.zvysPocetnost();
            } else {
                JOptionPane.showMessageDialog(null, "Uz nemozes mat viac tohto predmetu:" + polozka.getNazov());
            }
        }
    }

    /**
     * odstrani polozku z inventara
     * @param polozka - polozku ktoru chceme odstranit
     * @return true ak bola polozka odstranena false ak nie
     */
    @Override
    public boolean odstranPolozku(Predmet polozka) {
        if (super.obsahujePolozku(polozka)) {
            if (polozka.getPocetnost() <= 0) {
                return super.odstranPolozku(polozka);
            } else {
                polozka.znizPocetnost();
                return true;
            }
        } else {
            return false;
        }

    }

    /**
     * Vymeni polozky medzi sebou
     * @param index1 -index prvej polozky ktoru chceme prehodit
     * @param index2 -index druhej polozky ktoru chceme prehodit
     */
    @Override
    public void prehodPolozky(int index1, int index2) {
        JOptionPane.showMessageDialog(null, "Predmety v inventari sa nedaju premiestnovat");
    }


}
