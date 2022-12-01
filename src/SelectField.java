import javax.swing.*;
import java.awt.*;

public class SelectField extends JPanel
{
    ImageIcon BGIcon;
    Image BGImage;

    SelectField()
    {
        //生成背景
        BGIcon = new ImageIcon("./img/field/Selectfield.png");
        BGImage = BGIcon.getImage();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(BGImage, 0, 0, getWidth(), getHeight(), this);

    }
}
