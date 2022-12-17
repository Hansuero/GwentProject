import javax.swing.*;
import java.nio.channels.ClosedSelectorException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Mike Limpus
 * CST 338 Final Project
 * PlayModel.java
 * Handle all of the game calculations and data
 */

public class PlayModel
{
    // Members
    public TCDeck p1Deck = new TCDeck();
    public TCDeck p2Deck = new TCDeck(true);
    public static int p1Score = 0, p2Score = 0;
    public static final int HAND_SIZE = 6, BOARD_SIZE = 5;
    public int p1MeleePower, p1RangedPower, p1MagicPower, p1TotalPower,
            p2MeleePower, p2RangedPower, p2MagicPower, p2TotalPower;
    public JLabel[] p1Hand = new JLabel[100];
    public JLabel[] p2Hand = new JLabel[100];
    public ArrayList<TradingCard> p1MeleeBoard = new ArrayList<>();
    public ArrayList<TradingCard> p1RangedBoard = new ArrayList<>();
    public ArrayList<TradingCard> p1MagicBoard = new ArrayList<>();
    public ArrayList<TradingCard> p2MeleeBoard = new ArrayList<>();
    public ArrayList<TradingCard> p2RangedBoard = new ArrayList<>();
    public ArrayList<TradingCard> p2MagicBoard = new ArrayList<>();

    private static boolean initialized = false;
    private static int cpuTracker;

    /**
     * Weather conditions will affect different card types:
     * Clear - no change
     * Heatwave - melee nerf
     * Wind - ranged nerf
     * Fog - magic nerf
     * Eclipse - Magic buff, slight melee nerf
     * Nice Breeze - Melee buff, slight ranged nerf
     * Rain - Ranged buff, slight magic nerf
     */
    public enum Weather
    {
        CLEAR,
        HEATWAVE,
        WIND, FOG,
        ECLIPSE,
        NICEBREEZE,
        RAIN
    };

    // Methods
    public PlayModel()
    {
    }

    /**
     * The main game calculation, determines the entire power of any players'
     * side of the board based on the current weather condition, using the 3
     * rows calculated with calculateRowPower. Through the use of int
     * typecasting and intentionally vague user messages, the true calcaulations
     * will be intentionally somewhat obfuscated from the player. Only multiply/
     * divide are used to make power buffs and nerfs relative to the total row
     * power.
     *
     * @return total power of a turn
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
            case CLEAR:         // No change
                totalPower = meleePower + rangedPower + magicPower;
                break;
            case ECLIPSE:       // Magic * 2, Melee / 1.5
                totalPower =
                        (int) (meleePower / 1.5) + rangedPower + (magicPower * 2);
                break;
            case FOG:           // Magic / 2
                totalPower =
                        meleePower + rangedPower + (int) (magicPower / 2);
                break;
            case HEATWAVE:      // Melee / 2
                totalPower =
                        (int) (meleePower / 2) + rangedPower + magicPower;
                break;
            case NICEBREEZE:    // Melee * 2, Ranged / 1.5
                totalPower =
                        (meleePower * 2) + (int) (rangedPower / 1.5) + magicPower;
                break;
            case RAIN:          // Ranged * 2, Magic / 1.5
                totalPower =
                        meleePower + (rangedPower * 2) + (int) (magicPower / 1.5);
                break;
            case WIND:          // Ranged / 2
                totalPower =
                        meleePower + (int) (rangedPower / 2) + magicPower;
                break;
            default:
                condition = Weather.CLEAR;
                break;
        }
        return totalPower;
    }

    /**
     * Gets the power from each card in an arrayList and calculates the
     * cumilative power of all the TradingCards in the row
     *
     * @return power of that row of cards
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
     * Allocate memory for the arrays, shuffle the decks
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
     * Handles the cleanup needed to start a new round, and invokes
     * intializeGame on it's first call
     */
    public void roundStart()
    {
        // Initialize the board on the first round
        if (!initialized)
        {
            initializeGame();
        }
        // 'Discard the board' by resetting the arrays
        else
        {
            p1MeleeBoard.clear();
            p1RangedBoard.clear();
            p1MagicBoard.clear();
            p2MeleeBoard.clear();
            p2RangedBoard.clear();
            p2MagicBoard.clear();

        }
        // Draw cards
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
                    }
                    else if (tt == 1)
                    {
                        WeatherCard mid = new WeatherCard("ECLIPSE");
                        p1Hand[i] = mid;
                    }
                    else if (tt == 2)
                    {
                        WeatherCard mid = new WeatherCard("FOG");
                        p1Hand[i] = mid;
                    }
                    else if (tt == 3)
                    {
                        WeatherCard mid = new WeatherCard("HEATWAVE");
                        p1Hand[i] = mid;
                    }
                    else if (tt == 4)
                    {
                        WeatherCard mid = new WeatherCard("NICEBREEZE");
                        p1Hand[i] = mid;
                    }
                    else if (tt == 5)
                    {
                        WeatherCard mid = new WeatherCard("RAIN");
                        p1Hand[i] = mid;
                    }
                    else if (tt == 6)
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
     * The not-so-intelligent "AI" for the computer player. The computer will
     * default to playing their top card. Because this game implementation is
     * more luck based in the current implementation, this will ultimately be
     * trivial.
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
     * Accessors for neccessary members
     */
    public JLabel[] getP1Hand()
    {
        return p1Hand;
    }

    public TradingCard[] getP2Hand()
    {
        return (TradingCard[]) p2Hand;
    }

    public int getP1Power()
    {
        return p1TotalPower;
    }

    public int getP2Power()
    {
        return p2TotalPower;
    }

    public int getP1Score()
    {
        return p1Score;
    }

    public int getP2Score()
    {
        return p2Score;
    }
}