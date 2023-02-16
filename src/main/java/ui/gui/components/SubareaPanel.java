package ui.gui.components;

import gamemodel.mapengine.SubArea;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SubareaPanel extends JPanel {

    public SubareaPanel(Integer keyNumber, List<SubArea> subareasList){
        setBorder(new LineBorder(Color.RED));
        createSubareaPanels(this,subareasList, keyNumber);
        setLayout(new BoxLayout( this,BoxLayout.Y_AXIS));
    }

    private Integer createSubareaPanels(JPanel area,List<SubArea> subAreaList,Integer areaNumber){

        area.add(new JLabel(String.format("Area %d",areaNumber)));

        for (SubArea subArea : subAreaList) {

            // TEMP JButton Component to represent the pop-up Subarea on-click
            JButton building = new JButton();
            building.setAlignmentY(Component.CENTER_ALIGNMENT);
            building.setAlignmentX(Component.CENTER_ALIGNMENT);
//            building.setPreferredSize(new Dimension(300,200));
            building.add(new JLabel(subArea.getName()));
            building.setName(subArea.getName()); // give the btn a name
            building.addActionListener(btnListener(building));

            area.add(building);
        }

        return 1;
    }
    private ActionListener btnListener(JButton building){
        return e -> System.out.println("Clicked: "+ building.getName());
    }
}