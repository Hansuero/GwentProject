import javax.swing.*;
import java.awt.*;

public class TradingCard extends JLabel {
    // Members
    public enum CardType {MELEE, RANGED, MAGIC, DEBUG};
    private int power; 
    private String name;  
    private CardType type;  

    // Methods

    /**
     * Default constructor creates a useless card
     */
    TradingCard() {
        super();
        this.setSize(50,70);
        ImageIcon icon=new ImageIcon("res/image/Empty.jpg");
        icon=new ImageIcon(icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT));
        this.setIcon(icon);
        power = 0; 
        name = "Default Card";
        type = CardType.DEBUG;
    }

    /**
     * Create a new card if you have access to the CardType enum
     * @param power
     * @param name
     * @param type
     */
    TradingCard(int power, String name, CardType type) {
        super();
        this.setSize(50,70);
        ImageIcon icon=new ImageIcon("res/image/" + name + ".jpg");
        icon=new ImageIcon(icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT));
        this.setIcon(icon);
        this.power = power;
        this.name = name;
        this.type = type;
    }

    /**
     * Create a new card using a String for its type
     * @param power
     * @param name
     * @param type
     */
    TradingCard(int power, String name, String type) {
        super();
        this.setSize(50,70);
        ImageIcon icon=new ImageIcon("res/image/" + name + ".jpg");
        icon=new ImageIcon(icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT));
        this.setIcon(icon);
        this.power = power; 
        this.name = name; 
        if (type.contains("MELEE")) {
            this.type = CardType.MELEE;
        }
        else if (type.contains("RANGED")) {
            this.type = CardType.RANGED;
        }
        else if (type.contains("MAGIC")) {
            this.type = CardType.MAGIC;
        }
        else {
            this.type = CardType.DEBUG;
        }
    } 

    /**
     * Copy Constructor for TradingCard
     * @param card
     */
    TradingCard(TradingCard card) {
        super();
        this.setSize(50,70);
        ImageIcon icon=new ImageIcon("res/image/" + card.name + ".jpg");
        icon=new ImageIcon(icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT));
        this.setIcon(icon);
        this.power = card.power;
        this.name = card.name;
        this.type = card.type;
    }
    /**
     * Accessor for power
     * @return power
     */
    public int getPower() {
        return power;
    }

    /**
     * Accessor for name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor for type
     * @return type
     */
    public CardType getType() { 
        return type;
    }

    /**
     * Debug print function
     */
    public void print() {
        System.out.println(name + " " + type + " " + power);
    }
}
