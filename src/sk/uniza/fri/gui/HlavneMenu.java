package sk.uniza.fri.gui;


import sk.uniza.fri.Hra;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HlavneMenu {

    private JFrame panel;


    /**
     * Vytvori hlavne menu
     */
    public HlavneMenu() {
        this.inicializuj();
    }

    private void inicializuj() {
        this.panel = new JFrame();
        this.panel.setResizable(false);
        this.panel.setForeground(Color.BLACK);
        this.panel.setTitle("Pokemon");
        this.panel.setBounds(100, 100, 800, 800);
        this.panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.panel.setLocationRelativeTo(null);
        this.panel.getContentPane().setLayout(null);


        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Hra();
                HlavneMenu.this.panel.dispose();
            }
        });
        startButton.setBounds(300, 400, 170, 60);
        this.panel.getContentPane().add(startButton);


        JButton ukoncitButton = new JButton("Ukoncit");
        ukoncitButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        ukoncitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HlavneMenu.this.panel.dispose();
            }
        });
        ukoncitButton.setBounds(300, 500, 170, 60);
        this.panel.getContentPane().add(ukoncitButton);

        JLabel logoLabel = new JLabel("");
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setIcon(new ImageIcon("src/sk/uniza/fri/obrazky/logo.png"));
        logoLabel.setBounds(10, 11, 764, 368);
        this.panel.getContentPane().add(logoLabel);
        this.panel.setVisible(true);
    }

}
