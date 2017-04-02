import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import java.awt.*;

/**
 * Created by kamil on 02.04.2017.
 */
public class ImageJPanel extends JPanel {

    private BufferedImage image;

    public ImageJPanel(String path) {
        super();
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setMaximumSize(new Dimension(400,200));
        setMinimumSize(new Dimension(200,100));
        setPreferredSize(new Dimension(300,150));


        EventQueue.invokeLater(new Runnable() {
            public void run() {
                setVisible(true);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            g.drawImage(image, -50, -50, this);
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
        //setSize(image.getWidth(this), image.getHeight(this));
    }
}
