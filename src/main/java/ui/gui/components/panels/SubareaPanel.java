package ui.gui.components.panels;

import gamecontrol.GlobalVariables;
import gamecontrol.contents.Item;
import gamemodel.mapengine.Content;
import gamemodel.mapengine.SubArea;
import ui.gui.ConstructHTMLString;
import ui.gui.components.buttons.SettingsButton;
import ui.inventory.UIInventory;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class SubareaPanel extends JPanel {
    public SubArea subArea;
    private JButton combatButton;
    private JButton lootButton;

    public SubareaPanel(SubArea subArea){
        this.subArea = subArea;
        setBorder(new LineBorder(Color.RED));
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(0,0));
        setVisible(false);
        add(goToMapButton(this));
        add(goToLootButton(this));
        add(goToCombatButton(this));
        add(new SettingsButton());
        addComponentListener( onSubareaExpand(this) );
    }

    private ComponentAdapter onSubareaExpand(SubareaPanel thisPanel){
        return new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                addSubareaContentAreaPanel(thisPanel);
            }
        };
    }
    private Integer addSubareaContentAreaPanel(SubareaPanel thisPanel){
        // TODO set this containers size relative to main map
        // TODO add background image/wallpaper

        int w = thisPanel.getWidth();
        int h = (int) (thisPanel.getHeight()*.90);
        SubareaContentPanel container = new SubareaContentPanel(thisPanel, new Dimension(w,h));
        thisPanel.add( container );
        thisPanel.revalidate();
        thisPanel.repaint();
        return 1;
    }

    // Temporary Methods until SubArea is fully designed
    private JButton goToMapButton(JPanel subareaPanel){
        JButton btn = new JButton("Map");
        btn.addActionListener( handleReturnToMap(subareaPanel) );
        return btn;
    }
    private JButton goToCombatButton(JPanel subareaPanel) {
        combatButton = new JButton("Enter Combat");
        combatButton.addActionListener(handleGoToCombat(subareaPanel));
        combatButton.setEnabled(true);
        combatButton.setVisible(true);
        subareaPanel.add(combatButton);
        
        if(subArea.getContents().enemies.isEmpty() ||
                (subArea.getName().equals(GlobalVariables.DESTINATION) && !ConstructHTMLString.HAS_PASSWORD)) {
            combatButton.setEnabled(false);
        }
        return combatButton;
    }
    private JButton goToLootButton(JPanel subareaPanel){
        lootButton = new JButton("Loot");
        lootButton.addActionListener(handleLoot());
        if(!getSubArea().getContents().enemies.isEmpty()) lootButton.setEnabled(false); //disable when enemies present
        return lootButton;
    }

    private ActionListener handleGoToCombat(JPanel subareaPanel) {
        return e -> {
            GlobalVariables.enemySquad = getSubArea().getContents().enemies;
            JFrame ancestor = (JFrame) subareaPanel.getTopLevelAncestor();
            ancestor.getContentPane().removeAll();
            CombatPanel combatPanel = new CombatPanel(this);
            ancestor.add(combatPanel);
            ancestor.repaint();
            ancestor.pack();
            combatPanel.getInitiativeInfo().setText(combatPanel.combat.initiativeResultString());
            combatPanel.getInitiativeInfo().repaint();
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
    private ActionListener handleLoot(){
        // TODO auto-loot items after win in combat
        // TODO refresh contents subpanel
        return e -> {loot();};
    }
    public Integer loot(){
        StringBuilder outputMessage = new StringBuilder();
        if(getSubArea().getContents().enemies.isEmpty()) {
            outputMessage.append(addItemsToInventory(getSubArea().getContents()));
        }
        JOptionPane.showMessageDialog(this,outputMessage);
        return 1;
    }
    private String addItemsToInventory(Content content) {
        StringBuilder outputMessage = new StringBuilder();
        if (content.items.size() > 0) {
            for (Item i : content.items) {
                UIInventory.pickUpItem(i);
                outputMessage.append(i.getName()).append("\n");
            }
            content.items.clear();
        }
        else outputMessage.append("Rummaging around you find there's nothing of value.");
        return outputMessage.toString();
    }

    public SubArea getSubArea() { return subArea; }
    public JButton getCombatButton() { return combatButton; }
    public JButton getLootButton() { return lootButton; }
}