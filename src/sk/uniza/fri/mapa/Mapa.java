package sk.uniza.fri.mapa;

import sk.uniza.fri.Hrac;
import sk.uniza.fri.Konstanty;
import sk.uniza.fri.pokemoni.Bulbasaur;
import sk.uniza.fri.pokemoni.Charmander;
import sk.uniza.fri.pokemoni.Pokemon;
import sk.uniza.fri.pokemoni.Squirtle;

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Mapa {

    private Policko[][] mapa;
    private Hrac hrac;
    private Scanner citac;
    private Random generator;

    /**
     * Vytvori mapu
     * @param hrac - vytvoreni na zaciatku hry
     */
    public Mapa(Hrac hrac) {
        this.mapa = new Policko[Konstanty.POCET_STLPCOV][Konstanty.POCET_RIADKOV];
        this.hrac = hrac;
        this.nacitajMapu();
        this.hrac.nastavPoziciu(this.mapa[Konstanty.START_STLPEC][Konstanty.START_RIADOK]);
        this.generator = new Random();
    }

    /**
     * nacita mapu zo suboru
     */
    private void nacitajMapu() {
        try {
            File map = new File("src/sk/uniza/fri/mapa/mapa.txt");
            this.citac = new Scanner(map);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int j = 0; j < Konstanty.POCET_STLPCOV; j++) {
            for (int i = 0; i < Konstanty.POCET_RIADKOV; i++) {
                TypPolicka typ = TypPolicka.values()[this.citac.nextInt()];
                this.mapa[j][i] = new Policko(Konstanty.VELKOST_POLICKA * i, Konstanty.VELKOST_POLICKA * j, typ);
                this.mapa[j][i].setSuradnice(j, i);
            }
        }
    }


    /**
     * vykresli nacitanu mapu
     * @param g2 graficky component
     */
    public void nakresliMapu(Graphics2D g2) {
        for (int j = 0; j < this.mapa.length; j++) {
            for (int i = 0; i < this.mapa[j].length; i++) {
                this.mapa[j][i].nakresli(g2);
            }
        }
    }



    public Policko getPolicko(int riadok, int stlpec) {
        return this.mapa[riadok][stlpec];
    }

    /**
     * Vygeneruje pokemona na zaklade policka daneho parametrom a jeho typu
     */
    public Pokemon vygeneruj(Policko policko) {
        int lvl = this.generator.nextInt(5 - 1) + 1;
        Pokemon pokemon = null;
        if (policko.isGeneruje()) {
            switch (policko.getTyp()) {
                case OHEN:
                    pokemon = new Charmander(lvl);
                    break;
                case TRAVA:
                    pokemon = new Bulbasaur(lvl);
                    break;
                case VODA:
                    pokemon = new Squirtle(lvl);
                    break;
            }
        }
        return pokemon;
    }

}
