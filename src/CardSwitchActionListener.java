import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kamil on 30.03.2017.
 */
public class CardSwitchActionListener implements ActionListener {

    private PanelName switchTo;
    private String messageToWrite;
    private boolean ifWrite;

    public CardSwitchActionListener(PanelName switchTo){
        this.switchTo = switchTo;
        messageToWrite = "";
        ifWrite = false;
    }

    public CardSwitchActionListener(PanelName switchTo, String messageToWrite){
        this.switchTo = switchTo;
        this.messageToWrite = messageToWrite;
        ifWrite = true;
    }

    public void actionPerformed(ActionEvent e) {

        if(ifWrite)
            System.out.println("Wroc");

        ((Game.GamePanel)((MyJButton)e.getSource()).getParent()).setScene(switchTo);
    }
}

