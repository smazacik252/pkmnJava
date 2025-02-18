package sk.uniza.fri;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



public class Obrazok {

    private BufferedImage obrazok;

    /**
     * Vytvori obrazok
     * @param cesta -cesta k suboru obrazka
     */
    public Obrazok(String cesta) {
        try {
            this.obrazok = ImageIO.read(new File(cesta));
        } catch (IOException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Subor " + cesta + " sa nenasiel");
        }
    }


    /**
     * nakresli obrazok
     * @param g2 komponent na kreslenie
     * @param x x-ova suradnica obrazka;
     * @param y y-ova suradnica obrazka;
     * @param vyska -vyska obrazka
     * @param sirka - sirka obrazka
     */
    public void nakresli(Graphics2D g2, int x, int y, int vyska, int sirka) {
        g2.drawImage(this.obrazok, x, y , vyska, sirka, null);
    }




}
