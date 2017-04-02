import java.awt.*;
import java.util.ArrayList;

/**
 * Created by kamil on 02.04.2017.
 */
public class BaloonContainer extends Container{

    private int rows, cols;


    BaloonContainer(int rows, int cols){
        this.rows = rows;
        this.cols = cols;

        setSize(new Dimension(getParent().getWidth(), getParent().getHeight()));
    }


    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(Component c : this.getComponents())
            c.paint(g);
    }


}
