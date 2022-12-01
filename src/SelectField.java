import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SelectField extends JPanel
{
    public void showImg(Scene scene)
    {
        PictureView selectBGView = new PictureView();
        scene.setContentPane(selectBGView);
        try
        {
            Image BGImage = ImageIO.read(new File("./img/field/Selectfield.png"));
            selectBGView.setImage(BGImage);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
