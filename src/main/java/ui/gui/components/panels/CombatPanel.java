package ui.gui.components.panels;

import gamecontrol.GlobalVariables;
import gamemodel.combatengine.EngageEnemy;
import gamemodel.combatengine.GUICombatEngine;
import gamemodel.combatengine.UICombat;
import ui.gui.components.dialogs.InventoryDialog;
import ui.gui.components.buttons.SettingsButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class CombatPanel extends JPanel {

    private JPanel statuses;
    SubareaPanel subareaPanel;
    GUICombatEngine combat;
    private JTextArea roundInfo;
    private JTextArea initiativeInfo;
    private JLabel roundStatusInfo;


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
        statuses = new JPanel();
        statuses.setBackground(Color.LIGHT_GRAY);
        statuses.add(new StatusPanel(GlobalVariables.mySquad),BorderLayout.NORTH);
        statuses.add(new EnemyStatusPanel(getSubareaPanel().getSubArea().getContents().enemies),BorderLayout.CENTER);
        statuses.addComponentListener(adjustSubPanelDimensions(statuses,.40,.60));

        add(statuses,BorderLayout.NORTH);
    }

    private void refreshStatuses() {
        Dimension preferred = statuses.getPreferredSize();
        statuses.removeAll();

        JPanel status = new StatusPanel(GlobalVariables.mySquad);
        status.setPreferredSize(new Dimension(preferred.width,(int)(preferred.height *.4)));
        statuses.add(status,BorderLayout.NORTH);

        JPanel enemies = new EnemyStatusPanel(getSubareaPanel().getSubArea().getContents().enemies);
        enemies.setPreferredSize(new Dimension(preferred.width,(int)(preferred.height *.6)));
        statuses.add(enemies,BorderLayout.CENTER);

        statuses.repaint();
    }
    private JPanel areaTextPanel() {
        JPanel panel = new JPanel();
        roundInfo = new JTextArea(combat.getRoundText());
        roundInfo.setEditable(false);
        roundInfo.setFont(new Font("DialogInput",Font.BOLD,24));
        roundInfo.setPreferredSize(new Dimension(TitlePanel.SCREEN_WIDTH,100));
        panel.add(roundInfo);

        initiativeInfo = new JTextArea();
        initiativeInfo.setFont(new Font("DialogInput",Font.PLAIN,24));
        initiativeInfo.setPreferredSize(new Dimension(TitlePanel.SCREEN_WIDTH,100));
        panel.add(initiativeInfo);

        roundStatusInfo = new JLabel(combat.getResultText());
        roundStatusInfo.setPreferredSize(new Dimension(TitlePanel.SCREEN_WIDTH,150));
        panel.add(roundStatusInfo);
        EngageEnemy.enemyDmgMap.clear();
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
            btn.setEnabled(false);
            if(GlobalVariables.enemySquad.size() >0) {
                combat.autoCombat();

                advanceRound();
                refreshStatuses();
                btn.setEnabled(true);
            } else {
                retreatButton();
            }
        });
        return btn;
    }

    private JButton targetEnemyAttackButton() {
        JButton btn = new JButton("Attack Target");
        // TODO implement functionality
        btn.addActionListener(e -> {
            btn.setEnabled(false);
            if(GlobalVariables.enemySquad.size() > 0) {
                int target = parseUserInput();

                if (target > -1) {
                    combat.targetedCombat(target);


                    advanceRound();
                    refreshStatuses();
                } else {
                    JOptionPane.showMessageDialog(this,"You canceled your attack.","Attack Canceled",JOptionPane.INFORMATION_MESSAGE);
                }
                btn.setEnabled(true);
            } else {
                retreatButton();
            }

        });
        return btn;
    }

    private JButton useItemsButton() {
        // TODO: auto refresh stats panel after complete
        JButton btn = new JButton("Use Items");
        btn.addActionListener(e -> new InventoryDialog((JFrame) getTopLevelAncestor()));
        return btn;
    }

    private JButton retreatButton() {
        JButton retreatBtn = new JButton("Retreat");
        retreatBtn.addActionListener(e ->{
            boolean retreat = new Random().nextBoolean();
            if(GlobalVariables.enemySquad.size() == 0) {
                JOptionPane.showMessageDialog(this, "No enemies remain!","Successful Combat",JOptionPane.INFORMATION_MESSAGE);
                backToMap();
            } else if(retreat) {
                JOptionPane.showMessageDialog(this, UICombat.reportRetreatResults(true),"Successful Retreat!",JOptionPane.INFORMATION_MESSAGE);
                backToMap();
            } else {
                JOptionPane.showMessageDialog(this, UICombat.reportRetreatResults(false),"Retreat Failed!",JOptionPane.INFORMATION_MESSAGE);
                EngageEnemy.enemySquadMove();
                refreshStatuses();
                advanceRound();
            }
        });
        return retreatBtn;
    }

    public void backToMap() {
        JFrame ancestor = (JFrame) getTopLevelAncestor();
        ancestor.getContentPane().removeAll();
        if(GlobalVariables.enemySquad.isEmpty()) {
            getSubareaPanel().getCombatButton().setEnabled(false);
            getSubareaPanel().getLootButton().setEnabled(true);
        }
        ancestor.add(subareaPanel);
        ancestor.add(new StatusPanel(GlobalVariables.mySquad),BorderLayout.NORTH);
        ancestor.setPreferredSize(new Dimension(1024,768));
        ancestor.revalidate();
        ancestor.repaint();
        ancestor.pack();
    }

    private void advanceRound() {
        combat.setRound(combat.getRound()+1);
        roundInfo.setText(combat.getRoundText());
        initiativeInfo.setText(combat.initiativeResultString());
        roundStatusInfo.setText(combat.getResultText());

        roundInfo.repaint();
        initiativeInfo.repaint();
        roundStatusInfo.repaint();

        EngageEnemy.enemyDmgMap.clear(); // Clears the list of cumulative damage done each round.
    }

    private int parseUserInput() {
        String userInput;
        int target = 0;

        while (target < 1 || target > GlobalVariables.enemySquad.size()) {
            try {
                userInput = JOptionPane.showInputDialog(this,"Choose a target number to attack: ","Select Target Number",JOptionPane.INFORMATION_MESSAGE);
                if(userInput == null) {
                    return -2;
                }
                target = Integer.parseInt(userInput);
                if(target < 1 || target > GlobalVariables.enemySquad.size()) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,"Error that input is not a valid number!","Invalid Input Error",JOptionPane.ERROR_MESSAGE);
                target = -1;
            }
        }
        target--;
        return target;
    }

    public SubareaPanel getSubareaPanel() {
        return subareaPanel;
    }

    public JTextArea getInitiativeInfo() {
        return initiativeInfo;
    }
}