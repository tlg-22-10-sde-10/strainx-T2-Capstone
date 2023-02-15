package client;

import ui.UIEnterGame;
import java.io.IOException;

import GUI.StatsPanel;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StrainXMain {

    public static void main(String[] args) throws IOException, InterruptedException {

        UIEnterGame.displayEnterGame();


        // TEST
        ArrayList<String> players = new ArrayList<>();
        players.add("SGT Player");
        players.add("SPC Roy");
        players.add("PFC Steph");
        players.add("CPL Jackson");
        JFrame jFrame = new JFrame();

        JPanel map = new JPanel();
        map.add(new JLabel("MAP"), BorderLayout.SOUTH);
        map.setBackground(Color.BLACK);
        map.setSize(800,500);
        map.setOpaque(true);

        jFrame.add(new StatsPanel( players ));
        jFrame.add(map);

        jFrame.setResizable(false);
        jFrame.setTitle("StrainX");
        jFrame.setPreferredSize(new Dimension(800,600));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}