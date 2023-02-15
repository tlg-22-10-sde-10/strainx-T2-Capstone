package ui.gui.components;

import ui.gui.TitlePanel;

import javax.swing.*;
import java.awt.*;

public class DrawIntro {

    public static String[] introText = new String[]{
            "You are part of a small strike team covertly inserted onto a military installation where\n",
            "a failed experiment has taken place. The experiment caused a zombie outbreak and it is\n",
            "thought the only way to contain and reverse the spread is to obtain a blood sample from\n",
            "\"Patient Zero.\" \n\n",
            "Your objective is to locate Patient Zero on the base and obtain the sample by any means\n",
            "necessary.  Naturally, insertion was totally botched and your team's equipment was lost.\n",
            "As such, you'll have to pick your fights carefully as you search the base for gear and your\n",
            "target, Patient Zero.\n\n",
            "Patient Zero now leads a horde of zombies, his once human form twisted and corrupted by the \n",
            "failed experiment. You must face this formidable opponent and put an end to the zombie\n",
            "outbreak before it spreads beyond the confines of the installation. Can you overcome the\n",
            "odds and emerge victorious, or will you become just another mindless undead member of\n",
            "Patient Zero's horde?\n\n",
            "Your mission begins from Area 1, the base is arranged in a grid of numbered Areas containing\n",
            "enter-able subareas (buildings). You recall your commander telling you the famed\n",
            "\"Schrader Lab\" might be a good place to check for Patient Zero...\n\n",
            "If at any point you need help remembering game controls, press help. Good luck!"
    };
    public static JPanel drawIntro() {
        JPanel panel = new JPanel(null);
        panel.setPreferredSize(new Dimension(TitlePanel.SCREEN_WIDTH,TitlePanel.SCREEN_HEIGHT));
        panel.setBackground(Color.BLACK);

        panel.add(continueButton());
        panel.add(paragraphText());

        panel.setVisible(true);
        return panel;
    }

    private static JTextArea paragraphText() {
        JTextArea intro = new JTextArea();
        intro.setText(appendText());
        intro.setBackground(Color.BLACK);
        intro.setFont(new Font("Monospaced",Font.BOLD,16));
        intro.setBounds(50,50,924,500);
        intro.setForeground(Color.WHITE);
        intro.setEditable(false);
        intro.setVisible(true);
        return intro;
    }

    private static String appendText() {
        StringBuilder result = new StringBuilder();
        for(String s : introText) {
            result.append(s);
        }
        return result.toString();
    }

    private static JButton continueButton() {
        JButton button = new ContinueButton();
        button.setBounds(TitlePanel.SCREEN_WIDTH/2-60,700,120,30);
        return button;
    }
}
