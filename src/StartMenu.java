import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class StartMenu extends JPanel
{
    ImageIcon icon;
    Image img;
    JButton startButton;
    JButton exitButton;
    StartMenu(Scene scene){
        startButton=new JButton("Start");
        icon=new ImageIcon(getClass().getResource("menu.jpeg"));
        img=icon.getImage();
        startButton.addActionListener(e -> {
            System.out.println("按钮被点击");
            SelectField selectField=new SelectField();
            scene.remove(this);
            scene.add(selectField);
            scene.setVisible(true);
        });
        this.add(startButton);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img,0,0,getWidth(),getHeight(),this);
    }

}
