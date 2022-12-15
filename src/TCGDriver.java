/**
 * Mike Limpus
 * CST 338 Final Project
 * TCGDriver.java
 * Main program entry point, sets the look and feel of the application 
 * to be "Native" to the operating system
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

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
        StartMenu startMenu=new StartMenu();
        JButton start=new JButton();
        ImageIcon icon=new ImageIcon("res/image/startbtn1.png");
        start.setIcon(icon);
        icon=new ImageIcon("res/image/startbtn2.png");
        start.setPressedIcon(icon);
        start.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayController game = new PlayController();
                startMenu.setVisible(false);
                game.round();
            }
        });
        JButton rule=new JButton("规则");
        rule.setFont(new Font("Serif", Font.BOLD, 15));
        rule.setOpaque(false);
        rule.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame r=new JFrame("规则说明");
                JLabel text=new JLabel("//此处键入规则");
                r.setSize(400, 305);
                r.setResizable(false);
                r.setDefaultCloseOperation(EXIT_ON_CLOSE);
                ImageIcon icon=new ImageIcon("res/image/Unknown.jpg");
                r.setIconImage(icon.getImage());
                text.setBounds(0,0,r.getWidth(),r.getHeight());
                text.setFont(new Font("Sans Serif", Font.PLAIN, 20));
                text.setOpaque(false);
                r.add(text);
                r.setVisible(true);
            }
        });
        startMenu.add(start);
        startMenu.add(rule);
        start.setBounds(startMenu.getWidth()/2-75,400,150,45);
        rule.setBounds(0,0,50,50);
    }
}
