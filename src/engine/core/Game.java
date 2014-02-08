package engine.core;

import engine.util.Input;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * User: Zeejfps
 * Date: 1/25/14
 * Time: 9:03 PM
 */
public abstract class Game {

    private volatile boolean running = false;

    private final int width;
    private final int height;
    private final int scale;
    private final String title;

    private final GameLoop gameLoop;

    protected int fps, ups;
    protected Input input;

    public Game(int width, int height, int scale, String title, int fps, int ups) {

        this.width = width;
        this.height = height;
        this.scale = scale;
        this.title = title;

        this.fps = fps;
        this.ups = ups;

        this.input = new Input();

        gameLoop = new GameLoop(this);
    }

    public synchronized void start() {

        if (!running) {

            running = true;
            onStart();
            gameLoop.start();

        }

    }

    public synchronized void stop() {

        if (running) {

            running = false;
            onEnd();
            gameLoop.stop();

        }

    }

    public boolean isRunning() {
        return running;
    }

    public String getTitle() {
        return title;
    }

    public abstract void onStart();

    public abstract void update();

    public abstract void render();

    public abstract void onEnd();

    public int getScale() {
        return scale;
    }

    public void setUps(int ups) {

        if (ups < 1) {
            this.ups = 1;
        } else {
            this.ups = ups;
        }

    }

    public void setFps(int fps) {

        if (fps < 1) {
            this.fps = 1;
        } else {
            this.fps = fps;
        }

    }

}
