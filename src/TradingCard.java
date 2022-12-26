/**
 * TradingCard.java
 * 普通卡牌的相关属性
 * 和天气牌独立
 */

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class TradingCard extends JLabel
{
    // Members
    public enum CardType
    {
        MELEE,
        RANGED,
        MAGIC,
        DEBUG
    }

    private boolean hasAbility;
    private int power;
    private String name;
    private CardType type;
    private boolean isWeather;

    // Methods

    /**
     * 这是Default constructor， 创建一张空白的无用卡
     */
    TradingCard()
    {
        super();
        this.isWeather = false;
        this.setSize(50, 60);
        ImageIcon icon = new ImageIcon("res/image/Empty.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT));
        this.setIcon(icon);
        power = 0;
        name = "Default Card";
        type = CardType.DEBUG;
    }

    /**
     * 已知卡牌类型的前提下对卡牌创建，卡牌类型用CardType类表示
     *
     * @param power 设想的点数
     * @param name  名字
     * @param type  卡牌类型
     */
    TradingCard(int power, String name, CardType type)
    {
        super();
        this.isWeather = false;
        this.setSize(108, 200);
        ImageIcon icon = new ImageIcon("res/image/" + name + ".jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT));
        this.setIcon(icon);
        this.power = power;
        this.name = name;
        this.type = type;
    }

    /**
     * 已知卡牌类型的前提下对卡牌创建，卡牌类型用String类型表示
     *
     * @param power 设想的点数
     * @param name  名字
     * @param type  卡牌类型
     */
    TradingCard(int power, String name, String type)
    {
        super();
        this.isWeather = false;
        this.setSize(108, 200);
        ImageIcon icon = new ImageIcon("res/image/" + name + ".jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT));
        this.setIcon(icon);
        this.power = power;
        this.name = name;
        if (type.contains("MELEE"))
        {
            this.type = CardType.MELEE;
        }
        else if (type.contains("RANGED"))
        {
            this.type = CardType.RANGED;
        }
        else if (type.contains("MAGIC"))
        {
            this.type = CardType.MAGIC;
        }
        else
        {
            this.type = CardType.DEBUG;
        }
    }

    /**
     * 对原有卡牌进行复制的构造
     *
     * @param card 要复制的牌
     */
    TradingCard(TradingCard card)
    {
        super();
        this.isWeather = false;
        this.setSize(56, 85);
        ImageIcon icon = new ImageIcon("res/image/" + card.name + ".jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT));
        this.setIcon(icon);
        this.power = card.power;
        this.name = card.name;
        this.type = card.type;
    }

    /**
     * 获取卡牌点数
     *
     * @return 卡牌点数
     */
    public int getPower()
    {
        return power;
    }

    /**
     * 获取卡牌名字
     *
     * @return 卡牌名字
     */
    public String getName()
    {
        return name;
    }

    /**
     * 获取卡牌类型
     *
     * @return 卡牌类型
     */
    public CardType getType()
    {
        return type;
    }

    /**
     * 打印卡牌类型
     */
    public void print()
    {
        System.out.println(name + " " + type + " " + power);
    }

    /**
     * ID:[0]农民能力：复制对方已出卡牌中最高的点数
     *
     * @param gameModel
     */
    public void copyEnemyHighestPower(PlayModel gameModel)
    {
        int highestPower = 2;
        for (TradingCard card : gameModel.p2MagicBoard)
        {
            if (card.getPower() > highestPower)
            {
                highestPower = card.getPower();
            }
        }
        for (TradingCard card : gameModel.p2MeleeBoard)
        {
            if (card.getPower() > highestPower)
            {
                highestPower = card.getPower();
            }
        }
        for (TradingCard card : gameModel.p2RangedBoard)
        {
            if (card.getPower() > highestPower)
            {
                highestPower = card.getPower();
            }
        }
        this.power = highestPower;
        ImageIcon icon = new ImageIcon("res/image/" + name + power + ".jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT));
        this.setIcon(icon);

    }

    /**
     * ID:[0]农民能力：复制对方已出卡牌中最高的点数(AI)
     *
     * @param gameModel
     */
    public void copyPlayerHighestPower(PlayModel gameModel)
    {
        int highestPower = 2;
        for (TradingCard card : gameModel.p1MagicBoard)
        {
            if (card.getPower() > highestPower)
            {
                highestPower = card.getPower();
            }
        }
        for (TradingCard card : gameModel.p1MeleeBoard)
        {
            if (card.getPower() > highestPower)
            {
                highestPower = card.getPower();
            }
        }
        for (TradingCard card : gameModel.p1RangedBoard)
        {
            if (card.getPower() > highestPower)
            {
                highestPower = card.getPower();
            }
        }
        this.power = highestPower;
        ImageIcon icon = new ImageIcon("res/image/" + name + power + ".jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT));
        this.setIcon(icon);

    }

    /**
     * ID:[5]僵尸能力：
     *
     * @param gameModel
     * @param gameWindow
     */
    public void killEnemyRangedCard(PlayModel gameModel, PlayView gameWindow)
    {
        gameModel.p2RangedBoard.clear();
        gameWindow.p2Ranged.removeAll();
        gameWindow.p2Ranged.repaint();
    }

    /**
     * ID:[5]僵尸能力：杀死对方ranged行所有的牌(AI)
     *
     * @param gameModel
     * @param gameWindow
     */
    public void killPlayerRangedCard(PlayModel gameModel, PlayView gameWindow)
    {
        gameModel.p1RangedBoard.clear();
        gameWindow.p1Ranged.removeAll();
        gameWindow.p1Ranged.repaint();
    }

    /**
     * ID:[10]魔法学徒能力：己方每有一个magic，自身点数加倍
     *
     * @param gameModel
     */
    public void doublePowerWhenHaveAMagic(PlayModel gameModel)
    {
        int magicCount = 0;
        for (TradingCard card : gameModel.p1MagicBoard)
        {
            magicCount++;
        }
        int doubledPower = (int) Math.pow(2, magicCount + 1);
        this.power = doubledPower;
        ImageIcon icon = new ImageIcon("res/image/" + name + power + ".jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT));
        this.setIcon(icon);
    }

    /**
     * ID:[10]魔法学徒能力：己方每有一个magic，自身点数加倍(AI)
     *
     * @param gameModel
     */
    public void doublePowerWhenHaveAMagicForAI(PlayModel gameModel)
    {
        int magicCount = 0;
        for (TradingCard card : gameModel.p2MagicBoard)
        {
            magicCount++;
        }
        int doubledPower = (int) Math.pow(2, magicCount + 1);
        this.power = doubledPower;
        ImageIcon icon = new ImageIcon("res/image/" + name + power + ".jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT));
        this.setIcon(icon);
    }

}

