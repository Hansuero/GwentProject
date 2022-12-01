import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PictureView extends JPanel
{
    private Image image;

    public void setImage(Image image)
    {
        this.image = image;

        repaint();

    }


    protected void paintComponent(Graphics g)
    {

        super.paintComponent(g);


        int width = getWidth();
        int height = getHeight();

        g.drawImage(this.image, 0, 0, width, height, null);

        this.setOpaque(true);
    }


}
