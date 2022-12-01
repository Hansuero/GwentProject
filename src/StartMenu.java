import javax.swing.*;
import java.awt.*;

public class StartMenu extends JPanel
{
    ImageIcon buttonIcon;
    ImageIcon BGIcon;
    Image BGImage;
    JButton startButton;
    JButton exitButton;

    StartMenu(Scene scene)
    {
        //生成背景
        BGIcon = new ImageIcon("./img/field/menu.jpeg");
        BGImage = BGIcon.getImage();
        //生成StartButton
        buttonIcon = new ImageIcon("./img/field/game.jpg");
        startButton = new JButton(buttonIcon);
        startButton.setSize(new Dimension(150, 50));
        startButton.setMargin(new Insets(-3,-3,-3,-3));
        startButton.setBorderPainted(false);
        startButton.addActionListener(e ->
        {
            SelectField selectField = new SelectField();
            scene.setContentPane(selectField);
            scene.setVisible(true);
        });

        this.add(startButton);

        //生成ExitButton
        buttonIcon = new ImageIcon("./img/field/exit.jpg");
        exitButton = new JButton(buttonIcon);
        exitButton.setSize(new Dimension(150, 50));
        exitButton.setMargin(new Insets(-3,-3,-3,-3));
        exitButton.setBorderPainted(false);
        exitButton.addActionListener(e ->
        {
            System.exit(0);
        });
        this.add(exitButton);

    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(BGImage, 0, 0, getWidth(), getHeight(), this);

    }

}
