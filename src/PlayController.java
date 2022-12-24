/**
 * PlayController.java
 * 主要功能是控制游戏行为，包括出牌，回合，判断胜负等。
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlayController
{
    // Members
    PlayView gameWindow;
    PlayModel gameModel;
    public TradingCard[] hand1, hand2;
    public PlayModel.Weather currentWeather;
    public static int turnCount = 0, roundCount = 0;
    public static final int MAX_TURNS = (PlayModel.HAND_SIZE - 1);
    public static final int MAX_ROUNDS = 5;

    /**
     * 初始化游戏，产生新的游戏窗口和运算模块，初始化回合，添加鼠标事件
     */
    public PlayController()
    {
        gameWindow = new PlayView();
        gameModel = new PlayModel();
        gameModel.roundStart();
        drawHand();
        // Create the mouse listeners
        addMouseListeners();
    }

    /**
     * 添加鼠标事件：点击手牌即为出牌，讲手中手牌移除，并放置在出牌区域
     */
    public void addMouseListeners()
    {
        for (int i = 0; i < PlayModel.HAND_SIZE; ++i)
        {
            gameModel.getP1Hand()[i].addMouseListener(new MouseListener()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    // Handle the view manip in here then pass to clicked() for
                    // Game manipulation
                    try
                    {
                        TradingCard selected = new TradingCard((TradingCard) e.getComponent());
                        clicked(selected);
                    } catch (Exception ee)
                    {
                        WeatherCard selected = new WeatherCard(((WeatherCard) e.getComponent()));
                        clicked_w(selected);
                    }
                    e.getComponent().setVisible(false);
                }

                @Override
                public void mousePressed(MouseEvent e)
                {
                }

                @Override
                public void mouseReleased(MouseEvent e)
                {
                }

                @Override
                public void mouseEntered(MouseEvent e)
                {
                }

                @Override
                public void mouseExited(MouseEvent e)
                {
                }

            });
        }
    }

    /**
     * 将手牌数据提交至窗口hand Panel
     */
    public void drawHand()
    {
        for (int i = 0; i < PlayModel.HAND_SIZE; ++i)
        {
            gameWindow.p1Hand.add(gameModel.getP1Hand()[i]);
        }
    }

    /**
     * 移除手中全部手牌，当出牌数量达到回合最大值时调用
     */
    public void discardHand()
    {
        gameWindow.p1Hand.removeAll();
    }

    /**
     * 游戏结算，根据分数决定胜者
     * replay按钮，点击则重新开始一局游戏
     */
    public void game()
    {
        if (gameModel.getP1Score() > gameModel.getP2Score())
            gameWindow.winScreen();
        else
            gameWindow.loseScreen();
        JPanel gameOverPanel = new JPanel();
        gameOverPanel.setBackground(Color.CYAN);
        JLabel winOrLose;
        if (gameModel.getP1Score() > gameModel.getP2Score())
            winOrLose = new JLabel("YOU WIN");
        else
            winOrLose = new JLabel("YOU LOSE");
        winOrLose.setFont(new Font("Arial", Font.BOLD, 75));

        JButton playAgainButton = new JButton("Replay");
        playAgainButton.setFont(new Font("Arial", Font.PLAIN, 20));
        playAgainButton.setSize(200, 80);
        playAgainButton.addActionListener(new ActionListener()
        {
            @Override
        public void actionPerformed(ActionEvent e)
            {
                replay();
            }
        });

        gameOverPanel.setLayout(null);
        gameOverPanel.add(winOrLose);
        winOrLose.setSize(500, 300);
        winOrLose.setFont(new Font("Arial", Font.BOLD, 75));
        winOrLose.setVisible(true);
        winOrLose.setLocation(500, 0);
        gameOverPanel.add(playAgainButton);
        playAgainButton.setLocation(580, 300);
        gameWindow.setContentPane(gameOverPanel);
        gameOverPanel.repaint();
    }

    /**
     * 在每个回合开始时调用，初始化回合数据
     */
    public void round()
    {
        if (roundCount < MAX_ROUNDS)
        {
            // Zero out everything for the new round
            turnCount = 0;
            gameModel.p1TotalPower = 0;
            gameModel.p2TotalPower = 0;
            gameWindow.setPower(0, 0);
            gameModel.roundStart();
            gameWindow.clearBoard();
            addMouseListeners();
            gameWindow.fillCPUHand();
            currentWeather = gameModel.weatherRoll(0);
            gameWindow.setWeather(currentWeather);
            gameWindow.setScore(gameModel.getP1Score(), gameModel.getP2Score());
        } else
        {
            gameWindow.setScore(gameModel.getP1Score(), gameModel.getP2Score());
            game();
        }
    }

    /**
     * 出牌（普通卡牌），同时释放特殊能力，
     *
     * @param card 要出的卡牌（被点击）
     */
    public void turn(TradingCard card)
    {
        if (card.getName().equals("Peasant"))
        {
            card.copyEnemyHighestPower(gameModel);
        } else if (card.getName().equals("Diseased Zombie"))
        {
            card.killEnemyRangedCard(gameModel, gameWindow);
        } else if (card.getName().equals("Magician Apprentice"))
        {
            card.doublePowerWhenHaveAMagic(gameModel);
        }
        //放置卡牌置出牌区
        playCard(card);
        //电脑出牌（放置卡牌至出牌区
        cpuPlay(gameModel.cpuPlay());
        //根据场上的卡牌计算各自的power并更新
        int power1 = gameModel.calculatePower(currentWeather,
                gameModel.p1MeleeBoard, gameModel.p1RangedBoard, gameModel.p1MagicBoard);
        int power2 = gameModel.calculatePower(currentWeather,
                gameModel.p2MeleeBoard, gameModel.p2RangedBoard, gameModel.p2MagicBoard);
        gameWindow.setPower(power1, power2);
        gameModel.p2TotalPower = 0;
        gameModel.p1TotalPower = 0;
        turnCount++;
        //如果达到最大出牌数就结束当前回合，开始新回合
        if (turnCount == MAX_TURNS)
        {
            if (power1 > power2)
            {
                gameModel.p1Score++;
            } else if (power2 > power1)
            {
                gameModel.p2Score++;
            }
            discardHand();
            drawHand();
            roundCount++;
            round();
        }
    }

    /**
     * 针对天气牌的出牌
     *
     * @param card 要出的天气牌
     */
    public void turn_w(WeatherCard card)
    {
        playCard_w(card);
        cpuPlay(gameModel.cpuPlay());

        int power1 = gameModel.calculatePower(currentWeather,
                gameModel.p1MeleeBoard, gameModel.p1RangedBoard, gameModel.p1MagicBoard);
        int power2 = gameModel.calculatePower(currentWeather,
                gameModel.p2MeleeBoard, gameModel.p2RangedBoard, gameModel.p2MagicBoard);
        gameWindow.setPower(power1, power2);

        gameModel.p2TotalPower = 0;
        gameModel.p1TotalPower = 0;
        turnCount++;
        if (turnCount == MAX_TURNS)
        {
            if (power1 > power2)
            {
                gameModel.p1Score++;
            } else if (power2 > power1)
            {
                gameModel.p2Score++;
            }
            discardHand();
            drawHand();
            roundCount++;
            round();
        }
    }

    /**
     * 点击时调用，同时调用turn函数
     *
     * @param card 点击的卡牌
     */
    public void clicked(TradingCard card)
    {
        turn(card);
    }

    /**
     * 针对天气牌的点击
     *
     * @param card 点击的天气牌
     */
    public void clicked_w(WeatherCard card)
    {
        turn_w(card);
    }

    /**
     * 天气牌发挥作用，改变场上的天气
     *
     * @param card 即将发挥作用的天气牌
     */
    public void playCard_w(WeatherCard card)
    {
        switch (card.getWeatherType())
        {
            case CLEAR:
                currentWeather = gameModel.weatherRoll(0);
                break;
            case ECLIPSE:
                currentWeather = gameModel.weatherRoll(1);
                break;
            case FOG:
                currentWeather = gameModel.weatherRoll(2);
                break;
            case HEATWAVE:
                currentWeather = gameModel.weatherRoll(3);
                break;
            case NICEBREEZE:
                currentWeather = gameModel.weatherRoll(4);
                break;
            case RAIN:
                currentWeather = gameModel.weatherRoll(5);
                break;
            case WIND:
                currentWeather = gameModel.weatherRoll(6);
                break;
            default:
                break;
        }
        //使改动生效
        gameWindow.setWeather(currentWeather);
        //使该天气牌从手牌中移除
        for (int i = 0; i < PlayModel.HAND_SIZE; ++i)
        {
            if (card == gameModel.p1Hand[i])
                gameModel.p1Hand[i] = null;
        }
    }

    /**
     * 打出普通牌（将卡牌放进出牌区）
     *
     * @param card 将要放置的卡牌
     */
    public void playCard(TradingCard card)
    {
        switch (card.getType())
        {
            case DEBUG:
                break;
            case MAGIC:
                gameWindow.p1Magic.add(card);
                gameModel.p1MagicBoard.add(card);
                break;
            case MELEE:
                gameWindow.p1Melee.add(card);
                gameModel.p1MeleeBoard.add(card);
                break;
            case RANGED:
                gameWindow.p1Ranged.add(card);
                gameModel.p1RangedBoard.add(card);
                break;
            default:
                break;
        }
        for (int i = 0; i < PlayModel.HAND_SIZE; ++i)
        {
            if (card == gameModel.p1Hand[i])
                gameModel.p1Hand[i] = null;
        }
    }

    /**
     * 人机出牌（也会施展对应特殊能力）
     *
     * @param card 人机将要出的牌
     */
    public void cpuPlay(TradingCard card)
    {

        //农民特殊能力
        if (card.getName().equals("Peasant"))
        {
            card.copyPlayerHighestPower(gameModel);
        }
        //僵尸特殊能力
        else if (card.getName().equals("Diseased Zombie"))
        {
            card.killPlayerRangedCard(gameModel, gameWindow);
        }
        //魔法学徒特殊能力
        else if (card.getName().equals("Magician Apprentice"))
        {
            card.doublePowerWhenHaveAMagicForAI(gameModel);
        }
        //放置卡牌
        switch (card.getType())
        {
            case DEBUG:
                break;
            case MAGIC:
                gameWindow.p2Magic.add(card);
                gameModel.p2MagicBoard.add(card);
                break;
            case MELEE:
                gameWindow.p2Melee.add(card);
                gameModel.p2MeleeBoard.add(card);
                break;
            case RANGED:
                gameWindow.p2Ranged.add(card);
                gameModel.p2RangedBoard.add(card);
                break;
            default:
                break;
        }
        //清除对应手中卡牌
        for (int i = 0; i < PlayModel.HAND_SIZE; ++i)
        {
            if (card == gameModel.p2Hand[i])
                gameModel.p2Hand[i] = null;
        }
    }

    /**
     * 再来一局，重置出牌数和回合数，关闭旧窗口，创建新窗口和其他组件
     */
    public void replay()
    {
        turnCount = roundCount = 0;
        gameWindow.dispose();
        gameWindow = new PlayView();
        gameModel = new PlayModel();
        gameModel.roundStart();
        drawHand();
        // Create the mouse listeners
        addMouseListeners();
        round();
    }
}

