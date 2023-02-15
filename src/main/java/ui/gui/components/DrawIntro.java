package ui.gui.components;

import ui.endgame.UIIntroBlurb;
import ui.gui.TitlePanel;

import javax.swing.*;
import java.awt.*;

public class DrawIntro {
    public static JPanel drawIntro() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(TitlePanel.SCREEN_WIDTH,TitlePanel.SCREEN_HEIGHT));
        panel.add(paragraphText());
        panel.setVisible(true);
        return panel;
    }

    private static JTextArea paragraphText() {
        JTextArea intro = new JTextArea();
        intro.setText(UIIntroBlurb.paragraph1 + UIIntroBlurb.paragraph2 + UIIntroBlurb.paragraph3 + UIIntroBlurb.paragraph4);
        intro.setEditable(false);
        intro.setVisible(true);
        return intro;
    }
}
