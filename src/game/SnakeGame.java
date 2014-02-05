package game;

import engine.util.Clock;
import engine.core.Game;
import game.screens.EndScreen;
import game.screens.GameScreen;
import game.screens.StartScreen;

/**
 * User: Zeejfps
 * Date: 1/25/14
 * Time: 9:03 PM
 */
public class SnakeGame extends Game {

    public static final String TITLE = "Snake Game v1.0";
    public static final int WIDTH = 320;
    public static final int HEIGHT = 240;
    public static final int SCALE = 2;

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

    }

    @Override
    public void update() {

        if (!paused && !gameOver) {

            gameClock.tick();

        } else if (gameOver) {

            stop();

        }

    }

    @Override
    public void render() {

        gameScreen.clear();

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




