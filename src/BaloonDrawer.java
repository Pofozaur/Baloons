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

    public BaloonDrawer(){
        columns = 0;
    }

    public BaloonDrawer(int rows){
        super();
        this.columns = rows;
        //add(new Baloon(BaloonType.BLUE, new Point(0,0), false));
    }

    public void setRows(int rows) {
        this.columns = rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getColumns() {
        return columns;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.setColor(Color.BLACK);
        //g.fillRect(0,0,getWidth(),getHeight());
        //g.setColor(Color.WHITE);
        //g.drawString("BALOONDRAWER",getWidth()/2,getHeight()/2);
        for(Component c : getComponents())
            c.paint(g);
        System.out.println(getSize());
    }

}
