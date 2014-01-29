package game.screens;

import game.SnakeGame;

import javax.swing.*;
import java.awt.*;

/**
 * User: Zeejfps
 * Date: 1/25/14
 * Time: 10:15 PM
 */
public class EndScreen extends JPanel {

    private final SnakeGame game;

    public  EndScreen(SnakeGame game) {

        this.game = game;
        setName("EndScreen");
        setBackground(Color.BLACK);

    }

}
