package ui.gui.components.labels;

import ui.gui.components.LoadImage;
import ui.gui.components.panels.TitlePanel;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ZombieLabel extends JLabel {
    private static Timer timer;

    private final int MIN_X_VALUE = 0;
    private final int MIN_Y_VALUE = 0;
    private final int MAX_X_VALUE = TitlePanel.SCREEN_WIDTH - 20;
    private final int MAX_Y_VALUE = TitlePanel.SCREEN_HEIGHT - 20;
    private final Random rg = new Random();
    private final JFrame jFrame;

    private int xPosition;
    private int yPosition;
    private int xMove;
    private int yMove;
    private int previousX;
    private int previousY;
    private int previousXMove;
    private int previousYMove;

    public ZombieLabel(JFrame jFrame) {
        super(LoadImage.getIcon("images/zombie.png"));
        previousX = -1;
        previousY = -1;
        this.jFrame = jFrame;
        setPreferredSize(new Dimension(20, 20)); // TODO Same as above
        setBounds(MAX_X_VALUE, MAX_Y_VALUE, 20, 20);
        xPosition = rg.nextInt(MAX_X_VALUE);
        yPosition = rg.nextInt(MAX_Y_VALUE);
        xMove = 1;
        yMove = -1;
        previousXMove = 1;
        previousYMove = -1;
        addTimer();
        setVisible(true);
    }

    private void addTimer() {
        timer = new Timer(17, e -> {
            try {
                Point windowPosition = jFrame.getLocation();
                int windowX = (int) windowPosition.getX();
                int windowY = (int) windowPosition.getY();
                Point mousePoint = MouseInfo.getPointerInfo().getLocation();
                int mouseX = (int) mousePoint.getX() - windowX - 5;
                int mouseY = (int) mousePoint.getY() - windowY - 20;
                if (mouseX != previousX && mouseY != previousY) {
                    previousX = mouseX;
                    previousY = mouseY;
                    if (xPosition >= mouseX) {
                        xMove = -1 * (rg.nextInt(8)+ 1);
                    }
                    if (xPosition <= mouseX) {
                        xMove = (rg.nextInt(8)+ 1);
                    }
                    if (yPosition >= mouseY) {
                        yMove = -1 * (rg.nextInt(8)+ 1);
                    }
                    if (yPosition <= mouseY) {
                        yMove = (rg.nextInt(8)+ 1);
                    }
                    xPosition = xPosition + xMove;
                    yPosition = yPosition + yMove;
                } else {
                    setDirection();
                }
                if (xPosition < MIN_X_VALUE) xPosition = MIN_X_VALUE;
                if (xPosition > MAX_X_VALUE) xPosition = MAX_X_VALUE;
                if (yPosition < MIN_Y_VALUE) yPosition = MIN_Y_VALUE;
                if (yPosition > MAX_Y_VALUE) yPosition = MAX_Y_VALUE;
                setBounds(xPosition, yPosition, 20, 20);
            } catch (NullPointerException ignored) {
            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();
    }

    private void setDirection() {
        if(xMove == previousXMove) {
            xMove = (rg.nextInt(8)+ 1) * ((xMove < 0) ? -1 : 1);
        }
        if(yMove == previousYMove) {
            yMove = (rg.nextInt(8)+ 1) * ((yMove < 0) ? -1 : 1);
        }
        xPosition = xPosition + xMove;
        yPosition = yPosition + yMove;

        if (xPosition < MIN_X_VALUE || xPosition > MAX_X_VALUE) {
            xMove *= -1;
            previousXMove = xMove;
        }
        if (yPosition < MIN_Y_VALUE || yPosition > MAX_Y_VALUE) {
            yMove *= -1;
            previousYMove = yMove;
        }
    }

    public static Timer getTimer() {
        return timer;
    }
}
