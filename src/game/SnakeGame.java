package game;

import engine.gfx.Bitmap;
import engine.gfx.SpriteSheet;
import engine.util.Clock;
import engine.core.Game;
import game.screens.EndScreen;
import game.screens.GameScreen;
import game.screens.StartScreen;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

/**
 * User: Zeejfps
 * Date: 1/25/14
 * Time: 9:03 PM
 */
public class SnakeGame extends Game {

    public static final String TITLE = "Snake Game v1.0";
    public static final int WIDTH = 160;
    public static final int HEIGHT = 120;
    public static final int SCALE = 4;

    private final StartScreen startScreen;
    private final GameScreen gameScreen;
    private final EndScreen endScreen;

    private boolean gameOver = false;
    private boolean paused = false;

    private final Clock gameClock = new Clock();
    private int score = 0;

    public SnakeGame() {

        super(WIDTH, HEIGHT, SCALE, TITLE, 99999, 30);

        startScreen = new StartScreen(this);
        gameScreen = new GameScreen(this);
        endScreen = new EndScreen(this);

        setScreen(startScreen);
    }

    @Override
    public void onStart() {

        gameClock.reset();
        gameClock.start();

        setScreen(gameScreen);

        for (int i = 0; i < test.pixels.length; i++) {
            test.pixels[i] = rand.nextInt() * rand.nextInt(2) / 4;
        }

    }

    @Override
    public void update() {

        if (!paused && !gameOver) {

            gameClock.tick();

        } else if (gameOver) {

            stop();

        }

    }

    Random rand = new Random();
    Bitmap test = new Bitmap(64, 64);

    @Override
    public void render() {

        //gameScreen.clear();

        int dx = (int)(Math.sin(System.currentTimeMillis()%2000/2000.0 * Math.PI*2)*100);
        int dy = (int)(Math.cos(System.currentTimeMillis()%2000/2000.0 * Math.PI*2)*60);

        gameScreen.render(test, 80-32-dx, 60-32-dy);

        gameScreen.update();

    }

    @Override
    public void onEnd() {

        setScreen(endScreen);

    }

    public boolean isPaused() {
        return paused;
    }

    public void pause() {
        paused = true;
    }

    public void resume() {
        paused = false;
    }

}




