package ui.gui.components.panels;

import gamecontrol.GlobalVariables;
import gamemodel.mapengine.SubArea;
import ui.gui.ConstructHTMLString;
import ui.gui.components.dialogs.PasswordDialog;
import ui.gui.components.labels.SubareaContentLabel;
import ui.gui.components.labels.SubareaTitleLabel;
import ui.gui.components.textareas.SubareaTextArea;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static gamecontrol.GlobalVariables.DESTINATION;

public class SubareaContentPanel extends JPanel {

    public SubareaContentPanel(SubareaPanel parentPanel, Dimension dimension) {
        setPreferredSize(dimension);
        setBorder(new LineBorder(Color.GREEN));
        setLayout(new GridLayout(3,1));
        appendSubcomponents(parentPanel,this);
    }
    private Integer appendSubcomponents(SubareaPanel parentPanel, SubareaContentPanel thisPanel){
        JTextArea subareaDescriptionTA;
        String output
                = (parentPanel.getSubArea().getName().equals(DESTINATION))
                ? handleSchradersLab( parentPanel.getSubArea())
                : parentPanel.getSubArea().getDescription();

        subareaDescriptionTA = new SubareaTextArea(output);
        thisPanel.add(new SubareaTitleLabel(parentPanel.getSubArea().getName()));
        thisPanel.add(new SubareaContentLabel(parentPanel));
        thisPanel.add(subareaDescriptionTA);

        if(!ConstructHTMLString.HAS_PASSWORD && parentPanel.getSubArea().getName().equals(DESTINATION)){
            JButton passwordBtn = new JButton("Enter Password");
            passwordBtn.addActionListener(e -> handleInputPassword(parentPanel, subareaDescriptionTA, passwordBtn) );
            parentPanel.add(passwordBtn);
        }
        return 1;
    }

    private String handleSchradersLab(SubArea subArea){
        StringBuilder output = new StringBuilder();
        if( subArea.getName().equals(DESTINATION) && GlobalVariables.firstVisitToLab ){
            output.append("Upon finally reaching the lab, you start making your way toward its Entry Control Point.\n"
                    + "Inside, there is a massive blast proof door with a panel to the right prompting for a password.\n"
                    + "\"Of course there was no mention of this in the mission briefing notes...\" you think to yourself.\n"
                    + "Looking around you see the Entry Control officer's reception desk across the room. Behind the desk\n"
                    + "you find a hastily, bloodstained note that reads: \"For entry, find the lead scientist..\"");
            GlobalVariables.firstVisitToLab = !GlobalVariables.firstVisitToLab;
            output.append("\nAccess to this Lab is password protected. Enter the password ");
        }
        if( ConstructHTMLString.HAS_PASSWORD ) output.append(subArea.getDescription());

        output.append("Access to this Lab is password protected. Enter the password ");
        return output.toString();
    }

    private Boolean handleInputPassword(SubareaPanel subareaPanel, JTextArea textAreaComponent, JButton pwButton){

        new PasswordDialog(subareaPanel,textAreaComponent,pwButton);

        return ConstructHTMLString.HAS_PASSWORD;
    }

}