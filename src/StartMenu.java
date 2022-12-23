import javax.swing.*;
import java.awt.event.ActionEvent;

public class StartMenu extends JFrame
{
    //游戏名称（窗口名称）
    private static final String PROGRAM_NAME = "Card Combat";

    /**
     * 设定开始界面的背景图片
     */
    public void setBg()
    {
        ((JPanel) this.getContentPane()).setOpaque(false);
        ImageIcon img = new ImageIcon
                ("./res/image/Start.jpg");
        JLabel background = new JLabel(img);
        this.getLayeredPane().add(background, Integer.valueOf(Integer.MIN_VALUE));
        background.setBounds(0, 0, this.getWidth(), this.getHeight());
    }

    StartMenu()
    {
        super(PROGRAM_NAME);
        this.setVisible(true);
        this.setSize(1400, 900);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("res/image/Unknown.jpg");
        this.setIconImage(icon.getImage());
        this.setBg();
    }

    public static void main(String[] args)
    {
        StartMenu startMenu = new StartMenu();
    }
}
