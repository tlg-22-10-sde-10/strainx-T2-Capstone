package ui.gui.components;

import gamemodel.mapengine.SubArea;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class AreaPanel extends JPanel {

    public AreaPanel(Integer keyNumber, List<SubArea> subareasList){
        setBorder(new LineBorder(Color.GREEN.darker()));
        createSubareaPanels(this,subareasList, keyNumber);
        setLayout(new BoxLayout( this,BoxLayout.Y_AXIS));
    }

    private Integer createSubareaPanels(JPanel area,List<SubArea> subAreaList,Integer areaNumber){
        area.add(new JLabel(String.format("Area %d",areaNumber)));
        for (SubArea subArea : subAreaList) {

            SubareaPanel subareaPanel = new SubareaPanel(subArea);// pass subarea obj to subarea panel
            area.add(subareaPanel);// TEMP btn to toggle show/hide subarea panel
            JButton toggleSubarea = new SubareaButton(subArea,subareaPanel);// add toggleSubarea to subarea panel --> toggle show/hide panel
            subareaPanel.add(toggleSubarea);

            area.add(toggleSubarea);
            area.add(subareaPanel);
        }

        return 1;
    }

//    private ActionListener toggleSubareaPanel(JPanel subarea){
//        // test button click event
//        // toggle show/hide subarea panel
//        return e -> {
//            subarea.setVisible(!subarea.isVisible());
//            if(subarea.isVisible()){
//                JPanel tempParent = (JPanel) getParent().getParent();
//                tempParent.remove(1);
//                tempParent.add(subarea);
//                tempParent.revalidate();
//                tempParent.repaint();
//                subarea.setPreferredSize(new Dimension(tempParent.getWidth(),tempParent.getHeight()));
//            }
//            subarea.revalidate();
//            subarea.repaint();
//        };
//    }

}