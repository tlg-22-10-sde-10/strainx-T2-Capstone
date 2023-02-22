package ui.gui.components.labels;

import ui.gui.components.LoadImage;
import ui.gui.components.panels.TitlePanel;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ZombieLabel extends JLabel {

    private final int MIN_X_VALUE = 0;
    private final int MIN_Y_VALUE = 0;

    // TODO Change Max values to reflect the size of image.
    private final int MAX_X_VALUE = TitlePanel.SCREEN_WIDTH - 20;
    private final int MAX_Y_VALUE = TitlePanel.SCREEN_HEIGHT - 20;

    private int xPosition,yPosition;
    private int xMove,yMove;
    private final Random rg = new Random();

    public static Timer timer;

    public ZombieLabel() {
        super(LoadImage.getIcon("images/zombie.png"));
        setPreferredSize(new Dimension(20,20)); // TODO Same as above
        setBounds(MAX_X_VALUE,MAX_Y_VALUE,20,20);
        xPosition = rg.nextInt(MAX_X_VALUE);
        yPosition = rg.nextInt(MAX_Y_VALUE);
        xMove = 1;
        yMove = -1;
        addTimer();
        setVisible(true);
    }

    private void addTimer() {
        timer = new Timer(5,e -> {
            try {
                Point mousePoint = MouseInfo.getPointerInfo().getLocation();
                int mouseX = (int)mousePoint.getX() - 10;
                int mouseY = (int)mousePoint.getY() - 75;
                if(xPosition >= mouseX) {
                    xMove = -1 * rg.nextInt(10);
                }
                if(xPosition <= mouseX) {
                    xMove = rg.nextInt(10);
                }
                if(yPosition >= mouseY) {
                    yMove = -1 * rg.nextInt(10);
                }
                if(yPosition <= mouseY) {
                    yMove = rg.nextInt(10);
                }
                xPosition = xPosition+xMove;
                yPosition = yPosition+yMove;
                if(xPosition < MIN_X_VALUE) xPosition = MIN_X_VALUE;
                if(xPosition > MAX_X_VALUE) xPosition = MAX_X_VALUE;
                if(yPosition < MIN_Y_VALUE) yPosition = MIN_Y_VALUE;
                if(yPosition > MAX_Y_VALUE) yPosition = MAX_Y_VALUE;
                setBounds(xPosition,yPosition,20,20);
            } catch (NullPointerException ex) {
            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();
    }
}
