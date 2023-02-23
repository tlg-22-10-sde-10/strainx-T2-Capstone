package ui.gui.components.labels;
import ui.gui.components.LoadImage;

import javax.swing.*;
import java.awt.*;

public class BackgroundImageLabel extends JLabel {
    private final int width;
    private final int height;

    public BackgroundImageLabel(String fileName, int width, int height) {
        this.width = width;
        this.height = height;
        try {
            Image image = LoadImage.getImage(fileName).getScaledInstance(width,height,Image.SCALE_DEFAULT);
            setIcon(new ImageIcon(image));
        } catch (Exception e) {
            setIcon(null);
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(width,height);
    }
}
