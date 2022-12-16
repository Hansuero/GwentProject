import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Vector;

public class CardStats
{
    public static final int TradingCardNum = 15;

    private static Vector<TradingCardStats> tradingCardStatsVector = new Vector<>();

    public static TradingCardStats getStatsByID(int targetID)
    {
        for (TradingCardStats stats : tradingCardStatsVector)
        {
            if(stats.cardID == targetID)
            {
                return stats;
            }
        }
        return null;
    }

    public static void initStats()
    {
        //目前有15张卡，ID为0-14
        int id = 0;
        String name;
        String typeName;
        TradingCard.CardType type;
        int power;
        File file = new File("./res/CardList.txt");
        Scanner in = null;
        try
        {
            in = new Scanner(file);
        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        try
        {
            for (int i = 0; i < TradingCardNum; i++)
            {
                typeName = in.nextLine();
                power = Integer.parseInt(in.nextLine());
                name = in.nextLine();
                switch (typeName)
                {
                    case "MELEE":
                        type = TradingCard.CardType.MELEE;
                        break;
                    case "RANGED":
                        type = TradingCard.CardType.RANGED;
                        break;
                    case "MAGIC":
                        type = TradingCard.CardType.MAGIC;
                        break;
                    default:
                        type = TradingCard.CardType.DEBUG;
                        break;

                }
                tradingCardStatsVector.add(
                        new TradingCardStats(id, name, type, power));

                id++;

            }

        }
        catch(NoSuchElementException e)
        {
            //nothing
        }
        in.close();
    }

}
class TradingCardStats
{
    int cardID;
    String cardName;
    TradingCard.CardType type;
    int power;

    public TradingCardStats(int cardID, String cardName, TradingCard.CardType type, int power)
    {
        this.cardID = cardID;
        this.cardName = cardName;
        this.type = type;
        this.power = power;
    }
}
