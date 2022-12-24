/**
 * PlayModel.java
 * 负责游戏的数据保存和处理，计算分数
 */

import javax.swing.*;
import java.nio.channels.ClosedSelectorException;
import java.util.ArrayList;
import java.util.Random;

public class PlayModel
{
    // Members
    public TCDeck p1Deck;
    public TCDeck p2Deck;
    public static int p1Score, p2Score;
    public static final int HAND_SIZE = 6;
    public static final int BOARD_SIZE = 5;
    public int p1MeleePower, p1RangedPower, p1MagicPower, p1TotalPower,
            p2MeleePower, p2RangedPower, p2MagicPower, p2TotalPower;
    public JLabel[] p1Hand = new JLabel[100];
    public JLabel[] p2Hand = new JLabel[100];
    public ArrayList<TradingCard> p1MeleeBoard;
    public ArrayList<TradingCard> p1RangedBoard;
    public ArrayList<TradingCard> p1MagicBoard;
    public ArrayList<TradingCard> p2MeleeBoard;
    public ArrayList<TradingCard> p2RangedBoard;
    public ArrayList<TradingCard> p2MagicBoard;

    private static boolean initialized;
    private static int cpuTracker;

    /**
     * 天气设定:不同的天气会对场上不同位置卡牌点数产生影响
     * Clear - 无影响
     * Heatwave -削弱 Melee
     * Wind - 削弱 Ranged
     * Fog - 削弱 Magic
     * Eclipse - 增强 Magic, 轻度削弱 Melee
     * Nice Breeze - 增强 Melee, 轻度削弱 Ranged
     * Rain - 增强 Ranged, 轻度削弱 Magic
     */
    public enum Weather
    {
        CLEAR,
        HEATWAVE,
        WIND,
        FOG,
        ECLIPSE,
        NICEBREEZE,
        RAIN
    }

    ;

    /**
     * 刷新分数，补充牌库
     */
    public PlayModel()
    {
        p1Score = p2Score = 0;

        p1Deck = new TCDeck();
        p2Deck = new TCDeck(true);
        p1Deck.defaultDeck();
        p2Deck.defaultDeck();

        initialized = false;

        p1MagicBoard = new ArrayList<>();
        p1MeleeBoard = new ArrayList<>();
        p1RangedBoard = new ArrayList<>();
        p2MeleeBoard = new ArrayList<>();
        p2MagicBoard = new ArrayList<>();
        p2RangedBoard = new ArrayList<>();
    }

    /**
     * 游戏的主要计算, 计算某方出牌的power总和
     * 具体计算方式为，首先分别计算出三行的power和
     * 接着根据场上的天气，计算削弱和增强，更新受影响行的power
     * 将三行的power相加，得到该方的power总和并返回
     *
     * @return 出牌后己方场上的总power
     */
    public int calculatePower(Weather condition, ArrayList<TradingCard> meleeRow,
                              ArrayList<TradingCard> rangedRow, ArrayList<TradingCard> magicRow)
    {
        int totalPower = 0;
        int meleePower = calculateRowPower(meleeRow);
        int rangedPower = calculateRowPower(rangedRow);
        int magicPower = calculateRowPower(magicRow);
        switch (condition)
        {
            case CLEAR:
                // No Change
                totalPower = meleePower + rangedPower + magicPower;
                break;
            case ECLIPSE:
                // Magic * 2, Melee / 1.5
                totalPower =
                        (int) (meleePower / 1.5) + rangedPower + (magicPower * 2);
                break;
            case FOG:
                // Magic / 2
                totalPower =
                        meleePower + rangedPower + (int) (magicPower / 2);
                break;
            case HEATWAVE:
                // Melee / 2
                totalPower =
                        (int) (meleePower / 2) + rangedPower + magicPower;
                break;
            case NICEBREEZE:
                // Melee * 2, Ranged / 1.5
                totalPower =
                        (meleePower * 2) + (int) (rangedPower / 1.5) + magicPower;
                break;
            case RAIN:
                // Ranged * 2, Magic / 1.5
                totalPower =
                        meleePower + (rangedPower * 2) + (int) (magicPower / 1.5);
                break;
            case WIND:
                // Ranged / 2
                totalPower =
                        meleePower + (int) (rangedPower / 2) + magicPower;
                break;
            default:
                // No Change
                condition = Weather.CLEAR;
                break;
        }
        return totalPower;
    }

    /**
     * 计算某一行的power和并返回
     * 方法：遍历该行每张牌并加到power中
     *
     * @return 该行的power和
     */
    public int calculateRowPower(ArrayList<TradingCard> row)
    {
        int power = 0;
        for (int i = 0; i < row.size(); ++i)
        {
            power += row.get(i).getPower();
        }
        return power;
    }

    /**
     * 为Hand申请空间
     * shuffle牌库
     */
    public void initializeGame()
    {

        p1Hand = new JLabel[HAND_SIZE];
        p2Hand = new JLabel[HAND_SIZE];

        p1Deck.shuffle();
        p2Deck.shuffle();
        initialized = true;
    }

    /**
     * 开始新回合的处理
     * 第一次调用时初始化游戏
     */
    public void roundStart()
    {
        // 第一回合先初始化游戏
        if (!initialized)
        {
            initializeGame();
        }
        // 重设board，即清空数组
        else
        {
            p1MeleeBoard.clear();
            p1RangedBoard.clear();
            p1MagicBoard.clear();
            p2MeleeBoard.clear();
            p2RangedBoard.clear();
            p2MagicBoard.clear();

        }
        // 初始化手牌
        for (int i = 0; i < HAND_SIZE; ++i)
        {
            if (i == 5)
            {
                Random r = new Random();
                int j = r.nextInt(2);
                if (j == 1)
                {
                    int tt;
                    tt = r.nextInt(7);
                    if (tt == 0)
                    {
                        WeatherCard mid = new WeatherCard("CLEAR");
                        p1Hand[i] = mid;
                    } else if (tt == 1)
                    {
                        WeatherCard mid = new WeatherCard("ECLIPSE");
                        p1Hand[i] = mid;
                    } else if (tt == 2)
                    {
                        WeatherCard mid = new WeatherCard("FOG");
                        p1Hand[i] = mid;
                    } else if (tt == 3)
                    {
                        WeatherCard mid = new WeatherCard("HEATWAVE");
                        p1Hand[i] = mid;
                    } else if (tt == 4)
                    {
                        WeatherCard mid = new WeatherCard("NICEBREEZE");
                        p1Hand[i] = mid;
                    } else if (tt == 5)
                    {
                        WeatherCard mid = new WeatherCard("RAIN");
                        p1Hand[i] = mid;
                    } else if (tt == 6)
                    {
                        WeatherCard mid = new WeatherCard("WIND");
                        p1Hand[i] = mid;
                    }
                    continue;

                }

            }
            p1Hand[i] = p1Deck.drawCard();
            p2Hand[i] = p2Deck.drawCard();
        }
        cpuTracker = 0;
    }

    /**
     * AI的出牌
     * 不是很聪明的AI，它会出手中的第一张牌。
     * 考虑到这可能会有些无趣，将来会改善这个AI
     *
     * @return the "leftmost" card in p2Hand
     */
    public TradingCard cpuPlay()
    {
        TradingCard tempCard = new TradingCard((TradingCard) p2Hand[cpuTracker]);
        p2Hand[cpuTracker] = null;
        cpuTracker++;
        return tempCard;
    }

    /**
     * 根据传进来的参数返回对应id的天气
     *
     * @param random 要返回的天气类型所代表的id
     * @return 一个根据参数返回的天气
     */
    public Weather weatherRoll(int random)
    {
        switch (random)
        {
            case 0:
                return Weather.CLEAR;
            case 1:
                return Weather.ECLIPSE;
            case 2:
                return Weather.FOG;
            case 3:
                return Weather.HEATWAVE;
            case 4:
                return Weather.NICEBREEZE;
            case 5:
                return Weather.RAIN;
            case 6:
                return Weather.WIND;
            default:
                return Weather.CLEAR;
        }
    }

    /**
     * 返回p1手牌的JLabel
     *
     * @return p1手牌的JLabel数组
     */
    public JLabel[] getP1Hand()
    {
        return p1Hand;
    }

    /**
     * 返回p2手牌的Card数组
     *
     * @return p2手牌的Card数组
     */
    public TradingCard[] getP2Hand()
    {
        return (TradingCard[]) p2Hand;
    }

    /**
     * 返回p1的Power
     *
     * @return p1的Power
     */
    public int getP1Power()
    {
        return p1TotalPower;
    }

    /**
     * 返回p2的Power
     *
     * @return p2的Power
     */
    public int getP2Power()
    {
        return p2TotalPower;
    }

    /**
     * 返回p1的分数
     *
     * @return p1的分数
     */
    public int getP1Score()
    {
        return p1Score;
    }

    /**
     * 返回p2的分数
     *
     * @return p2的分数
     */
    public int getP2Score()
    {
        return p2Score;
    }
}