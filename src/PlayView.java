/**
 * Mike Limpus
 * CST 338 Final Project
 * PlayView.java
 * Creates the swing window and elements of the game board, as well as provide
 * methods that directly modify the window.
 */
import java.awt.*;
import javax.swing.*;

public class PlayView extends JFrame {
    // Members
    public JPanel leftPanel, rightPanel; 
    private static final String PROGRAM_NAME = "Card Combat";
    // Right side panels
    public JPanel p1Melee, p1Ranged, p1Magic, 
        p2Melee, p2Ranged, p2Magic, weatherPanel;
    // Left side panels
    public JPanel p1Hand, p2Hand, gameInfo, score;
    public JLabel p1Score, p2Score, p1Power, p2Power, weatherLabel, weatherDsc;
    // Array for the "card backs" that the opponent will have
    public JLabel[] opponentCards;
    
    

    public PlayView() {
        super(PROGRAM_NAME);
        this.setSize(1280, 1024);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Initialize the Jpanel and Jlabel objects
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        p1Melee = new JPanel();
        p1Ranged = new JPanel();
        p1Magic = new JPanel();
        p2Melee = new JPanel();
        p2Ranged = new JPanel();
        p2Magic = new JPanel();
        weatherPanel = new JPanel();
        p1Hand = new JPanel();
        p2Hand = new JPanel();
        gameInfo = new JPanel();
        score = new JPanel();
        // The following default messages will be replaced by the controller
        p1Score = new JLabel("Player 1 Score", JLabel.RIGHT);       
        p2Score = new JLabel("Player 2 Score", JLabel.LEFT);
        p1Power = new JLabel("Player 1 Power", JLabel.CENTER);
        p2Power = new JLabel("Player 2 Power", JLabel.CENTER);
        weatherLabel = new JLabel("Weather", JLabel.CENTER);
        weatherDsc = new JLabel("Weather info here", JLabel.CENTER);
        opponentCards = new JLabel[PlayModel.HAND_SIZE];

        // Add some layouts and borders, as well as some styles
        getContentPane().setLayout(new GridLayout(0,2));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder());
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder());
        p1Melee.setLayout(new FlowLayout());
        p1Melee.setPreferredSize(new Dimension(50,70));
        p1Melee.setBorder(BorderFactory.createTitledBorder("Melee"));
        p1Ranged.setLayout(new FlowLayout());
        p1Ranged.setPreferredSize(new Dimension(50,70));
        p1Ranged.setBorder(BorderFactory.createTitledBorder("Ranged"));
        p1Magic.setLayout(new FlowLayout());
        p1Magic.setPreferredSize(new Dimension(50,70));
        p1Magic.setBorder(BorderFactory.createTitledBorder("Magic"));
        p2Melee.setLayout(new FlowLayout());
        p2Melee.setBorder(BorderFactory.createTitledBorder("Melee"));
        p2Ranged.setLayout(new FlowLayout());
        p2Ranged.setBorder(BorderFactory.createTitledBorder("Ranged"));
        p2Magic.setLayout(new FlowLayout());
        p2Magic.setBorder(BorderFactory.createTitledBorder("Magic"));
        p1Hand.setLayout(new FlowLayout(PlayModel.HAND_SIZE));
        p1Hand.setBorder(BorderFactory.createTitledBorder("Hand"));
        p2Hand.setLayout(new FlowLayout(PlayModel.HAND_SIZE));
        p2Hand.setBorder(BorderFactory.createTitledBorder("Hand"));
        gameInfo.setLayout(new GridLayout(3,0));
        gameInfo.setBorder(BorderFactory.createEmptyBorder());
        weatherPanel.setLayout(new GridLayout(2,0));
        score.setLayout(new GridLayout(0,5));
        p1Score.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        p2Score.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        p1Power.setFont(new Font("Sans Serif", Font.PLAIN, 24));
        p2Power.setFont(new Font("Sans Serif", Font.PLAIN, 24));
        weatherLabel.setFont(new Font("Sans Serif", Font.PLAIN, 18));

        // Add everything
        this.add(leftPanel);
        this.add(rightPanel);
        leftPanel.add(p2Hand);
        leftPanel.add(gameInfo);
        gameInfo.add(score);
        score.add(new JLabel("Your Score", JLabel.CENTER));
        score.add(p1Score);
        score.add (new JLabel("-", JLabel.CENTER));
        score.add(p2Score);
        score.add(new JLabel("Opponent Score", JLabel.CENTER));
        leftPanel.add(p1Hand);
        rightPanel.add(p2Magic);
        rightPanel.add(p2Ranged);
        rightPanel.add(p2Melee);
        rightPanel.add(weatherPanel);
        weatherPanel.add(weatherLabel);
        weatherPanel.add(weatherDsc);
        rightPanel.add(p1Melee);
        rightPanel.add(p1Ranged);
        rightPanel.add(p1Magic);
        gameInfo.add(p2Power);
        gameInfo.add(score);
        gameInfo.add(p1Power);
        setVisible(true);
    }

    /**
     * Display the Weather conditions on the window, something of a "cousin" to 
     * PlayModel.calculatePower() for the graphical end.
     * @param condition
     */
    public void setWeather(PlayModel.Weather condition) {
        switch(condition) {
        case CLEAR:
            weatherLabel.setText("Clear");
            weatherDsc.setText("The clear weather bothers nobody");
            break;
        case ECLIPSE:
            weatherLabel.setText("Eclipse");
            weatherDsc.setText("Your mages are empowered but your warriors' " +
                "vision is hindered");
            break;
        case FOG:
            weatherLabel.setText("Fog");
            weatherDsc.setText("Your mage's vision is concealed");
            break;
        case HEATWAVE:
            weatherLabel.setText("Heatwave");
            weatherDsc.setText("Your warrior's stamina is drained by the heat");
            break;
        case NICEBREEZE:
            weatherLabel.setText("Nice Breeze");
            weatherDsc.setText("The nice breeze cools your warriors but makes" +
                " arrows miss their mark");
            break;
        case RAIN:
            weatherLabel.setText("Rain");
            weatherDsc.setText(
                "The rain and mud slows all, helping archers' aim");
            break;
        case WIND:
            weatherLabel.setText("Wind");
            weatherDsc.setText(
                "The wind makes arrows fly far from their intended target");
            break;
        default:
            condition = PlayModel.Weather.CLEAR;
            break;
        }
    }

    /**
     * Allows for direct modification of label text if needed.
     * @param label
     * @param str
     */
    public void setLabelText(JLabel label, String str) {
        label.setText(str);
    }

    /**
     * Allaws the controller to pass the Model's power to the View's labels.
     * @param player1
     * @param player2
     */
    public void setPower(int player1, int player2) { 
        p1Power.setText(Integer.toString(player1));
        p2Power.setText(Integer.toString(player2)); 
    }

    /**
     * Allaws the controller to pass the Model's score to the View's labels.
     * @param player1
     * @param player2
     */
    public void setScore(int player1, int player2) {
        p1Score.setText(Integer.toString(player1));
        p2Score.setText(Integer.toString(player2));
    }

    /**
     * Pack the opponent's hand Panel with icons to represent actual TradingCard
     * objects that the player cannot see.
     */
    public void fillCPUHand() {
        p2Hand.removeAll();
        for(int i = 0; i < PlayModel.HAND_SIZE; ++i) {
            opponentCards[i] = new JLabel();
            opponentCards[i].setSize(50,70);
            ImageIcon icon=new ImageIcon("res/image/Unknown.jpg");
            icon=new ImageIcon(icon.getImage().getScaledInstance(50, 70, Image.SCALE_DEFAULT));
            opponentCards[i].setIcon(icon);
            p2Hand.add(opponentCards[i]);
        }
    }

    /**
     * Allows the controller to resent the play area for a new round.
     */
    public void clearBoard() {
        p1Melee.removeAll();
        p1Ranged.removeAll();
        p1Magic.removeAll();
        p2Melee.removeAll();
        p2Ranged.removeAll();
        p2Magic.removeAll();
    }


    /**
     * Displays the text "You Win!" if the controller determines the user won.
     */
    public void winScreen() {
        p1Hand.removeAll();
        JLabel win = new JLabel("You Won!", JLabel.CENTER);
        win.setFont(new Font("Serif", Font.BOLD, 50));
        win.setBackground(Color.GREEN);
        p1Hand.add(win);
    }

    /**
     * Displays the text "You Lose :(" 
     * if the controller determines the user lost.
     */
    public void loseScreen() {
        p1Hand.removeAll();
        JLabel lose = new JLabel("You Lost :(", JLabel.CENTER);
        lose.setFont(new Font("Serif", Font.BOLD, 50));
        lose.setBackground(Color.RED);
        p1Hand.add(lose);
    }


    /**
     * This main method only exists to test layout changes, and is not the main
     * entry point. Running it will display an empty window with placeholder
     * labels.
     * @param args
     */
    public static void main(String[] args) {
        // From the official Java documentation, sets the app to look more
        // 'native'
        try {
            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());
        } 
        catch (UnsupportedLookAndFeelException e) {
           // handle exception
           e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
           // handle exception
           e.printStackTrace();
        }
        catch (InstantiationException e) {
           // handle exception
           e.printStackTrace();
        }
        catch (IllegalAccessException e) {
           // handle exception
           e.printStackTrace();
        }
        PlayView view = new PlayView();
    }
}
