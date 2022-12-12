import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Scene extends JFrame
{
    private CardLayout panelLayout;
    public Scene(String title)
    {
        setTitle("Gwent Demo");
        setSize(1960, 1080);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StartMenu startMenu = new StartMenu(this);
        setContentPane(startMenu);
        setVisible(true);
    }


}
