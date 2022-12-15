/**
 * Mike Limpus
 * CST 338 Final Project
 * TCGDriver.java
 * Main program entry point, sets the look and feel of the application 
 * to be "Native" to the operating system
 */

import javax.swing.*;

public class TCGDriver {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());
        } 
        catch (UnsupportedLookAndFeelException | ClassNotFoundException | 
            InstantiationException | IllegalAccessException e) {
           e.printStackTrace();
        }
        PlayController game = new PlayController();
        game.round();
    }
}
