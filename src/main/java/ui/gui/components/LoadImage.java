package ui.gui.components;

import client.StrainXMain;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class LoadImage {

    public static ImageIcon getIcon(String fileName) {
        ImageIcon icon;
        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(StrainXMain.class
                    .getClassLoader().getResourceAsStream(fileName)));
            icon = new ImageIcon(image);

            return icon;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static Image getImage(String fileName) {
        try {
            return ImageIO.read(Objects.requireNonNull(StrainXMain.class.getClassLoader().getResourceAsStream(fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
