package ui.gui.components;

import javax.swing.*;
import java.awt.*;

public class HelpMapDialog extends JDialog {

    private final JFrame frame;
    public HelpMapDialog(JFrame frame) {
        super(frame);
        this.frame = frame;
        setLayout(new BorderLayout());
        setBounds(200,200,300,100);
        setResizable(false);
        setLocationRelativeTo(frame);
        add(label(),BorderLayout.CENTER);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private JPanel label(){
        JPanel panel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("help 1\nhelp 2\nhelp 3");
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        panel.add(label);
        return panel;
    }

}
