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
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        StartMenu startMenu = new StartMenu();
        startMenu.showImg(this);
        //panelLayout = new CardLayout();
        //sceneSet = new Panel(panelLayout);
        //this.add(sceneSet);
        //panelLayout.addLayoutComponent("startMenu",startMenu);


        SelectField selectField = new SelectField();
        selectField.showImg(this);

        //panelLayout.addLayoutComponent("selectField",selectField);


    }


}
