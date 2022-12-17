/**
 * Mike Limpus
 * CST 338 Final Project
 * PlayController.java
 * The final piece of the MVC puzzle. The controller uses instances of PlayModel
 * and PlayView to construct the game itself. It handles some game logic and
 * GUI layout, but only in instances where the Model and View directly need to
 * communicate. It is also responsible for all user input through mouse
 * listeners.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlayController
{
    // Members
    PlayView gameWindow = new PlayView();
    PlayModel gameModel = new PlayModel();
    public TradingCard[] hand1, hand2;
    public PlayModel.Weather currentWeather;
    public static int turnCount = 0, roundCount = 0;
    public static final int MAX_TURNS = (PlayModel.HAND_SIZE - 1);
    public static final int MAX_ROUNDS = 5;

    // Methods
    public PlayController()
    {
        gameModel.roundStart();
        drawHand();
        // Create the mouse listeners
        addMouseListeners();
    }

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

    public void drawHand()
    {
        for (int i = 0; i < PlayModel.HAND_SIZE; ++i)
        {
            gameWindow.p1Hand.add(gameModel.getP1Hand()[i]);
        }
    }

    public void discardHand()
    {
        gameWindow.p1Hand.removeAll();
    }

    public void game()
    {

        if (gameModel.getP1Score() > gameModel.getP2Score())
            gameWindow.winScreen();
        else
            gameWindow.loseScreen();


    }

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
        }
        else
        {



            gameWindow.setScore(gameModel.getP1Score(), gameModel.getP2Score());
            game();
        }
    }

    public void turn(TradingCard card)
    {
        if (card.getName().equals("Peasant"))
        {
            card.copyEnemyHighestPower(gameModel);
        }
        else if (card.getName().equals("Diseased Zombie"))
        {
            card.killEnemyRangedCard(gameModel, gameWindow);
        }
        else if (card.getName().equals("Magician Apprentice"))
        {
            card.doublePowerWhenHaveAMagic(gameModel);
        }
        playCard(card);

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
            }
            else if (power2 > power1)
            {
                gameModel.p2Score++;
            }
            discardHand();
            drawHand();
            roundCount++;
            round();
        }
    }

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

    public void clicked(TradingCard card)
    {
        turn(card);
    }

    public void clicked_w(WeatherCard card)
    {
        turn_w(card);
    }

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
        gameWindow.setWeather(currentWeather);
        for (int i = 0; i < PlayModel.HAND_SIZE; ++i)
        {
            if (card == gameModel.p1Hand[i])
                gameModel.p1Hand[i] = null;
        }
    }

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

    public void cpuPlay(TradingCard card)
    {

        if (card.getName().equals("Peasant"))
        {
            card.copyPlayerHighestPower(gameModel);
        }
        else if (card.getName().equals("Diseased Zombie"))
        {
            card.killPlayerRangedCard(gameModel, gameWindow);
        }
        else if (card.getName().equals("Magician Apprentice"))
        {
            card.doublePowerWhenHaveAMagicForAI(gameModel);
        }
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

        for (int i = 0; i < PlayModel.HAND_SIZE; ++i)
        {
            if (card == gameModel.p2Hand[i])
                gameModel.p2Hand[i] = null;
        }
    }
}
