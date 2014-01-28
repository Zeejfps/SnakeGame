package game;

import engine.Clock;
import engine.Game;
import engine.GameContainer;
import game.objects.Apple;
import game.objects.Renderable;
import game.objects.Snake;
import game.screens.EndScreen;
import game.screens.GameScreen;
import game.screens.StartScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * User: Zeejfps
 * Date: 1/25/14
 * Time: 9:03 PM
 */
public class SnakeGame extends Game {

    public static final String TITLE = "Snake Game v1.0";
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;

    private final InputHandler ih = new InputHandler();
    private final GameContainer gc;

    private final StartScreen startScreen;
    private final GameScreen gameScreen;
    private final EndScreen endScreen;

    private final Snake snake;
    private final Apple apple;

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

        snake = new Snake();
        apple = new Apple();

        addKeyListener(ih);
        setScreen(startScreen);

        gc = new GameContainer(this);
        gc.show();
    }

    @Override
    public void onStart() {

        setScreen(gameScreen);

        snake.init(gameScreen.getBounds());
        apple.init(gameScreen.getBounds(), snake);

    }

    @Override
    public void update() {

        if (!paused && !gameOver) {

            gameClock.tick();
            snake.move(gameClock.getDeltaTime());
            if (snake.collided(apple)) {

                System.out.println("collided");
                apple.respawn(snake);
                snake.grow();
                score++;

                if (snake.getLength() % 5 == 0) {
                    snake.increaseSpeed(1);
                }

            }
           /* if (snake.collided(gameScreen.getWalls())) {
                gameOver = true;
            }*/

        } else if (gameOver) {

            stop();

        }

    }

    @Override
    public void render() {

        gameScreen.clear();

        gameScreen.render(apple);
        gameScreen.render(snake);

        gameScreen.update();

    }

    @Override
    public void onEnd() {

        setScreen(endScreen);

    }

    public boolean isPaused() {
        return paused;
    }

    public KeyListener getInputHandler() {
        return ih;
    }

    private class InputHandler extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            System.out.println("pressed");
            snake.turn(e.getKeyCode());

            if (e.getKeyCode() == KeyEvent.VK_C) {
                snake.grow();
            }

        }

    }

    public void pause() {
        paused = true;
    }

    public void resume() {
        paused = false;
    }

}




