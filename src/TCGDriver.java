/**
 * TCGDriver.java
 * Main program entry point, sets the look and feel of the application
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class TCGDriver
{
    public static void main(String[] args)
    {
        try
        {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException |
                 InstantiationException | IllegalAccessException e)
        {
            e.printStackTrace();
        }
        StartMenu startMenu = new StartMenu();
        JButton start = new JButton();
        ImageIcon icon = new ImageIcon("res/image/startbtn1.png");
        start.setIcon(icon);
        icon = new ImageIcon("res/image/startbtn2.png");
        start.setPressedIcon(icon);
        CardStats.initStats();
        start.addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                PlayController game = new PlayController();
                startMenu.setVisible(false);
                game.round();
            }
        });
        JButton rule = new JButton("规则");
        rule.setFont(new Font("Serif", Font.BOLD, 15));
        rule.setOpaque(false);
        rule.addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JFrame r = new JFrame("规则说明");
                JLabel text = new JLabel(
                        "<html>·本游戏是一个支持人机对战的卡牌游戏<br>" +
                                "·游戏的胜利规则是,在5个回合中赢得较多回合的人胜利<br>" +
                                "·每个回合开始时，双方各随机抽取6张手牌，其中可能有一张天气牌<br>" +
                                "·卡牌分为两种：普通卡牌TradingCard和天气牌WeatherCard<br>" +
                                "·普通卡牌：分为3种类型：Melee，Ranged， Magic；同时具备点数<br>" +
                                "·打出普通卡牌时，该卡牌会被放置在右侧面板中该类型的对应的行，并为自己增加数值等同于点数的power<br>" +
                                "·普通牌中有一定数量的具有特殊能力的牌，具有金色边框，基础点数均为2，打出后可以施展特殊能力<br>" +
                                "·农民(Peasant)：打出后复制对方已出牌中最高的点数<br>" +
                                "·僵尸(Diseased Zombie)：打出后消灭对方Ranged行中所有的牌<br>" +
                                "·魔法学徒(Magician Apprentice)：己方每有一个Magic(不包含自身)，点数加倍<br>" +
                                "·打出天气牌时，场上的天气会随之变化，对双方的power产生一定影响，不同天气的影响不同<br>" +
                                "·玩家每个回合固定出5张牌，出牌完毕后，power较高的一方赢得本回合胜利，然后开始下一回合的战斗<br>" +
                                "</html>"

                );
                r.setSize(1000, 500);
                r.setResizable(false);
                ImageIcon icon = new ImageIcon("res/image/Unknown.jpg");
                r.setIconImage(icon.getImage());
                text.setBounds(0, 0, r.getWidth(), r.getHeight());
                text.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                text.setOpaque(false);
                r.add(text);
                r.setVisible(true);
            }
        });
        startMenu.add(start);
        startMenu.add(rule);
        start.setBounds(startMenu.getWidth() / 2 - 75, 400, 150, 45);
        rule.setBounds(0, 0, 50, 50);
    }
}
