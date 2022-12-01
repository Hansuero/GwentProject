import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class StartMenu extends JFrame
{
    JButton startButton;
    JButton exitButton;

    public StartMenu(String title)
    {
        //设置窗口大小
        super(title);
        this.setSize(800, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //加载背景图
        PictureView menuView = new PictureView();
        this.setContentPane(menuView);
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
