/**
 * TCDeck.java
 * 牌库，在游戏开始时为双方生成牌库
 * 之后的牌都从牌库中抽取
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class TCDeck
{
    // Members
    private ArrayList<TradingCard> deck = new ArrayList<>();
    private static ArrayList<TradingCard> cardList;
    private static ArrayList<WeatherCard> WeathercardList = new ArrayList<>();
    private static final int DECK_SIZE = 60;
    private boolean initialized = false;

    // Methods

    /**
     * 生成新牌库
     * 默认调用defaultDeck()函数
     */
    public TCDeck()
    {
        cardList = new ArrayList<>();

        createCards();
        defaultDeck();
    }

    /**
     * 非默认构造
     * 如果传入random则调用次构造函数
     *
     * @param random 是否random牌库
     */
    public TCDeck(boolean random)
    {
        cardList = new ArrayList<>();
        //if (!initialized)
        createCards();
        if (random)
            randomDeck();
        else
            defaultDeck();
    }

    /**
     * 随机生成一张普通卡牌
     *
     * @return 一张普通卡牌
     */
    public static TradingCard createRandomTradingCard()
    {
        int randID = (int) (Math.random() * CardStats.TradingCardNum);
        TradingCardStats stats = CardStats.getStatsByID(randID);
        return new TradingCard(stats.power, stats.cardName, stats.type.toString());
    }

    /**
     * 使用CardList.txt来生成卡牌到cardList
     */
    public void createCards()
    {
        for (int i = 0; i < DECK_SIZE; i++)
        {
            cardList.add(createRandomTradingCard());
        }

        for (int i = 0; i < 2; i++)
        {
            WeathercardList.add(new WeatherCard("CLEAR"));
            WeathercardList.add(new WeatherCard("ECLIPSE"));
            WeathercardList.add(new WeatherCard("FOG"));
            WeathercardList.add(new WeatherCard("HEATWAVE"));
            WeathercardList.add(new WeatherCard("NICEBREEZE"));
            WeathercardList.add(new WeatherCard("RAIN"));
            WeathercardList.add(new WeatherCard("WIND"));
        }
        initialized = true;
    }

    /**
     * 将cardList复制一份到牌库
     */
    public void defaultDeck()
    {
        deck = new ArrayList<>(cardList);
    }

    /**
     * 将cardList随机5张牌到牌库
     */
    public void randomDeck()
    {
        for (int i = 0; i < DECK_SIZE; ++i)
        {
            int rand = (int) (Math.random() * cardList.size());
            deck.add(cardList.get(rand));
        }
    }

    /**
     * 使用 Collections.shuffle() 方法来刷新牌库
     */
    public void shuffle()
    {
        Collections.shuffle(deck);
    }

    /**
     * 移除牌库第一张牌
     *
     * @return 要移除的牌
     */
    public TradingCard drawCard()
    {
        return deck.remove(0);
    }

    /**
     * 获得一张天气牌
     *
     * @return 一张天气牌
     */
    public WeatherCard getWeatherCard()
    {
        Random t = new Random();
        int i = t.nextInt(WeathercardList.size());
        WeatherCard it = new WeatherCard(WeathercardList.get(i));
        return it;
    }

    /**
     * debug方法
     *
     * @param args 命令行参数
     */
    public static void main(String[] args)
    {
        TCDeck test = new TCDeck(false);
        TCDeck test2 = new TCDeck(true);
        for (int i = 0; i < cardList.size(); i++)
        {
            test.deck.get(i).print();
        }

        test.shuffle();
        for (int i = 0; i < cardList.size(); i++)
        {
            test.deck.get(i).print();
        }

        for (int i = 0; i < cardList.size(); i++)
        {
            test2.deck.get(i).print();
        }

        test2.shuffle();
        for (int i = 0; i < cardList.size(); i++)
        {
            test2.deck.get(i).print();
        }
    }
}
