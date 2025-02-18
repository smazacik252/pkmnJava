package sk.uniza.fri.predmety;

import sk.uniza.fri.mapa.Policko;
import sk.uniza.fri.mapa.TypPolicka;

import javax.swing.JOptionPane;


public class Udica extends Predmet<Policko> {

    private static Udica instancia = null;

    public static Udica dajInstanciu() {
        if (instancia == null) {
            instancia = new Udica();
        }
        return instancia;
    }


    private Udica() {
        super("Udica");
    }

    /**
     * Zacne suboj s vodnym pokemon ak je policko vodne
     * @param policko - policko z ktoreho ma byt vygenerovany pokemon
     * @return true ak je policko vodneho type false inac
     */
    @Override
    public int pouzi(Policko policko) {
        if (policko.getTyp() == TypPolicka.VODA) {
            JOptionPane.showMessageDialog(null, "Lovis vodneho pokemona");
            return 1;
        } else {
            JOptionPane.showMessageDialog(null, "Skus pouzit na vodne policko");
            return -1;
        }
    }

    @Override
    public void zvysPocetnost() {
        if (super.getPocetnost() >= 1) {
            System.out.println("Udicu mozes mat len jednu");
        } else {
            super.zvysPocetnost();
        }
    }

    @Override
    public String dajPopis() {
        return "Pouzi pri vode pre suboj s vodnym pokemonom";
    }

}
