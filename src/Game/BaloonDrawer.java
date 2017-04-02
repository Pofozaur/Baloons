package Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by kamil on 02.04.2017.
 */
public class BaloonDrawer extends JPanel {

    private int columns;

    public void add(Baloon b){
        super.add(b);
        System.out.println(b);
    }

    public BaloonDrawer(){columns = 0;
    }

    public BaloonDrawer(int columns){
        super();
        this.columns = columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getColumns() {
        return columns;
    }

    public void addSpacing(){
        for(Component c : getComponents())
            ((Baloon)c).addSpace();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawRect(0,0,getWidth()/columns * columns -1,getHeight()-1);
        //g.setColor(Color.WHITE);
        //g.drawString("BALOONDRAWER",getWidth()/2,getHeight()/2);
        for(Component c : getComponents())
            c.paint(g);
        System.out.println(getSize());
    }

}
