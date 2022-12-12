import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CardSlot extends JScrollPane {
    List<Card> cards = new ArrayList<>();
    int totalPoints;
    int reducedPoints;
    boolean isWeatherAffected;
    boolean isDoubled;

    CardSlot() {
        this.isDoubled = false;
        this.isWeatherAffected = false;
        this.totalPoints = 0;
        this.reducedPoints = 0;
    }

    int calPoints() {
        this.totalPoints = 0;
        for (Card card : this.cards) {
            if (isWeatherAffected && !card.isHero && isDoubled && card.getPoint() > 0) {
                //天气影响，有双倍
                this.totalPoints += 2;
            } else if (isWeatherAffected && !card.isHero && card.getPoint() > 0) {
                //天气影响，无双倍
                this.totalPoints += 1;
            } else if (!isWeatherAffected && !card.isHero && isDoubled) {
                //无天气，有双倍
                this.totalPoints += 2 * card.getPoint();
            } else {
                this.totalPoints += card.getPoint();
            }
        }
        return this.totalPoints;
    }

    //算出因为天气影响而减少的点数
    int calReduced() {
        this.reducedPoints = 0;
        for (Card card : this.cards) {
            this.reducedPoints += card.getPoint();
        }
        this.calPoints();
        this.reducedPoints = this.reducedPoints - this.totalPoints;
        return this.reducedPoints;
    }

    void addCard(Card card) {
        this.add(card);
        this.calPoints();
        this.calReduced();
        this.setVisible(true);
    }
}
