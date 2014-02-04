package game;

import engine.game.GameWindow;
import engine.util.Clock;
import engine.game.Game;
import game.objects.Apple;
import game.objects.Grid;
import game.objects.snake.Snake;
import game.screens.EndScreen;
import game.screens.GameScreen;
import game.screens.StartScreen;

import java.awt.event.KeyEvent;

/**
 * User: Zeejfps
 * Date: 1/25/14
 * Time: 9:03 PM
 */
public class SnakeGame extends Game {

    public static final String TITLE = "Snake Game v1.0";
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;

    private final StartScreen startScreen;
    private final GameScreen gameScreen;
    private final EndScreen endScreen;

    private Grid grid;
    private Snake snake;
    private Apple apple;

    private boolean gameOver = false;
    private boolean paused = false;

    private final Clock gameClock = new Clock();
    private int score = 0;

    public SnakeGame() {

        super(WIDTH, HEIGHT, TITLE, 99999, 30);

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

        if (keyboard.keyDown(KeyEvent.VK_E)) {
            System.out.println("Key Pressed!!");
        }

        if (keyboard.keyReleased(KeyEvent.VK_E)) {
            System.out.println("Key Released!");
        }

        if (keyboard.keyTyped(KeyEvent.VK_E)) {
            System.out.println("Key Typed!");
        }

        if (!paused && !gameOver) {

            gameClock.tick();
            //snake.move(gameClock.getDeltaTime());

        } else if (gameOver) {

            stop();

        }

    }

    @Override
    public void render() {

        gameScreen.clear();

        //gameScreen.render(grid);
        //gameScreen.render(apple);
        //gameScreen.render(snake);

        gameScreen.update();

    }

    @Override
    public void onEnd() {

        System.out.println("OnEnd");
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




