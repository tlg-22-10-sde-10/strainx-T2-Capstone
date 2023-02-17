package ui.gui.components.labels;

import client.StrainXMain;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class BackgroundImageLabel extends JLabel {
    private final int width;
    private final int height;

    public BackgroundImageLabel(String fileName, int width, int height) {
        this.width = width;
        this.height = height;
        try {
            URL imgURL = StrainXMain.class.getClassLoader().getResource(fileName);
            BufferedImage image = ImageIO.read(imgURL);
            Image img = image.getScaledInstance(width,height,Image.SCALE_DEFAULT);
            JLabel temp = new JLabel(new ImageIcon(img));
            this.setIcon(temp.getIcon());
        } catch (Exception e) {
            this.setIcon(null);
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(width,height);
    }
}
