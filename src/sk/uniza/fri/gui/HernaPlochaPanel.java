package sk.uniza.fri.gui;

import sk.uniza.fri.Hrac;
import sk.uniza.fri.mapa.Mapa;
import sk.uniza.fri.mapa.Policko;
import sk.uniza.fri.mapa.TypPolicka;
import sk.uniza.fri.pokemoni.Pokemon;

import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;




public class HernaPlochaPanel extends JPanel implements KeyListener {

    private Hrac hrac;
    private Mapa mapa;
    private HernyFrame hernyFrame;

    /**
     * Vytvori herne okno
     * @param hrac -hrac vytvoreny na zaciatku hry
     */
    public HernaPlochaPanel(Hrac hrac) {
        super();
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(this);
        this.hrac = hrac;
        this.mapa = new Mapa(this.hrac);
        this.hernyFrame = new HernyFrame(this.hrac);
        this.hernyFrame.setHra(this);

    }


    /**
     * Vygeneruje bojovy panel ktory nasledne prekryje herny
     * @param policko vygeneruje z neho typ pokemona
     * @return bojovy panel
     */
    public SubojPlochaPanel vygeneruj(Policko policko) {
        Pokemon p = this.mapa.vygeneruj(policko);
        SubojPlochaPanel vrat = null;
        double random = Math.random();
        if ((random <= 0.1) && (p != null) && (policko.getTyp() != TypPolicka.VODA)) {
            vrat = new SubojPlochaPanel(p, this.hrac.dajPokemona(0));
            return vrat;
        }
        return vrat;
    }

    /**
     * Sluzi na kreslenie obrazkov
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D)g;
        this.mapa.nakresliMapu(g2D);
        this.hrac.nakresli(g2D);
        g2D.dispose();
    }


    /**
     * v tejto implementacii nerobi nic
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }


    /**
     * Ovladanie hraca, kazdy pohyb hraca ma 10% sancu vygenerovat suboj s divokym pokemonom
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        Policko policko;
        if (code == KeyEvent.VK_W) {
            policko = this.mapa.getPolicko(this.hrac.getRiadok() - 1, this.hrac.getStlpec());
            this.hrac.posunHore(policko);
            SubojPlochaPanel vyg = this.vygeneruj(policko);
            if (vyg != null) {
                this.hernyFrame.setSubojHPanel(vyg);
            }
            this.repaint();
        }
        if (code == KeyEvent.VK_A) {
            policko = this.mapa.getPolicko(this.hrac.getRiadok(), this.hrac.getStlpec() - 1);
            this.hrac.posunVlavo(policko);
            SubojPlochaPanel vyg = this.vygeneruj(policko);
            if (vyg != null) {
                this.hernyFrame.setSubojHPanel(vyg);
            }
            this.repaint();
        }
        if (code == KeyEvent.VK_S) {
            policko = this.mapa.getPolicko(this.hrac.getRiadok() + 1, this.hrac.getStlpec());
            this.hrac.posunDole(policko);
            SubojPlochaPanel vyg = this.vygeneruj(policko);
            if (vyg != null) {
                this.hernyFrame.setSubojHPanel(vyg);
            }
            this.repaint();
        }
        if (code == KeyEvent.VK_D) {
            policko = this.mapa.getPolicko(this.hrac.getRiadok(), this.hrac.getStlpec() + 1);
            this.hrac.posunVpravo(policko);
            SubojPlochaPanel vyg = this.vygeneruj(policko);
            if (vyg != null) {
                this.hernyFrame.setSubojHPanel(vyg);
            }
            this.repaint();
        }
    }


    /**
     * v tejto implementacii nerobi nic
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
