package game.screens;

import engine.gfx.Screen;
import game.objects.Renderable;
import game.SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

/**
 * User: Zeejfps
 * Date: 1/25/14
 * Time: 10:05 PM
 */
public class GameScreen extends Screen {

    private final SnakeGame game;
    private final PauseMenu pauseMenu;

    private BufferedImage buffer;

    public GameScreen(SnakeGame game) {

        super(game, "GameScreen");

        this.game = game;
        setLayout(new BorderLayout());
        setName("gameScreen");

        pauseMenu = new PauseMenu();

        setFocusable(true);
        setBackground(Color.BLACK);

        game.addScreen(this);
    }

    public void clear() {

    }

    public void render(Renderable obj) {

    }

    public void update() {


    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(game.getWidth(), game.getHeight());
    }

    private class PauseMenu {

        private final Font mainFont = new Font(Font.DIALOG_INPUT, Font.BOLD, 32);
        private final Font innerFont = new Font(Font.DIALOG_INPUT, Font.PLAIN, 18);

        private final FontMetrics mainFm = getFontMetrics(mainFont);
        private final FontMetrics innerFm = getFontMetrics(innerFont);

        private final String[] strings = {"Paused", "(Esc/P)To Resume", "(E)To Exit"};

        public void draw(Graphics g) {

            g.setColor(Color.RED);
            int x;
            int y;

            g.setFont(mainFont);
            x = (getWidth() - mainFm.stringWidth(strings[0]))/2 + getX();
            y = (getHeight())/2 + getX() - mainFm.getHeight();
            g.drawString(strings[0], x, y);

            g.setFont(innerFont);
            x = (getWidth() - innerFm.stringWidth(strings[1]))/2 + getX();
            y = (getHeight())/2 + getX();
            g.drawString(strings[1], x, y);

            x = (getWidth() - innerFm.stringWidth(strings[2]))/2 + getX();
            y = (getHeight())/2 + getX() + innerFm.getHeight();
            g.drawString(strings[2], x, y);

        }

    }

}
