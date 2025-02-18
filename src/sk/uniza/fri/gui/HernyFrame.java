package sk.uniza.fri.gui;

import sk.uniza.fri.Hrac;
import sk.uniza.fri.mapa.Mapa;
import sk.uniza.fri.predmety.Udica;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.CardLayout;

public class HernyFrame {

    private JFrame hernyPanel;
    private JPanel vrchnyPanel;
    private JPanel subojHPanel;
    private JPanel hra;
    private JPanel spodnyPanel;
    private Hrac hrac;
    private Mapa mapa;

    /**
     * Vytvori herne okno
     * @param hrac -hrac ktory bol vytvoreny na zaciatku hry
     */
    public HernyFrame(Hrac hrac) {
        this.inicializuj();
        this.hrac = hrac;
        this.mapa = new Mapa(this.hrac);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void inicializuj() {
        this.hernyPanel = new JFrame();
        this.hernyPanel.setTitle("Pokemon");
        this.hernyPanel.setBounds(100, 100, 775, 800);
        this.hernyPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.hernyPanel.getContentPane().setLayout(null);
        this.hernyPanel.setLocationRelativeTo(null);
        this.hernyPanel.setVisible(true);
        this.vrchnyPanel = new JPanel();
        this.vrchnyPanel.setBounds(0, 0, 759, 455);
        this.hernyPanel.getContentPane().add(this.vrchnyPanel);
        this.vrchnyPanel.setLayout(new CardLayout(0, 0));
        this.hra = new JPanel();
        this.vrchnyPanel.add(this.hra, "0");

        this.subojHPanel = new JPanel();
        this.vrchnyPanel.add(this.subojHPanel, "1");

        this.spodnyPanel = new JPanel();
        this.spodnyPanel.setBackground(Color.WHITE);
        this.spodnyPanel.setBounds(0, 455, 759, 306);
        this.hernyPanel.getContentPane().add(this.spodnyPanel);
        this.spodnyPanel.setLayout(new CardLayout());

        JPanel generalPanel = new JPanel();
        generalPanel.setLayout(new GridLayout(1, 0, 0, 0));

        JButton inventarButton = new JButton("Inventar");
        inventarButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        inventarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HernyFrame.this.inventarDialog(false);
            }
        });
        generalPanel.add(inventarButton);

        JButton pokemoniButton1 = new JButton("Pokemoni");
        pokemoniButton1.setFont(new Font("Tahoma", Font.BOLD, 15));
        pokemoniButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HernyFrame.this.pokemonDialog();
            }
        });
        generalPanel.add(pokemoniButton1);

        JPanel subojDPanel = new JPanel();
        subojDPanel.setLayout(new GridLayout(1, 1, 0, 0));

        JPanel panel1 = new JPanel();
        subojDPanel.add(panel1);
        panel1.setLayout(new GridLayout(0, 1, 0, 0));

        JButton utokButton = new JButton("Utok");
        utokButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        utokButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((SubojPlochaPanel)HernyFrame.this.subojHPanel).zautoc();
                if (((SubojPlochaPanel)HernyFrame.this.subojHPanel).vyhodnotSuboj()) {
                    HernyFrame.this.hernyPanel.transferFocus();
                    ((CardLayout)HernyFrame.this.spodnyPanel.getLayout()).show(HernyFrame.this.spodnyPanel, "1");
                    ((CardLayout)HernyFrame.this.vrchnyPanel.getLayout()).show(HernyFrame.this.vrchnyPanel, "0");
                }
                HernyFrame.this.subojHPanel.repaint();

            }
        });
        panel1.add(utokButton);

        JButton predmetButton = new JButton("Pouzi predmet");
        predmetButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        predmetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HernyFrame.this.inventarDialog(true);
                if (((SubojPlochaPanel)HernyFrame.this.subojHPanel).vyhodnotSuboj()) {
                    ((CardLayout)HernyFrame.this.spodnyPanel.getLayout()).show(HernyFrame.this.spodnyPanel, "1");
                    ((CardLayout)HernyFrame.this.vrchnyPanel.getLayout()).show(HernyFrame.this.vrchnyPanel, "0");
                    HernyFrame.this.hernyPanel.transferFocus();
                }
                HernyFrame.this.subojHPanel.repaint();
            }
        });
        panel1.add(predmetButton);

        JButton utecButton = new JButton("Utec");
        utecButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        utecButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((SubojPlochaPanel)HernyFrame.this.subojHPanel).setUtiekol();
                ((SubojPlochaPanel)HernyFrame.this.subojHPanel).vyhodnotSuboj();
                ((CardLayout)HernyFrame.this.spodnyPanel.getLayout()).show(HernyFrame.this.spodnyPanel, "1");
                ((CardLayout)HernyFrame.this.vrchnyPanel.getLayout()).show(HernyFrame.this.vrchnyPanel, "0");
                HernyFrame.this.hernyPanel.transferFocus();
            }
        });
        panel1.add(utecButton);

        this.spodnyPanel.add(generalPanel, "1");
        this.spodnyPanel.add(subojDPanel, "4");
        ((CardLayout)this.spodnyPanel.getLayout()).show(this.spodnyPanel, "1");

    }

    /**
     * Nastavi subojovi panel
     * @param subojHPanel subojovy panel
     */
    public void setSubojHPanel(SubojPlochaPanel subojHPanel) {
        ((CardLayout)this.vrchnyPanel.getLayout()).removeLayoutComponent(this.subojHPanel);
        this.subojHPanel = subojHPanel;
        this.vrchnyPanel.add(this.subojHPanel, "1");
        this.subojHPanel.revalidate();
        this.subojHPanel.repaint();
        ((CardLayout)this.vrchnyPanel.getLayout()).show(this.vrchnyPanel, "1");
        ((CardLayout)this.spodnyPanel.getLayout()).show(this.spodnyPanel, "4");
    }

    /**
     * Nastavi panel hry
     * @param hra JPanel hry
     */
    public void setHra(JPanel hra) {
        this.hra = hra;
        ((CardLayout)this.vrchnyPanel.getLayout()).removeLayoutComponent(this.hra);
        this.vrchnyPanel.add(this.hra, "0");
        this.hra.revalidate();
        this.hra.repaint();
        ((CardLayout)this.vrchnyPanel.getLayout()).show(this.vrchnyPanel, "0");

    }

    private int ziskajInput(String text) {
        String input = JOptionPane.showInputDialog(null, text);
        if (input != null && !input.isEmpty()) {
            return Integer.parseInt(input);
        } else {
            return -1;
        }

    }

    private void inventarDialog(boolean suboj) {
        String s = "[INDEX]NAZOV(POCET)\n";
        s += this.zoznam("Predmet");
        String[] moznosti;
        if (!suboj) {
            moznosti = new String[]{"Pouzi predmet", "Info o predmete", "Odstran predmet"};
        } else {
            moznosti = new String[]{"Pouzi predmet", "Info o predmete"};
        }
        int vyber = JOptionPane.showOptionDialog(null, s, "Inventar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, moznosti, null);
        try {
            if (vyber == JOptionPane.CLOSED_OPTION) {
                HernyFrame.this.hernyPanel.transferFocus();
                return;
            } else if (vyber == 0) {
                this.pouziPredmet(suboj);
            } else if (vyber == 1) {
                this.info("Predmet");
            } else if (vyber == 2) {
                this.odstran("Predmet");
            }
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Neplatny index");
        }
        if (!suboj) {
            this.inventarDialog(suboj);
        }
        HernyFrame.this.hernyPanel.transferFocus();
    }


    private String zoznam(String polozka) {
        String s = "";
        if (polozka.equals("Pokemon")) {
            for (int i = 0; i < this.hrac.getVelkostPokemonInventara(); i++) {
                s += "[" + (i + 1) + "]" + this.hrac.dajPokemona(i).getMeno() + "\n";
            }
        } else if (polozka.equals("Predmet")) {
            for (int i = 0; i < this.hrac.getVelkostPredmetInventara(); i++) {
                s += "[" + (i + 1) + "]" + this.hrac.dajPredmet(i).toString();
            }
        }
        return s;
    }

    private void pokemonDialog() {
        String s = "[Index]MENO\n";
        s += this.zoznam("Pokemon");
        String [] moznosti = new String[]{"Prehodit pokemonov", "Daj info o pokemonovy", "Odstran pokemona"};
        int vyber = JOptionPane.showOptionDialog(null, s, "Pokemoni", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, moznosti, null);
        try {
            if (vyber == JOptionPane.CLOSED_OPTION) {
                HernyFrame.this.hernyPanel.transferFocus();
                return;
            } else if (vyber == 0) {
                this.prehodPokemonov();
            } else if (vyber == 1) {
                this.info("Pokemon");
            } else if (vyber == 2) {
                this.odstran("Pokemon");
            }
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Neplatny index");

        }
        this.pokemonDialog();
        HernyFrame.this.hernyPanel.transferFocus();
    }

    private void pouziPredmet(boolean suboj) throws IndexOutOfBoundsException {
        int index = this.ziskajInput("Zadaj index na ktorom sa predmet nachadza v inventari\n" + this.zoznam("Predmet"));
        if (index != -1) {
            if (!suboj) {
                if (this.hrac.dajPredmet(index - 1) instanceof Udica) {
                    if (this.hrac.dajPredmet(index - 1).pouzi(this.mapa.getPolicko(this.hrac.riadokSmer(), this.hrac.stlpecSmer())) == 1) {
                        SubojPlochaPanel sub = new SubojPlochaPanel(this.mapa.vygeneruj(this.mapa.getPolicko(this.hrac.riadokSmer(), this.hrac.stlpecSmer())), this.hrac.dajPokemona(0));
                        this.setSubojHPanel(sub);
                    }
                } else {
                    int indexPokemona = this.ziskajInput("Vyber si na akeho pokemona predmet pouzijes\n" + this.zoznam("Pokemon"));
                    if (indexPokemona != -1) {
                        this.hrac.pouziPredmet(index - 1, this.hrac.dajPokemona(indexPokemona - 1));
                    } else {
                        JOptionPane.showMessageDialog(null, "Nevybral si pokemona");
                    }
                }
            } else {
                String[] moznostiNa = {"Svojho", "Nepriatela"};
                String moznost = moznostiNa[JOptionPane.showOptionDialog(null, "Vyber si na akeho pokemona predmet pouzijes", null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, moznostiNa, null)];
                if (moznost.equals("Svojho") || moznost.equals("Nepriatela")) {
                    ((SubojPlochaPanel)HernyFrame.this.subojHPanel).predmet(HernyFrame.this.hrac, index - 1, moznost);
                } else {
                    this.inventarDialog(true);
                }
            }
        }

    }

    private void info(String polozka) throws IndexOutOfBoundsException {
        int index = this.ziskajInput("Zadaj index na ktorom sa " + polozka + " nachadza\n" + this.zoznam(polozka));
        if (polozka.equals("Pokemon")) {
            if (index >= 1 && index <= this.hrac.getVelkostPokemonInventara()) {
                JOptionPane.showMessageDialog(null, this.hrac.dajPokemona(index - 1).dlhyPopis());
            } else {
                throw new IndexOutOfBoundsException();
            }
        } else if (polozka.equals("Predmet")) {
            if (index >= 1 && index <= this.hrac.getVelkostPredmetInventara()) {
                JOptionPane.showMessageDialog(null, this.hrac.dajPredmet(index - 1).dajPopis());
            } else {
                throw new IndexOutOfBoundsException();
            }
        }
    }

    private void odstran(String polozka) throws IndexOutOfBoundsException {
        int index = this.ziskajInput("Zadaj index na ktorom sa " + polozka + " nachadza\n" + this.zoznam(polozka));
        if (polozka.equals("Pokemon")) {
            if (index >= 1 && index <= this.hrac.getVelkostPokemonInventara()) {
                this.hrac.odstranPokemona(index - 1);
            } else {
                throw new IndexOutOfBoundsException();
            }
        } else if (polozka.equals("Predmet")) {
            if (index >= 1 && index <= this.hrac.getVelkostPredmetInventara()) {
                this.hrac.odstranPredmet(index - 1);
            } else {
                throw new IndexOutOfBoundsException();
            }
        }
    }

    private void prehodPokemonov() throws IndexOutOfBoundsException {
        int indexPrvy = this.ziskajInput("Zadaj index na ktorom sa nachadza 1.pokemon\n" + this.zoznam("Pokemon"));
        if (indexPrvy != -1) {
            int indexDruhy = this.ziskajInput("Zadaj index na ktorom sa nachadza 2.pokemon\n" + this.zoznam("Pokemon"));
            if (indexDruhy != -1) {
                this.hrac.prehodPokemonov(indexPrvy - 1, indexDruhy - 1);
            }
        }
    }


}



