package Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by kamil on 30.03.2017.
 */
public class MyJButton extends JButton {


    MyJButton(String name){
        super(name);
        setBackground(new Color(128, 128, 128));
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setFont(new Font("Tahoma", Font.BOLD, 12));

        setMaximumSize(new Dimension(400,200));
        setMinimumSize(new Dimension(20,10));
        setPreferredSize(new Dimension(100,50));
    }

    @Override
    public Dimension getPreferredSize() {
        return super.getPreferredSize();
    }
}
