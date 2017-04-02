import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Properties;

/**
 * Created by kamil on 02.04.2017.
 */
public class ScoresPanel extends Game.GamePanel {

    private Properties gameProperties = null;
    private ScoresContainer scoresContainer= new ScoresContainer();
    private JLabel panelName = new JLabel("Scores");

    ScoresPanel(Game gameReference){

        gameReference.super();
        gameProperties = gameReference.getGameProperties();
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(new Insets(40, 60, 40, 60)));

        JPanel insidePanel = new JPanel(new BorderLayout(10, 0));
        insidePanel.setBorder(new EmptyBorder(new Insets(40, 60, 40, 60)));

        panelName.setFont(new Font("Tahoma", Font.BOLD, 30));
        panelName.setHorizontalAlignment(SwingConstants.CENTER);

        scoresContainer.loadFromFile();

        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                if(isVisible())
                System.out.println("SCORES Resized");
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                System.out.println("SCORES MOVED");
            }

            @Override
            public void componentShown(ComponentEvent e) {
                loadScores();
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                System.out.println("SCORES Hiddem");
            }
        });


        //add(new MyJButtonPanelSwitcher("BACK", this, PanelName.START_SCREEN));

       // JPanel middlePanel = new JPanel ();
       // middlePanel.setBorder ( new TitledBorder( new EtchedBorder(), "SCORES" ) );
        JTextArea display = new JTextArea ( 10, 40 );
        display.setEditable ( false ); // set textArea non-editable
        JScrollPane scroll = new JScrollPane ( display );
        scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );

        //Add Textarea in to middle panel
        //middlePanel.add ( scroll );
        //add(scroll)
        display.setText(scoresContainer.toString());
        //add(middlePanel);

        insidePanel.add(panelName,BorderLayout.NORTH);
        insidePanel.add(scroll,BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(new MyJButtonPanelSwitcher("BACK", this, PanelName.START_SCREEN), BorderLayout.CENTER);
        buttonPanel.setBorder(new EmptyBorder(new Insets(40, 60, 40, 60)));
        insidePanel.add(buttonPanel, BorderLayout.SOUTH);
        //insidePanel.add(new MyJButtonPanelSwitcher("BACK", this, PanelName.START_SCREEN), BorderLayout.SOUTH);
        add(insidePanel);

    }

    private void loadScores(){


    }
}
