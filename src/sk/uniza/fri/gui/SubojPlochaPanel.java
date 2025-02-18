package sk.uniza.fri.gui;

import sk.uniza.fri.Hrac;
import sk.uniza.fri.Konstanty;
import sk.uniza.fri.pokemoni.Pokemon;


import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class SubojPlochaPanel extends JPanel {

    private Pokemon utocnik;
    private Pokemon obranca;
    private boolean utiekol;

    public SubojPlochaPanel(Pokemon utocnik, Pokemon obranca) {
        super();
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.utocnik = utocnik;
        this.obranca = obranca;

    }

    /**
     * Vyhodnocuje ci uz suboj skoncil
     * @return boolean true/false podla podmienok
     */

    public boolean vyhodnotSuboj() {
        if (this.obranca.getHp() <= 0) {
            JOptionPane.showMessageDialog(null, "Prehral si :(");
            System.exit(0);
            return true;
        }
        if (this.utocnik.getHp() <= 0 || !(this.utocnik.isDivoky())) {
            JOptionPane.showMessageDialog(null, "Vyhral si :)");
            JOptionPane.showMessageDialog(null, this.obranca.getMeno() + " ziskal 10 skusenosti");
            this.obranca.pridajSkusenosti(10);
            return true;
        }
        if (this.utiekol) {
            JOptionPane.showMessageDialog(null, "Utiekol si :|");
            return true;
        } else {
            return false;
        }
    }


    public void setUtiekol() {
        this.utiekol = true;
    }

    /**
     * Metoda ktora sa vola po kazdom hracovom utoku,prvy utoci hrac,druhy divoky pokemon
     */
    public void zautoc() {
        JOptionPane.showMessageDialog(null, this.obranca.utok(this.utocnik));
        JOptionPane.showMessageDialog(null, this.utocnik.utok(this.obranca));
    }


    /**
     * skontroluje a nasledne pouzije predmet na vybrateho pokemona
     * @param hrac - hrac z ktoreho inventaru sa predmet bude vyberat
     * @param index - index predmetu v inventari
     * @param naKoho - pokemon na ktoreho sa predmet pouzije,moze byt hracov alebo divoky;
     */
    public void predmet(Hrac hrac, int index, String naKoho) {
        if (naKoho.equals("Svojho")) {
            hrac.pouziPredmet(index, this.obranca);
            JOptionPane.showMessageDialog(null, this.utocnik.utok(this.obranca));
        } else {
            hrac.pouziPredmet(index, this.utocnik);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        this.utocnik.nakresli(g2D, Konstanty.UTOCNIK_X, Konstanty.UTOCNIK_OBRANCA_Y, 200 , 200);
        this.obranca.nakresli(g2D, Konstanty.OBRANCA_X, Konstanty.UTOCNIK_OBRANCA_Y, 200, 200);
        g2D.dispose();
    }
}


