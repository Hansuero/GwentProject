import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SelectField extends JPanel
{
    ImageIcon icon;
    Image img;
    SelectField(){
        icon=new ImageIcon(getClass().getResource("Selectfield.png"));
        img=icon.getImage();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img,0,0,getWidth(),getHeight(),this);
    }
}
