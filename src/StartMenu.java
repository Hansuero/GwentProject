import javax.swing.*;
import java.awt.event.ActionEvent;

public class StartMenu extends JFrame {
    private static final String PROGRAM_NAME = "Card Combat";
    public void setBg()
    {
        ((JPanel)this.getContentPane()).setOpaque(false);
        ImageIcon img = new ImageIcon
                ("./res/image/Start.jpg");
        JLabel background = new JLabel(img);
        this.getLayeredPane().add(background, Integer.valueOf(Integer.MIN_VALUE));
        background.setBounds(0, 0, this.getWidth(), this.getHeight());
    }
    StartMenu(){
        super(PROGRAM_NAME);
        this.setVisible(true);
        this.setSize(810, 605);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ImageIcon icon=new ImageIcon("res/image/Unknown.jpg");
        this.setIconImage(icon.getImage());
        this.setBg();

    }
    public static void main(String[] args){
        StartMenu startMenu=new StartMenu();
    }
}
