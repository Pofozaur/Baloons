
package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by kamil on 02.04.2017.
 */
public class Baloon extends JComponent {

    private BaloonType baloonType;
    private Point position;
    private float diameter;
    private boolean offset;

    private static Color translateColor(BaloonType bt){
        switch (bt){
            case BLUE:      return Color.blue;
            case GREEN:     return Color.green;
            case ORANGE:    return Color.orange;
            case PINK:      return Color.pink;
            case RED:       return Color.red;
            case YELLOW:    return Color.yellow;
            case BOMB:      return Color.black;
            case TIME:      return Color.white;
            case NEXT:      return Color.darkGray;
            default: return Color.green;
        }
    }

    Baloon(BaloonType baloonType, Point position, boolean offset){
        this.baloonType = baloonType;
        this.position = position;
        this.offset = offset;
    }

    Baloon(BaloonType baloonType){
        this.baloonType = baloonType;
        this.position = null;
    }

    public void addSpace(){
        this.position.y = this.position.y + 1;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
    public void setDiameter(float diameter) {
        this.diameter = diameter;
    }

    @Override
    public String toString() {
        return baloonType.toString() + " " + position.toString();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Dimension parentSize = getParent().getSize();
        float width = (parentSize.width-1)/((BaloonDrawer)getParent()).getColumns() ;
        int offsetInt = (offset) ? 1 : 0;
        float xPos = width * position.x + width/2*offsetInt;
        float yPos = width * position.y;
        Shape circle = new Ellipse2D.Float(xPos, yPos, width, width);
        Graphics2D ga =(Graphics2D)g;
        //ga.draw(circle);
        ga.setPaint(translateColor(baloonType));
        ga.fill(circle);
        //System.out.println("BOO");
    }
}
