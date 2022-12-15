import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TCDeck {
    // Members
    private ArrayList<TradingCard> deck = new ArrayList<>();
    private static ArrayList<TradingCard> cardList = new ArrayList<>();
    private static final int DECK_SIZE = 60;
    private boolean initialized = false;

    // Methods

    /**
     * Default constructor will create a default deck and initialize the master
     * list if not done already
     */
    public TCDeck() {
        if (!initialized)
            createCards();
        defaultDeck();
    }

    /**
     * Constructor to determine if the deck is a random one or the default one
     * @param random
     */
    public TCDeck(boolean random) {
        if (!initialized)
            createCards();
        if(random)
            randomDeck();
        else 
            defaultDeck();
    }

    /**
     * Using the included CardList.txt, fill the arraylist cardList with each of
     * the created cardTypes
     */
    public void createCards() {
        try {
            File file = new File("res/CardList.txt");
            Scanner scanner = new Scanner(file);
            for (int i = 0; i < DECK_SIZE; i++) {
                String tempType = scanner.nextLine();
                int tempPower = Integer.parseInt(scanner.nextLine());
                String tempName = scanner.nextLine();
                cardList.add(new TradingCard(tempPower, tempName, tempType)); 
            }
            scanner.close();
            initialized = true;

        }
        catch (FileNotFoundException e) {
            System.out.println("File not found!" + 
                "Should have a 'CardList.txt' file in this directory");
            e.printStackTrace();
        }
    }

    /**
     * Make the deck be a deep copy of the cardList, intended for the player
     */
    public void defaultDeck() {
        deck = new ArrayList<>(cardList);
    }

    /**
     * Fill the deck with random cards, meant for the CPU player
     */
    public void randomDeck() {
        for (int i = 0; i < DECK_SIZE; ++i) {
            int rand = (int) (Math.random() * cardList.size());
            deck.add(cardList.get(rand));
        }
    }

    /**
     * Use the java Collections.shuffle methods to shuffle the deck
     */
    public void shuffle() {
        Collections.shuffle(deck);
    }
 
    /**
     * Remove the top card of the deck and return it to be played
     * @return TradingCard that was removed from the deck
     */
    public TradingCard drawCard() {
        return deck.remove(0);
    }

    // DEBUG Main method, not main program entry point
    public static void main(String[] args) {
        TCDeck test = new TCDeck(false);
        TCDeck test2 = new TCDeck(true);
        for (int i = 0; i < cardList.size(); i++) { 
            test.deck.get(i).print();
        }
        test.shuffle();
        for (int i = 0; i < cardList.size(); i++) { 
            test.deck.get(i).print();
        }
        for (int i = 0; i < cardList.size(); i++) { 
            test2.deck.get(i).print();
        }
        test2.shuffle();
        for (int i = 0; i < cardList.size(); i++) { 
            test2.deck.get(i).print();
        }


    }
}
