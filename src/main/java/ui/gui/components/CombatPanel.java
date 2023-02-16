package ui.gui.components;

import gamecontrol.GlobalVariables;
import gamemodel.combatengine.EngageEnemy;
import gamemodel.mapengine.SubArea;
import ui.gui.TitlePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CombatPanel extends JPanel {

    SubArea subArea;
    SubareaPanel subareaPanel;
    public CombatPanel(SubareaPanel subareaPanel) {
        super(new BorderLayout());
        this.subareaPanel=subareaPanel;
        this.subArea = subareaPanel.getSubArea();

        setBackground(Color.blue);
        setPreferredSize(new Dimension(TitlePanel.SCREEN_WIDTH,TitlePanel.SCREEN_HEIGHT));
        //setVisible(false);
        JPanel filler = new JPanel();
        filler.setBackground(Color.LIGHT_GRAY);
        filler.add(new StatusPanel(GlobalVariables.mySquad),BorderLayout.NORTH);
        filler.add(new EnemyStatusPanel(subArea.getContents().enemies),BorderLayout.CENTER);
        filler.addComponentListener(adjustSubPanelDimensions(filler,.40,.60));
        add(filler,BorderLayout.NORTH);

        add(buttonPanel());
        addComponentListener(adjustSubPanelDimensions(this,.25,.20));
        setVisible(true);
    }

    private ComponentAdapter adjustSubPanelDimensions(JPanel parent, double modifier1, double modifier2) {
        return new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int thisHeight = parent.getHeight();
                int thisWidth = parent.getWidth();
                for(Component c : parent.getComponents()) {
                    System.out.println(c);
                }
                parent.getComponent(0).setPreferredSize(new Dimension(thisWidth, (int) ((thisHeight)*modifier1)));
                parent.getComponent(1).setPreferredSize(new Dimension(thisWidth, (int) ((thisHeight)*modifier2)));
                parent.revalidate();
            }
        };
    }

    private JPanel buttonPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(retreatButton());
        panel.add(autoCombatButton());
        panel.add(targetEnemyAttackButton());
        panel.add(useItemsButton());
        panel.add(new SettingsButton());
        panel.setPreferredSize(new Dimension(TitlePanel.SCREEN_WIDTH,(int) (TitlePanel.SCREEN_HEIGHT*.20)));
        return panel;
    }

    private JButton autoCombatButton() {
        JButton btn = new JButton("Auto Combat");
        // TODO implement functionality
        btn.addActionListener(e -> {
            GlobalVariables.enemySquad = subArea.getContents().enemies;
            if(GlobalVariables.enemySquad.size() >0) {EngageEnemy.playerAutoCombat(-1);}

            // Remove and Rebuild entire scene
            JFrame ancestor = (JFrame) this.getTopLevelAncestor();
            ancestor.getContentPane().removeAll();
            ancestor.add(new CombatPanel(subareaPanel));
            ancestor.repaint();
            ancestor.pack();
        });
        return btn;
    }

    private JButton targetEnemyAttackButton() {
        JButton btn = new JButton("Attack Target");
        // TODO implement functionality
        return btn;
    }

    private JButton useItemsButton() {
        JButton btn = new JButton("Use Items");
        // TODO implement functionality
        return btn;
    }
    private JButton retreatButton() {
        JButton retreatBtn = new JButton("Retreat");
        retreatBtn.addActionListener(e ->{
            JFrame ancestor = (JFrame) getTopLevelAncestor();
            ancestor.getContentPane().removeAll();
            ancestor.add(new StatusPanel(GlobalVariables.mySquad),BorderLayout.NORTH);
            ancestor.add(subareaPanel);
            ancestor.setPreferredSize(new Dimension(1024,768));
            ancestor.repaint();
            ancestor.pack();
        });
        return retreatBtn;
    }
}
