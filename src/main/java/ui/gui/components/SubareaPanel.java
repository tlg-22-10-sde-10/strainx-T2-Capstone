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

        area.add(new JLabel(String.format("%d",areaNumber)));
        for (SubArea subArea : subAreaList) {
            JButton building = new JButton();
            building.setAlignmentY(Component.CENTER_ALIGNMENT);
            building.setAlignmentX(Component.CENTER_ALIGNMENT);
            building.setSize(200,100);
            building.add(new JLabel(subArea.getName()));
            building.setName(subArea.getName());
            building.addActionListener(btnListener(building));
            area.add(building);
        }

        return 1;
    }
    private ActionListener btnListener(JButton building){
        return e -> System.out.println("Clicked: "+ building.getName());
    }
}