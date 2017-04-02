package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by kamil on 02.04.2017.
 */
public class MainPanel extends Game.GamePanel {

    private JPanel sidePanel = new JPanel();
    private JPanel mainGamePanel = new JPanel(new GridBagLayout());
    private JPanel content = new JPanel();
    private CardLayout cardLayout = new CardLayout();

    private NewGameSettings ngs;

    private BaloonManager baloonManager = new BaloonManager();
    private BaloonDrawer baloonDrawer;

    private JLabel playerName = new JLabel();
    private JLabel difficulty = new JLabel();

    MainPanel(Game gameReferance){
        gameReferance.super();
        BorderLayout bl = new BorderLayout();
        setLayout(bl);

        baloonDrawer = baloonManager.prepareBaloonDrawer();


        //SIDEPANEL

        sidePanel.setBackground(Color.white);
        sidePanel.setLayout(new GridLayout(6,1));

        sidePanel.add(new MyJButtonPanelSwitcher("BACK", this, PanelName.NEW_GAME));
        sidePanel.add(new MyJButtonPanelSwitcher("END", this, PanelName.START_SCREEN));
        sidePanel.add(playerName);
        sidePanel.add(difficulty);

        Button switcher = new Button("Switch");
        switcher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             cardLayout.show(content, "START");
            }
        });
        sidePanel.add(switcher);

        Button loadNext = new Button("loadNext");
        loadNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(content, "LOADING");
                baloonManager.loadNext("level2.level");
                baloonDrawer = baloonManager.getBaloonDrawer();
                cardLayout.show(content,"START");
                }
        });
        sidePanel.add(loadNext);

        //CONTENT
        content.setLayout(cardLayout);
        content.add(new LoadingComponent(), "LOADING");
        content.add(baloonDrawer, "START");

        content.setBackground(Color.green);

        //MAINGAMEPANEL
        mainGamePanel.add(content);

        mainGamePanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                //System.out.println(mainGamePanel.getSize().width);
                //int w = content.getParent().getWidth();//container.getWidth();
                //int h = content.getParent().getHeight();//container.getHeight();
                //int size =  Math.min(w, h);
                //content.setPreferredSize(new Dimension(size, size));


                resizePreview(content, mainGamePanel);
                //System.out.println(content.getSize());
            }
        });
        //mainGamePanel.setBackground(Color.white);

        add(mainGamePanel, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.EAST);

    }

    private static void resizePreview(JPanel innerPanel, JPanel container) {
        int w = innerPanel.getParent().getWidth();//container.getWidth();
        int h = innerPanel.getParent().getHeight();//container.getHeight();

        if(h<w)
            innerPanel.setPreferredSize(new Dimension((int)(h/1.5), h));
        else
            if(h>(int)(w*1.5))
                innerPanel.setPreferredSize(new Dimension(w, (int)(w*1.5)));
            else
                innerPanel.setPreferredSize(new Dimension((int)(h/1.5), h));
        //int size =  Math.min(w, h);
        //innerPanel.setPreferredSize(new Dimension((int)(size/1.5), size));
        container.revalidate();
    }

    public void setGameSettings(NewGameSettings ngs){
        System.out.println("GAMESETTINGS");
        this.cardLayout.show(content, "LOADING");
        this.ngs = ngs;
        playerName.setText(ngs.getPlayerName());
        difficulty.setText(ngs.getDifficulty().toString());

        baloonManager.load(ngs.getLevelFilePath());
        baloonDrawer = baloonManager.prepareBaloonDrawer();
        this.cardLayout.show(content,"START");

    }


}
