import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by kamil on 02.04.2017.
 */
public class SettingsPanel extends Game.GamePanel {

    private Properties gameProperties = null;
    private JTextField widthTextField = new JTextField(6);
    private JTextField heightTextField = new JTextField(6);
    private JCheckBox serverConnectionCheckBox = new JCheckBox();
    private JCheckBox randomLevelsCheckBox = new JCheckBox();
    private JLabel statusLabel = new JLabel();
    private MyJButton  saveButton = new MyJButton("SAVE");

    private JLabel panelName = new JLabel("Settings");
    SettingsPanel(Game gameReference){
        gameReference.super();
        gameProperties = gameReference.getGameProperties();

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(new Insets(40, 60, 40, 60)));

        JPanel gridPanel = new JPanel(new GridLayout(5,1));
        setBorder(new EmptyBorder(new Insets(40, 60, 40, 60)));

        panelName.setFont(new Font("Tahoma", Font.BOLD, 30));
        panelName.setHorizontalAlignment(SwingConstants.CENTER);

        gridPanel.add(panelName);

        JPanel resolutionSection = new JPanel();
        resolutionSection.add(new JLabel("Default width"));
        resolutionSection.add(widthTextField);
        resolutionSection.add(new JLabel("Default height"));
        resolutionSection.add(heightTextField);

        gridPanel.add(resolutionSection);



        widthTextField.setText(gameProperties.getProperty("width"));
        heightTextField.setText(gameProperties.getProperty("height"));

        JPanel checkBoxSection = new JPanel();
        serverConnectionCheckBox.setText("Server Connection");
        serverConnectionCheckBox.setSelected(Boolean.parseBoolean(gameProperties.getProperty("serverConnection")));
        checkBoxSection.add(serverConnectionCheckBox);

        randomLevelsCheckBox.setText("Random Levels");
        randomLevelsCheckBox.setSelected(Boolean.parseBoolean(gameProperties.getProperty("randomLevels")));
        checkBoxSection.add(randomLevelsCheckBox);

        gridPanel.add(checkBoxSection);

        JPanel buttonPanel = new JPanel();


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsPanel.this.saveProperties();
            }
        });
        buttonPanel.setLayout(new GridLayout(1,2,10,10));
        buttonPanel.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        buttonPanel.add(new MyJButtonPanelSwitcher("BACK", this, PanelName.START_SCREEN));
        buttonPanel.add(saveButton);
        gridPanel.add(buttonPanel);
        gridPanel.add(statusLabel);
        add(gridPanel);
    }

    private void saveProperties(){
        FileOutputStream fileOut = null;
        System.out.println("save PROPERTIES");
        gameProperties.setProperty("width",widthTextField.getText());
        gameProperties.setProperty("height",heightTextField.getText());
        gameProperties.setProperty("serverConnection",Boolean.toString(serverConnectionCheckBox.isSelected()));
        gameProperties.setProperty("randomLevels",Boolean.toString(randomLevelsCheckBox.isSelected()));
        try {
            File file = new File("config.properties");
            fileOut = new FileOutputStream(file);
            gameProperties.store(fileOut,"");
            fileOut.close();
            setStatus("Settings Saved");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                    TimeUnit.SECONDS.sleep(3);
                    setStatus("");}
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private synchronized void setStatus(String status){
        statusLabel.setText(status);
    }
}
