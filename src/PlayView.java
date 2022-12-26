/**
 * PlayView.java
 * 创建游戏板的摆动窗口和元素
 * 并提供直接修改窗口的方法。
 */

import java.awt.*;
import javax.swing.*;

public class PlayView extends JFrame
{
    // Members
    public JPanel leftPanel, rightPanel;
    private static final String PROGRAM_NAME = "Gwent Project";
    // Right side panels
    public JPanel p1Melee, p1Ranged, p1Magic,
            p2Melee, p2Ranged, p2Magic, weatherPanel;
    // Left side panels
    public JPanel p1Hand;
    public JPanel p2Hand;
    public JPanel gameInfo, score;
    public JLabel p1Score, p2Score, p1Power, p2Power, weatherLabel, weatherDsc;
    // 对手将拥有的“card back”数组
    public JLabel[] opponentCards;

    /**
     * 设置游戏界面的背景
     * 即卡槽背景
     */
    public void setBg()
    {
        ((JPanel) this.getContentPane()).setOpaque(false);
        ImageIcon img = new ImageIcon
                ("./res/image/bg.png");
        JLabel background = new JLabel(img);
        this.getLayeredPane().add(background, Integer.valueOf(Integer.MIN_VALUE));
        background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
    }


    /**
     * 初始化游戏窗口
     */
    public PlayView()
    {
        super(PROGRAM_NAME);
        this.setSize(1400, 900);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("res/image/Unknown.jpg");
        this.setIconImage(icon.getImage());

        // 初始化 JPanel 和 JLabel 对象
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        setBg();
        leftPanel.setOpaque(false);
        rightPanel.setOpaque(false);

        p1Melee = new JPanel();
        p1Melee.setOpaque(false);
        p1Ranged = new JPanel();
        p1Ranged.setOpaque(false);
        p1Magic = new JPanel();
        p1Magic.setOpaque(false);

        p2Melee = new JPanel();
        p2Melee.setOpaque(false);
        p2Ranged = new JPanel();
        p2Ranged.setOpaque(false);
        p2Magic = new JPanel();
        p2Magic.setOpaque(false);

        weatherPanel = new JPanel();
        weatherPanel.setOpaque(false);

        p1Hand = new JPanel();
        p1Hand.setOpaque(false);
        p2Hand = new JPanel();
        p2Hand.setOpaque(false);

        gameInfo = new JPanel();
        gameInfo.setOpaque(false);
        score = new JPanel();
        score.setOpaque(false);
        // 以下默认消息将由控制器替换
        p1Score = new JLabel("Player 1 Score", JLabel.RIGHT);
        p2Score = new JLabel("Player 2 Score", JLabel.LEFT);
        p1Power = new JLabel("Player 1 Power", JLabel.CENTER);
        p2Power = new JLabel("Player 2 Power", JLabel.CENTER);

        weatherLabel = new JLabel("Weather", JLabel.CENTER);
        weatherDsc = new JLabel("Weather info here", JLabel.CENTER);
        opponentCards = new JLabel[PlayModel.HAND_SIZE];

        // 添加一些布局和边框，以及一些样式
        getContentPane().setLayout(new GridLayout(0, 2));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder());
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder());

        p1Melee.setLayout(new FlowLayout());
        p1Melee.setPreferredSize(new Dimension(600, 70));
        p1Melee.setBorder(BorderFactory.createTitledBorder("Melee"));

        p1Ranged.setLayout(new FlowLayout());
        p1Ranged.setPreferredSize(new Dimension(500, 70));
        p1Ranged.setBorder(BorderFactory.createTitledBorder("Ranged"));

        p1Magic.setLayout(new FlowLayout());
        p1Magic.setPreferredSize(new Dimension(500, 70));
        p1Magic.setBorder(BorderFactory.createTitledBorder("Magic"));

        p2Melee.setLayout(new FlowLayout());
        p2Melee.setPreferredSize(new Dimension(500, 70));
        p2Melee.setBorder(BorderFactory.createTitledBorder("Melee"));

        p2Ranged.setLayout(new FlowLayout());
        p2Ranged.setPreferredSize(new Dimension(500, 70));
        p2Ranged.setBorder(BorderFactory.createTitledBorder("Ranged"));

        p2Magic.setLayout(new FlowLayout());
        p2Magic.setPreferredSize(new Dimension(500, 70));
        p2Magic.setBorder(BorderFactory.createTitledBorder("Magic"));


        p1Hand.setLayout(new FlowLayout(PlayModel.HAND_SIZE));
        p1Hand.setBorder(BorderFactory.createTitledBorder("Hand"));
        p2Hand.setLayout(new FlowLayout(PlayModel.HAND_SIZE));
        p2Hand.setBorder(BorderFactory.createTitledBorder("Hand"));

        gameInfo.setLayout(new GridLayout(3, 0));
        gameInfo.setBorder(BorderFactory.createEmptyBorder());
        score.setLayout(new GridLayout(0, 5));

        weatherPanel.setLayout(new GridLayout(2, 0));
        weatherLabel.setFont(new Font("Sans Serif", Font.PLAIN, 18));

        p1Score.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        p2Score.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        p1Power.setFont(new Font("Sans Serif", Font.PLAIN, 24));
        p2Power.setFont(new Font("Sans Serif", Font.PLAIN, 24));

        // 把生成的每一个组件添加到容器中
        this.add(leftPanel);
        this.add(rightPanel);

        leftPanel.add(p2Hand);
        leftPanel.add(gameInfo);
        gameInfo.add(score);

        score.add(new JLabel("Your Score", JLabel.CENTER));
        score.add(p1Score);
        score.add(new JLabel("-", JLabel.CENTER));
        score.add(p2Score);
        score.add(new JLabel("Opponent Score", JLabel.CENTER));
        score.setVisible(true);

        leftPanel.add(p1Hand);
        rightPanel.add(p2Magic);
        rightPanel.add(p2Ranged);
        rightPanel.add(p2Melee);
        rightPanel.add(weatherPanel);

        weatherPanel.add(weatherLabel);
        weatherPanel.add(weatherDsc);
        weatherPanel.setVisible(true);

        rightPanel.add(p1Melee);
        rightPanel.add(p1Ranged);
        rightPanel.add(p1Magic);
        rightPanel.setVisible(true);

        gameInfo.add(p2Power);
        gameInfo.add(score);
        gameInfo.add(p1Power);
        gameInfo.setVisible(true);

        setVisible(true);
    }

    /**
     * 背景类
     */
    class BackgroundPanel extends JPanel
    {
        Image im;

        public BackgroundPanel(Image im)
        {
            this.im = im;
            //设置控件不透明,若是false,那么就是透明
            this.setOpaque(true);
        }

        //Draw the background again,继承自JPanle,是Swing控件需要继承实现的方法,而不是AWT中的Paint()
        public void paintComponent(Graphics g)
        {
            super.paintComponents(g);
            //绘制指定图像中当前可用的图像。图像的左上角位于该图形上下文坐标空间的 (x, y)。图像中的透明像素不影响该处已存在的像素
            g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }


    /**
     * 在窗户上显示天气状况
     * PlayModel.calculatePower() 用于图形端。
     *
     * @param condition 当前要设置的天气
     */

    public void setWeather(PlayModel.Weather condition)
    {
        switch (condition)
        {
            case CLEAR:
                weatherLabel.setText("Clear");
                weatherDsc.setText("The clear weather bothers nobody");
                break;
            case ECLIPSE:
                weatherLabel.setText("Eclipse");
                weatherDsc.setText("Your mages are empowered but your warriors' " +
                        "vision is hindered");
                break;
            case FOG:
                weatherLabel.setText("Fog");
                weatherDsc.setText("Your mage's vision is concealed");
                break;
            case HEATWAVE:
                weatherLabel.setText("Heatwave");
                weatherDsc.setText("Your warrior's stamina is drained by the heat");
                break;
            case NICEBREEZE:
                weatherLabel.setText("Nice Breeze");
                weatherDsc.setText("The nice breeze cools your warriors but makes" +
                        " arrows miss their mark");
                break;
            case RAIN:
                weatherLabel.setText("Rain");
                weatherDsc.setText(
                        "The rain and mud slows all, helping archers' aim");
                break;
            case WIND:
                weatherLabel.setText("Wind");
                weatherDsc.setText(
                        "The wind makes arrows fly far from their intended target");
                break;
            default:
                condition = PlayModel.Weather.CLEAR;
                break;
        }
    }

    /**
     * 允许根据需要直接修改标签文本
     *
     * @param label 标签对象
     * @param str 要修改的文本
     */
    public void setLabelText(JLabel label, String str)
    {
        label.setText(str);
    }

    /**
     * 允许controller将model的power传递给view的label
     *
     * @param player1 玩家1的power
     * @param player2 玩家2的power
     */
    public void setPower(int player1, int player2)
    {
        p1Power.setText(Integer.toString(player1));
        p2Power.setText(Integer.toString(player2));
    }

    /**
     * 允许controller将model的score传递给view的label
     *
     * @param player1 玩家1的score
     * @param player2 玩家2的score
     */
    public void setScore(int player1, int player2)
    {
        p1Score.setText(Integer.toString(player1));
        p2Score.setText(Integer.toString(player2));
    }

    /**
     * 在对手的手牌面板上用图标包装，代表实际trading cards
     * 玩家看不到的对象
     */
    public void fillCPUHand()
    {
        p2Hand.removeAll();
        for (int i = 0; i < PlayModel.HAND_SIZE; ++i)
        {
            opponentCards[i] = new JLabel();
            opponentCards[i].setSize(108, 200);
            ImageIcon icon = new ImageIcon("res/image/UnknownHand.jpg");
            icon = new ImageIcon(icon.getImage().getScaledInstance(108, 200, Image.SCALE_DEFAULT));
            opponentCards[i].setIcon(icon);
            p2Hand.add(opponentCards[i]);
        }
    }

    /**
     * 重设面板，先清空
     */
    public void clearBoard()
    {
        p1Melee.removeAll();
        p1Ranged.removeAll();
        p1Magic.removeAll();
        p2Melee.removeAll();
        p2Ranged.removeAll();
        p2Magic.removeAll();
        p1Magic.repaint();
        p1Magic.setVisible(true);
        p1Melee.repaint();
        p1Melee.setVisible(true);
        p1Ranged.repaint();
        p1Ranged.setVisible(true);
        p2Magic.repaint();
        //p2Magic.setVisible(true);
        p2Melee.repaint();
        //p2Melee.setVisible(true);
        p2Ranged.repaint();
        //p2Ranged.setVisible(true);

    }


    /**
     * 如果确定用户获胜，则显示文本"you won"
     */
    public void winScreen()
    {
        p1Hand.removeAll();
        JLabel win = new JLabel("You Won", JLabel.CENTER);
        win.setFont(new Font("Serif", Font.BOLD, 150));
        win.setBackground(Color.GREEN);
        p1Hand.add(win);
    }

    /**
     * 显示文本 "You Lose :("
     * 如果确定用户输了
     */
    public void loseScreen()
    {
        p1Hand.removeAll();
        JLabel lose = new JLabel("You Lost", JLabel.CENTER);
        lose.setFont(new Font("Serif", Font.BOLD, 150));
        lose.setBackground(Color.RED);
        p1Hand.add(lose);
    }


    /**
     * 此主要方法仅用于测试布局更改，而不是主要方法入口
     * 运行它将显示一个带有占位符的空窗口label
     *
     * @param args 参数
     */
    public static void main(String[] args)
    {
        // 专用的设置

        try
        {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e)
        {
            // handle exception
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            // handle exception
            e.printStackTrace();
        }
        catch (InstantiationException e)
        {
            // handle exception
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            // handle exception
            e.printStackTrace();
        }
        PlayView view = new PlayView();
    }
}
