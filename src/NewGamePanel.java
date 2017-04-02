import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kamil on 29.03.2017.
 */
public class NewGamePanel extends Game.GamePanel {

    private JLabel panelName = new JLabel("New Game");
    private JRadioButton
        easyButton = new JRadioButton("Easy: "),
        normalButton = new JRadioButton("Normal: "),
        hardButton = new JRadioButton("Hard: ");
    private JTextField playerNameTextField = new JTextField(20);
    private MainPanel mp = null;
    public NewGamePanel(Game gameReference, MainPanel mainPanelReference){
        gameReference.super();
        mp = mainPanelReference;
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(new Insets(40, 60, 40, 60)));

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(4,1, 10, 10));

        panelName.setFont(new Font("Tahoma", Font.BOLD, 30));
        panelName.setHorizontalAlignment(SwingConstants.CENTER);
        gridPanel.add(panelName);

        JPanel playerNamePanel = new JPanel();
        playerNamePanel.add(new JLabel("Player's name: "));
        playerNameTextField.setText("Player");
        playerNamePanel.add(playerNameTextField);
        gridPanel.add(playerNamePanel);

        ButtonGroup buttonsGroup = new ButtonGroup();
        buttonsGroup.add(easyButton);
        buttonsGroup.add(normalButton);
        buttonsGroup.add(hardButton);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(easyButton);
        buttonsPanel.add(normalButton);
        buttonsPanel.add(hardButton);
        easyButton.setSelected(true);

        gridPanel.add(buttonsPanel);

        JPanel controlButtonsPanel = new JPanel();

        controlButtonsPanel.add(new MyJButtonPanelSwitcher("BACK", this, PanelName.START_SCREEN));
        MyJButton startGameButton = new MyJButtonGameInitializer("START", this, PanelName.MAIN, mp);
        controlButtonsPanel.add(startGameButton);
        //controlButtonsPanel.add(new MyJButtonPanelSwitcher("START", this, PanelName.MAIN));
        //mp.setGameSettings(new NewGameSettings("level1.level", Difficulty.EASY, playerNameTextField.getText()));
        gridPanel.add(controlButtonsPanel);
        add(gridPanel, BorderLayout.CENTER);
    }

    public NewGameSettings getNGS(){
        return new NewGameSettings("level1.level", Difficulty.EASY, playerNameTextField.getText());
    }

}
