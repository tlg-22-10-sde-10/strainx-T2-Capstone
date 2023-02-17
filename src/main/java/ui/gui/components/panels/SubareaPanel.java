package ui.gui.components.panels;

import gamemodel.mapengine.SubArea;
import ui.gui.components.buttons.SubareaButton;
import ui.maps.UIEnterMainMap;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Arrays;

public class SubareaPanel extends JPanel {
    public SubArea subArea;

    public SubareaPanel(SubArea subArea){
        this.subArea = subArea;
        setBorder(new LineBorder(Color.RED));
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(0,0));
        setVisible(false);
        add(goToMapButton(this));
        add(goToCombatButton(this));
        addComponentListener( onSubareaExpand(this) );
    }

    private ComponentAdapter onSubareaExpand(SubareaPanel thisPanel){
        // component will only grow never shrinks
        return new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                System.out.println("COMPONENT EXPANDED");
                addContainerPanel(thisPanel);
            }
        };
    }

    private Integer addContainerPanel(SubareaPanel thisPanel){

        // TODO set this containers size relative to main map
        // TODO add background image/wallpaper
        int w = thisPanel.getWidth();
        int h = (int) (thisPanel.getHeight()*.90);

        JPanel container = new JPanel();
        container.setPreferredSize(new Dimension(w,h));
        container.setBorder(new LineBorder(Color.BLUE));
        // Container subPanels
        container.add(new JLabel(thisPanel.getSubArea().getName()));
        container.add(new JTextArea(thisPanel.getSubArea().getDescription()));
        container.add(new JTextArea(String.valueOf(thisPanel.getSubArea().getContents().items.size())));
        container.add(new JTextArea( UIEnterMainMap.displayThreatLvl(subArea)));

        //
        thisPanel.add( container );
        thisPanel.revalidate();
        thisPanel.repaint();
        return 1;
    }
    private JButton goToMapButton(JPanel subareaPanel){
        JButton btn = new JButton("Map");
        btn.addActionListener( handleReturnToMap(subareaPanel) );
        return btn;
    }
    // Temporary Methods until SubArea is fully designed
    private JButton goToCombatButton(JPanel subareaPanel) {
        JButton btn = new JButton("Enter Combat");
        btn.addActionListener(handleGoToCombat(subareaPanel));
        if(subArea.getContents().enemies.isEmpty()) {
            btn.setEnabled(false);
        }
        return btn;
    }
    private ActionListener handleGoToCombat(JPanel subareaPanel) {
        return e -> {
            JFrame ancestor = (JFrame) subareaPanel.getTopLevelAncestor();
            ancestor.getContentPane().removeAll();
            ancestor.add(new CombatPanel(this));
            ancestor.repaint();
            ancestor.pack();
        };
    }
    private ActionListener handleReturnToMap(JPanel subareaPanel){
        return e -> {
            JFrame ancestor = (JFrame) subareaPanel.getTopLevelAncestor();
            ancestor.getContentPane().removeAll();
            ancestor.add(new WrapperPanel());
            ancestor.repaint();
            ancestor.pack();
        };
    }


    public SubArea getSubArea() { return subArea; }
}