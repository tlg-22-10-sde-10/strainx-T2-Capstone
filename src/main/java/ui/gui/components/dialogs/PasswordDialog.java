package ui.gui.components.dialogs;

import gamecontrol.GlobalVariables;
import ui.gui.ConstructHTMLString;
import ui.gui.components.panels.SubareaPanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PasswordDialog extends JDialog {

    SubareaPanel subareaPanel;
    JTextArea textAreaComponent;
    JButton pwButton;

    JPanel pwPanel;
    JButton submitButton;
    JPasswordField pwfield;

    public PasswordDialog(SubareaPanel subareaPanel,JTextArea textAreaComponent, JButton pwButton) {
        super((JFrame) subareaPanel.getTopLevelAncestor(),"Enter Password",true);
        this.subareaPanel = subareaPanel;
        this.textAreaComponent = textAreaComponent;
        this.pwButton = pwButton;
        setBounds(200,200,150,180);
        setLocationRelativeTo(subareaPanel.getTopLevelAncestor());
        createJPanel();
        getContentPane().add(pwPanel);
        pack();
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private Integer createJPanel() {
        createPWField();
        createSubmitButton();
        pwPanel = new JPanel(new GridLayout(0,2));
        pwPanel.add(new JLabel("Password:"));
        pwPanel.setBorder(new LineBorder(Color.PINK));
        pwPanel.add(pwfield);
        pwPanel.add(submitButton);
        return 1;
    }

    private Integer createPWField() {
        pwfield = new JPasswordField(10);

        return 1;
    }
    private Integer createSubmitButton() {
        submitButton = new JButton("Submit");

        submitButton.addActionListener( e -> {
            String given = GlobalVariables.getPassWord();
            String actual = new String(pwfield.getPassword());
            if(actual.equals(given)) {
                ConstructHTMLString.HAS_PASSWORD = !ConstructHTMLString.HAS_PASSWORD;
                textAreaComponent.setText("Password Accepted!!\n"+subareaPanel.getSubArea().getDescription() );
                subareaPanel.getCombatButton().setEnabled(true);
                subareaPanel.remove(pwButton);
                subareaPanel.revalidate();
                subareaPanel.repaint();
            }
            else textAreaComponent.setText("INCORRECT");
            dispose();
        });

        return 1;
    }
}
