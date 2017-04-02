package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kamil on 02.04.2017.
 */
public class MyJButtonPanelSwitcher extends MyJButton{

    private Game.GamePanel callBack;
    private PanelName switchTo;

    MyJButtonPanelSwitcher(String name, Game.GamePanel callBack, PanelName switchTo){
        super(name);
        this.callBack = callBack;
        this.switchTo = switchTo;

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callBack.setScene(switchTo);
            }
        });
    }
}
