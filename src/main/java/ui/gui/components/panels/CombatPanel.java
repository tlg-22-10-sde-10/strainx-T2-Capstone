package ui.gui.components.panels;

import gamecontrol.GlobalVariables;
import gamemodel.combatengine.EngageEnemy;
import gamemodel.combatengine.GUICombatEngine;
import ui.gui.components.buttons.SettingsButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class CombatPanel extends JPanel {

    private JPanel filler;
    SubareaPanel subareaPanel;
    GUICombatEngine combat;
    private JTextArea roundInfo;
    private JTextArea initiativeInfo;
    private JTextArea roundStatusInfo;


    public CombatPanel(SubareaPanel subareaPanel) {
        super(new BorderLayout());
        this.subareaPanel=subareaPanel;
        combat = new GUICombatEngine(this);
        combat.enterCombat();
        setBackground(Color.blue);
        setPreferredSize(new Dimension(TitlePanel.SCREEN_WIDTH,TitlePanel.SCREEN_HEIGHT));
        //setVisible(false);
        populateFillerPanel();
        add(areaTextPanel(),BorderLayout.CENTER);
        add(buttonPanel(),BorderLayout.SOUTH);
        addComponentListener(adjustSubPanelDimensions(this,.25,.20));
        setVisible(true);
    }

    private void populateFillerPanel() {
        filler = new JPanel();
        filler.setBackground(Color.LIGHT_GRAY);
        filler.add(new StatusPanel(GlobalVariables.mySquad),BorderLayout.NORTH);
        filler.add(new EnemyStatusPanel(getSubareaPanel().getSubArea().getContents().enemies),BorderLayout.CENTER);
        filler.addComponentListener(adjustSubPanelDimensions(filler,.40,.60));

        add(filler,BorderLayout.NORTH);
    }

    private void refreshFiller() {
        Dimension preferred = filler.getPreferredSize();
        filler.removeAll();
        filler.add(new StatusPanel(GlobalVariables.mySquad),BorderLayout.NORTH);

        filler.add(new EnemyStatusPanel(getSubareaPanel().getSubArea().getContents().enemies),BorderLayout.CENTER);
        filler.setPreferredSize(preferred);
        filler.repaint();
    }
    private JPanel areaTextPanel() {
        JPanel panel = new JPanel();
        roundInfo = new JTextArea(combat.getRoundText());
        roundInfo.setEditable(false);
        roundInfo.setFont(new Font("DialogInput",Font.BOLD,24));
        roundInfo.setPreferredSize(new Dimension(TitlePanel.SCREEN_WIDTH,100));
        panel.add(roundInfo);

        initiativeInfo = new JTextArea(combat.initiativeResultString());
        initiativeInfo.setFont(new Font("DialogInput",Font.PLAIN,24));
        initiativeInfo.setPreferredSize(new Dimension(TitlePanel.SCREEN_WIDTH,100));
        panel.add(initiativeInfo);

        roundStatusInfo = new JTextArea(combat.getResultText());
        roundStatusInfo.setPreferredSize(new Dimension(TitlePanel.SCREEN_WIDTH,150));
        panel.add(roundStatusInfo);

        return panel;
    }
    private ComponentAdapter adjustSubPanelDimensions(JPanel parent, double modifier1, double modifier2) {
        return new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int thisHeight = parent.getHeight();
                int thisWidth = parent.getWidth();

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
            GlobalVariables.enemySquad = getSubareaPanel().getSubArea().getContents().enemies;
            if(GlobalVariables.enemySquad.size() >0) {
                String initiative = combat.initiativeResultString();
                if(combat.getRoundInitiative()>0) {
                    EngageEnemy.playerAutoCombat(-1);
                    EngageEnemy.restOfMySquadMove();
                    EngageEnemy.enemySquadMove();

                } else {
                    EngageEnemy.enemySquadMove();
                    EngageEnemy.playerAutoCombat(-1);
                    EngageEnemy.restOfMySquadMove();
                }

                combat.setRound(combat.getRound()+1);
                roundInfo.setText(combat.getRoundText());
                initiativeInfo.setText(initiative);
                refreshFiller();
                roundInfo.repaint();
                initiativeInfo.repaint();
                roundStatusInfo.setText(combat.getResultText());
                EngageEnemy.enemyDmgMap.clear();
                roundStatusInfo.repaint();
            } else {
                JOptionPane.showMessageDialog(this,"No Enemies Remaining","No Enemies", JOptionPane.ERROR_MESSAGE);
                btn.setEnabled(false);
            }

            // Remove and Rebuild entire scene
            //JFrame ancestor = (JFrame) this.getTopLevelAncestor();

            //ancestor.repaint();
            //ancestor.pack();
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

    public SubareaPanel getSubareaPanel() {
        return subareaPanel;
    }
}