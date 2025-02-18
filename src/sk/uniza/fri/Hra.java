package sk.uniza.fri;

import sk.uniza.fri.gui.HernaPlochaPanel;
import sk.uniza.fri.gui.HernyFrame;
import sk.uniza.fri.mapa.Mapa;
import sk.uniza.fri.pokemoni.Bulbasaur;
import sk.uniza.fri.pokemoni.Charmander;
import sk.uniza.fri.pokemoni.Pokemon;
import sk.uniza.fri.pokemoni.Squirtle;
import sk.uniza.fri.predmety.PokeBall;
import sk.uniza.fri.predmety.Potion;
import sk.uniza.fri.predmety.Predmet;
import sk.uniza.fri.predmety.Udica;

import javax.swing.JOptionPane;


public class Hra {

    private HernyFrame hernyFrame;
    private HernaPlochaPanel hernaPlochaPanel;
    private Hrac hrac;
    private Predmet pokeBall;
    private Potion potion;
    private Udica udica;
    private boolean hra;
    private boolean suboj;

    private Pokemon[] pokemon;
    private Mapa mapa;

    /**
     * Vytvori a inicializuje hru
     */
    public Hra() {
        this.pokemon = new Pokemon[]{new Charmander(1), new Squirtle(1), new Bulbasaur(1)};
        this.inicizializuj();
    }

    private void inicizializuj() {
        String meno = this.inputMeno("Zadaj svoje meno");
        while (meno == null) {
            meno = this.inputMeno("Zadaj svoje meno");
        }
        int vyber = this.inputPokemon("Ahoj " + meno + "!\n" + "Vyber si startujuceho pokemona");
        while (vyber == JOptionPane.CLOSED_OPTION) {
            vyber = this.inputPokemon("Ahoj " + meno + "!\n" + "Vyber si startujuceho pokemona");
        }
        Pokemon starter = this.pokemon[vyber];
        this.hrac = new Hrac(meno, Konstanty.START_RIADOK, Konstanty.START_STLPEC, starter);
        this.potion = Potion.dajInstanciu();
        this.pokeBall = PokeBall.dajInstanciu(this.hrac);
        this.udica = Udica.dajInstanciu();
        this.hrac.pridajPredmet(this.potion);
        for (int i = 0; i < 5; i++) {
            this.hrac.pridajPredmet(this.pokeBall);
        }
        this.hrac.pridajPredmet(this.udica);
        this.hernaPlochaPanel = new HernaPlochaPanel(this.hrac);
        this.mapa = new Mapa(this.hrac);

    }

    private String inputMeno(String text) {
        String input = JOptionPane.showInputDialog(null, text, "Vitaj", JOptionPane.INFORMATION_MESSAGE);
        if (input != null && !input.isEmpty()) {
            return input;
        } else {
            return null;
        }
    }

    private int inputPokemon(String text) {
        int vyber = JOptionPane.showOptionDialog(null, text, "Vyber pokemona", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, this.pokemon, null);
        return vyber;
    }



}
