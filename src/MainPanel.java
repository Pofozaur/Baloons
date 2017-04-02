import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

    MainPanel(Game gameReferance){
        gameReferance.super();
        BorderLayout bl = new BorderLayout();
        setLayout(bl);

        baloonDrawer = baloonManager.prepareBaloonDrawer();
        content.setLayout(cardLayout);

        content.add(new LoadingComponent(), "LOADING");
        content.add(baloonDrawer, "START");

        sidePanel.setBackground(Color.red);
        sidePanel.setLayout(new GridLayout(5,1));
        sidePanel.add(new MyJButtonPanelSwitcher("BACK", this, PanelName.NEW_GAME));
        sidePanel.add(new MyJButtonPanelSwitcher("END", this, PanelName.START_SCREEN));
        Button switcher = new Button("Switch");
        switcher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             cardLayout.show(content, "START");
            }
        });
        sidePanel.add(switcher);
        sidePanel.add(playerName);
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
        sidePanel.setOpaque(true);
        //JPanel content = new JPanel();
        LoadingComponent lc = new LoadingComponent();

        content.setBackground(Color.green);
        //content.add(lc);
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
        /*mainGamePanel.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension parentSize = getParent().getSize();

                if(parentSize.getWidth() > parentSize.getHeight()){
                    setPreferredSize(new Dimension((int)parentSize.getHeight(), (int)parentSize.getHeight()));
                }else
                    setPreferredSize(new Dimension((int)parentSize.getWidth(), (int)parentSize.getWidth()));
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });*/
        mainGamePanel.setBackground(Color.cyan);
        //filler.add(mainGamePanel);
        add(mainGamePanel, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.EAST);

    }

    private static void resizePreview(JPanel innerPanel, JPanel container) {
        int w = innerPanel.getParent().getWidth();//container.getWidth();
        int h = innerPanel.getParent().getHeight();//container.getHeight();
        int size =  Math.min(w, h);
        innerPanel.setPreferredSize(new Dimension((int)(size/1.5), size));
        container.revalidate();
        //innerPanel.repaint();
    }

    public void setGameSettings(NewGameSettings ngs){
        System.out.println("GAMESETTINGS");
        this.ngs = ngs;
        playerName.setText(ngs.getPlayerName());
        baloonManager.load(ngs.getLevelFilePath());
        baloonDrawer = baloonManager.prepareBaloonDrawer();

    }
}
