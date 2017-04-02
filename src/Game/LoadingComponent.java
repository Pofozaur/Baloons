package Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by kamil on 02.04.2017.
 */
public class LoadingComponent extends JPanel{

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(),getHeight());
        g.setColor(Color.WHITE);
        g.drawString("LOADING",getWidth()/2,getHeight()/2);
    }

    public static void main(String[] args){

    }
}
