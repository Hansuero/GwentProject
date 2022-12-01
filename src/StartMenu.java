import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class StartMenu extends JPanel
{
    JButton startButton;
    JButton exitButton;
    public void showImg(Scene scene)
    {
        PictureView menuView = new PictureView();
        scene.setContentPane(menuView);
        try
        {
            Image menuImage = ImageIO.read(new File("./img/field/menu.jpeg"));
            menuView.setImage(menuImage);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


}
