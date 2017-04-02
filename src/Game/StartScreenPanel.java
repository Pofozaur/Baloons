package Game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kamil on 29.03.2017.
 */
public class StartScreenPanel extends Game.GamePanel   {

        private JButton newGameButton, settingsButton, scoresButton, exitButton;
        private JLabel panelName = new JLabel("Baloons");

    StartScreenPanel(Game gameReference){

        gameReference.super();

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(new Insets(40, 60, 40, 60)));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(5,1, 10, 10));

        newGameButton = new MyJButtonPanelSwitcher("NEW GAME", this, PanelName.NEW_GAME);
        settingsButton = new MyJButtonPanelSwitcher("SETTINGS", this, PanelName.SETTINGS);
        scoresButton = new MyJButtonPanelSwitcher("SCORES", this, PanelName.SCORES);
        exitButton = new MyJButton("Exit");

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Wyjscie");
                System.exit(0);
            }
        });

        panelName.setFont(new Font("Tahoma", Font.BOLD, 30));
        panelName.setHorizontalAlignment(SwingConstants.CENTER);

        buttonsPanel.add(panelName);
        buttonsPanel.add(newGameButton);
        buttonsPanel.add(settingsButton);
        buttonsPanel.add(scoresButton);
        buttonsPanel.add(exitButton);

        add(buttonsPanel, BorderLayout.CENTER);
    }
}
