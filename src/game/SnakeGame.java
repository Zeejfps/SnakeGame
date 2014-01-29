package game;

import engine.Clock;
import engine.Game;
import engine.GameContainer;
import game.objects.Apple;
import game.objects.Grid;
import game.objects.snake.Snake;
import game.screens.EndScreen;
import game.screens.GameScreen;
import game.screens.StartScreen;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * User: Zeejfps
 * Date: 1/25/14
 * Time: 9:03 PM
 */
public class SnakeGame extends Game implements KeyListener{

    public static final String TITLE = "Snake Game v1.0";
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;

    private final GameContainer gc;

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

        super(WIDTH, HEIGHT, TITLE, 60, 30);

        startScreen = new StartScreen(this);
        addScreen(startScreen);

        gameScreen = new GameScreen(this);
        addScreen(gameScreen);

        endScreen = new EndScreen(this);
        addScreen(endScreen);

        setScreen(startScreen);

        gc = new GameContainer(this);
        gc.show();
    }

    @Override
    public void onStart() {

        setScreen(gameScreen);

        Canvas drawingCanvas = gameScreen.getDrawingCanvas();
        grid = new Grid(drawingCanvas, 20);
        snake = new Snake(grid);
        apple = new Apple(grid);

    }

    @Override
    public void update() {

        if (!paused && !gameOver) {

            gameClock.tick();
            snake.move(gameClock.getDeltaTime());
            if (snake.outOfBounds()) {
                gameOver = true;
            }

        } else if (gameOver) {

            stop();

        }

    }

    @Override
    public void render() {

        gameScreen.clear();

        gameScreen.render(grid);
        gameScreen.render(apple);
        gameScreen.render(snake);

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

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        snake.turn(e.getKeyCode());

        if (e.getKeyCode() == KeyEvent.VK_C) {
            snake.grow();
        }

        if (e.getKeyCode() == KeyEvent.VK_X) {
            apple.respawn();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {}

}




