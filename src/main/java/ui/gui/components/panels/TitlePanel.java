package ui.gui.components.panels;

import gamemusic.AudioPlayer;
import ui.gui.components.LoadImage;
import ui.gui.components.buttons.ExitButton;
import ui.gui.components.buttons.SettingsButton;
import ui.gui.components.buttons.StartButton;
import ui.gui.components.labels.BackgroundImageLabel;
import ui.gui.components.labels.ZombieLabel;
import ui.gui.components.textareas.TitleText;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class TitlePanel extends JPanel {

    public final static int SCREEN_WIDTH = 1024;
    public final static int SCREEN_HEIGHT = 768;
    private static Timer titleStrobe;

    private static JFrame jFrame;

    public TitlePanel(JFrame jFrame) {
        this.jFrame = jFrame;
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setLayout(null);
        this.setBackground(Color.CYAN);
        this.setDoubleBuffered(true);
        this.add(addTitleText());
        this.add(addTitleShadow());
        this.add(addStartButton());
        this.add(addExitButton());
        this.add(addSettingsButton());
        for (int i = 0; i < 2500;i++) {
            this.add(wanderingZombie());
        }
        this.add(background());
        if (AudioPlayer.isSoundOn()) {
            AudioPlayer.setVolume(-5.0f);
            AudioPlayer.getInstance().playAudio("sound/titlemusic.wav");
        }
    }

    private JLabel background() {
        BackgroundImageLabel label = new BackgroundImageLabel("images/overhead.jpg",SCREEN_WIDTH,SCREEN_HEIGHT);
        label.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        return label;
    }

    private JButton addSettingsButton() {
        SettingsButton setting = new SettingsButton();
        setting.setBounds(5, TitlePanel.SCREEN_HEIGHT-55,100,50);
        setting.setBackground(Color.ORANGE);
        setting.setOpaque(true);
        setting.setBorderPainted(false);
        return setting;
    }

    private JButton addExitButton() {
        JButton exitBtn = new ExitButton();
        exitBtn.setBounds(SCREEN_WIDTH / 2 + 100, SCREEN_HEIGHT/2+150, 115, 50);
        exitBtn.setBackground(Color.RED);
        exitBtn.setOpaque(true);
        exitBtn.setBorderPainted(false);
        return exitBtn;
    }

    private JButton addStartButton() {
        JButton startBtn = new StartButton();
        startBtn.setBounds(SCREEN_WIDTH / 2 - 200, SCREEN_HEIGHT/2+150, 115, 50);
        startBtn.setBackground(Color.GREEN);
        startBtn.setOpaque(true);
        startBtn.setBorderPainted(false);
        return startBtn;
    }


    private JTextArea addTitleText() {
        Random rg = new Random();
        TitleText title = new TitleText((SCREEN_WIDTH - 580) / 2, SCREEN_HEIGHT/2-150, 700, 200);
        title.setForeground(new Color(rg.nextInt(256),rg.nextInt(256),rg.nextInt(256)));
        titleStrobe = new Timer(100, e -> {
            title.setForeground(new Color(rg.nextInt(256), rg.nextInt(256), rg.nextInt(256)));
        });

        titleStrobe.setRepeats(true);
        titleStrobe.setCoalesce(true);
        titleStrobe.start();
        return title;
    }

    private JTextArea addTitleShadow() {
        TitleText shadow = new TitleText((SCREEN_WIDTH - 577) / 2, SCREEN_HEIGHT/2-147, 580, 150);
        shadow.setForeground(Color.BLACK);
        return shadow;
    }

    private ZombieLabel wanderingZombie() {
        ZombieLabel zombie = new ZombieLabel(jFrame);
        return zombie;
    }

    public static Timer getTitleStrobe() {
        return titleStrobe;
    }

    public static JFrame getjFrame() {
        return jFrame;
    }

    @Deprecated
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Strain X");

        TitlePanel title = new TitlePanel(window);
        window.add(title);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
