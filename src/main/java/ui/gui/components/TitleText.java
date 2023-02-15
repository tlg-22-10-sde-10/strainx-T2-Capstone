package ui.gui.components;

import ui.endgame.UITitlePage;

import javax.swing.*;
import java.awt.*;

public class TitleText extends JTextArea {

    public TitleText(){
        this.setText(UITitlePage.displayTitle());
        this.setFont(new Font("Monospaced", Font.PLAIN, 12));
        this.setPreferredSize(new Dimension(600, 150));
        this.setOpaque(false);
        this.setEditable(false);
    }

    public TitleText(int x, int y, int width, int height) {
        this();
        this.setBounds(x,y,width,height);
    }
}
