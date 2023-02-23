package ui.gui.components.textareas;

import ui.endgame.UITitlePage;

import javax.swing.*;
import java.awt.*;

public class TitleText extends JTextArea {

    public TitleText(){
        this.setText(UITitlePage.displayTitle());
        this.setFont(new Font("Courier New", Font.PLAIN, 12));
        this.setPreferredSize(new Dimension(700, 150));
        this.setOpaque(false);
        this.setEditable(false);
    }

    public TitleText(int x, int y, int width, int height) {
        this();
        this.setBounds(x,y,width,height);
    }
}
