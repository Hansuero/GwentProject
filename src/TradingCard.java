import javax.swing.*;
import java.awt.*;

public class TradingCard extends JLabel
{
    // Members
    public enum CardType
    {MELEE, RANGED, MAGIC, DEBUG}
    private boolean hasAbility;
    ;
    private int power;
    private String name;
    private CardType type;
    private boolean isWeather;

    // Methods

    /**
     * Default constructor creates a useless card
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
     * Create a new card if you have access to the CardType enum
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
     * Create a new card using a String for its type
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
        } else if (type.contains("RANGED"))
        {
            this.type = CardType.RANGED;
        } else if (type.contains("MAGIC"))
        {
            this.type = CardType.MAGIC;
        } else
        {
            this.type = CardType.DEBUG;
        }
    }

    /**
     * Copy Constructor for TradingCard
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
     * Accessor for power
     */
    public int getPower()
    {
        return power;
    }

    /**
     * Accessor for name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Accessor for type
     */
    public CardType getType()
    {
        return type;
    }

    public void print()
    {
        System.out.println(name + " " + type + " " + power);
    }

    /**
     * ID:[0]农民能力：复制对方已出卡牌中最高的点数
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
}




