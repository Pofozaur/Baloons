import javax.swing.*;
import java.awt.*;
import java.io.*;

import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by kamil on 29.03.2017.
 */
public class Game extends JFrame{


    private CardLayout cardLayout = new CardLayout();
    private JPanel cardJPanel = new JPanel();
    private Properties gameProperties = null;
    private BaloonManager baloonManager = null;
    private MainPanel mp = null;


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


        mp = new MainPanel(this);
            cardJPanel.add(new StartScreenPanel(this), PanelName.START_SCREEN.toString());
        cardJPanel.add(new NewGamePanel(this, mp), PanelName.NEW_GAME.toString());
        cardJPanel.add(new SettingsPanel(this), PanelName.SETTINGS.toString());
        cardJPanel.add(new ScoresPanel(this), PanelName.SCORES.toString());

        cardJPanel.add(mp, PanelName.MAIN.toString());
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

        /*ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new TestFileThread());*/

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Game myGame = new Game();
            }
        });
        System.out.println("sdgdfg");

       /* Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                System.out.println("In shutdown hook");
            }
        }, "Shutdown-thread"));*/

    }
}
