package Game;

import javax.swing.*;
import java.awt.*;
import java.io.*;

import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by kamil on 29.03.2017.
 */
public class Game extends JFrame{


    private CardLayout cardLayout = new CardLayout();
    private JPanel cardJPanel = new JPanel();

    private Properties gameProperties = null;
    private BaloonManager baloonManager = null;

    //private MainPanel mp = null;
    private GamePanel[] panelReferences;

    class GamePanel extends JPanel{
        void setScene(PanelName scene){
            System.out.println("setScene() " + scene.toString());
            cardLayout.show(Game.this.cardJPanel, scene.toString());
        }
    }

    public Game(){

        super("BaloonGame");

        loadProperties();

        add(cardJPanel);
        cardJPanel.setLayout(cardLayout);

        panelReferences = new GamePanel[5];

        panelReferences[PanelName.START_SCREEN.ordinal()] = new StartScreenPanel(this);
        panelReferences[PanelName.MAIN.ordinal()] = new MainPanel(this);
        panelReferences[PanelName.NEW_GAME.ordinal()] =
                new NewGamePanel(this, (MainPanel)panelReferences[PanelName.MAIN.ordinal()]);
        panelReferences[PanelName.SCORES.ordinal()] = new ScoresPanel(this);
        panelReferences[PanelName.SETTINGS.ordinal()] = new SettingsPanel(this);

        for(PanelName pn : PanelName.values())
            cardJPanel.add(panelReferences[pn.ordinal()], pn.toString());

       // mp = (MainPanel) panelReferences[PanelName.MAIN.ordinal()];
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(new Dimension(Integer.parseInt(gameProperties.getProperty("width")), Integer.parseInt(gameProperties.getProperty("height"))));
        setVisible(true);
    }

    private void loadProperties(){
        FileInputStream fileIn = null;
        System.out.println("PROPERTIES LOAD");
        try {
            File file = new File("config.properties");
            fileIn = new FileInputStream(file);
            gameProperties = new Properties();
            gameProperties.load(fileIn);
            fileIn.close();

            Enumeration enuKeys = gameProperties.keys();
            while (enuKeys.hasMoreElements()) {
                String key = (String) enuKeys.nextElement();
                String value = gameProperties.getProperty(key);
                System.out.println(key + ": " + value);
            }
        } catch (FileNotFoundException e) {

            createProperties();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void createProperties() {
        FileOutputStream fileOut = null;
        System.out.println("CREATE PROPERTIES");
        try {
            gameProperties = new Properties();
            gameProperties.setProperty("width", "800");
            gameProperties.setProperty("height", "450");
            gameProperties.setProperty("serverConnection", "true");
            gameProperties.setProperty("randomLevels", "false");

            File file = new File("config.properties");
            fileOut = new FileOutputStream(file);
            gameProperties.store(fileOut,"");
            fileOut.close();
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
    Properties getGameProperties(){return gameProperties;}

    public static void main(String[] args){


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Game myGame = new Game();
            }
        });
        System.out.println("sdgdfg");

    }
}
