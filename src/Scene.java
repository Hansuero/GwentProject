import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Scene extends JFrame
{
    private CardLayout panelLayout;
    Panel sceneSet;
    public Scene(String title)
    {

        setTitle("Gwent Demo");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StartMenu startMenu = new StartMenu(this);
        this.add(startMenu);
        setVisible(true);
    }


}
