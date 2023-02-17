package ui.gui.components.panels;

import gamemodel.mapengine.SubArea;
import ui.gui.ConstructHTMLString;
import ui.gui.components.buttons.SubareaButton;
import ui.maps.UIEnterMainMap;

import javax.accessibility.AccessibleContext;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
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
        add(goToLootButton(this));
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

        // TODO - TEMP - create Class for this container/subcomponents
        JPanel container = new JPanel();
        container.setPreferredSize(new Dimension(w,h));
        container.setBorder(new LineBorder(Color.BLUE));
        container.setLayout(new GridLayout(3,1));

        // Container sub components
        JLabel subareaTitleLabel = new JLabel(thisPanel.getSubArea().getName());
        subareaTitleLabel.setFont(getFont().deriveFont(Font.BOLD,30));
        subareaTitleLabel.setBorder(new LineBorder(Color.PINK));
        subareaTitleLabel.setForeground(Color.BLUE);

        JLabel subareaContentLabel = new JLabel(ConstructHTMLString.parseThreatLevelHTMLString(thisPanel.getSubArea()));
        subareaContentLabel.setBorder(new LineBorder(Color.PINK));

        JTextArea subareaDescriptionTextArea = new JTextArea(thisPanel.getSubArea().getDescription());
        subareaDescriptionTextArea.setBorder(new LineBorder(Color.PINK));

        // Add Container subPanels
        container.add(subareaTitleLabel);
        container.add(subareaContentLabel);
        container.add(subareaDescriptionTextArea);

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
    private JButton goToLootButton(JPanel subareaPanel){
        JButton btn = new JButton("Loot");
        btn.addActionListener(handleGoToLoot());
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
    private ActionListener handleGoToLoot(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("LOOTING");
            }
        };
    }

    public SubArea getSubArea() { return subArea; }
}