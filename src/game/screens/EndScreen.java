package game.screens;

import engine.gfx.Screen;
import game.SnakeGame;

import javax.swing.*;
import java.awt.*;

/**
 * User: Zeejfps
 * Date: 1/25/14
 * Time: 10:15 PM
 */
public class EndScreen extends Screen {

    public  EndScreen(SnakeGame game) {

        super(game, "EndScreen");

        setName("EndScreen");
        setBackground(Color.BLACK);

    }

}
