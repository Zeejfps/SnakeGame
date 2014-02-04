package engine.core;

import engine.gfx.Screen;
import engine.util.Keyboard;
import engine.util.Mouse;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * User: Zeejfps
 * Date: 1/25/14
 * Time: 9:03 PM
 */
public abstract class Game extends JPanel {

    private volatile boolean running = false;

    private final int width;
    private final int height;
    private final int scale;
    private final String title;

    private final GameWindow gameWindow;
    private final GameLoop gameLoop;

    protected int fps, ups;
    protected Keyboard keyboard;
    protected Mouse mouse;

    private final JPanel screenContainer = new JPanel(new CardLayout());
    private final ArrayList<Screen> screens = new ArrayList<Screen>();
    private JPanel currentScreen;

    public Game(int width, int height, int scale, String title, int fps, int ups) {

        super(new BorderLayout());
        setBackground(Color.BLACK);

        this.width = width;
        this.height = height;
        this.scale = scale;
        this.title = title;

        this.fps = fps;
        this.ups = ups;

        this.keyboard = new Keyboard();
        this.mouse = new Mouse();

        gameLoop = new GameLoop(this);
        add(screenContainer, BorderLayout.CENTER);

        gameWindow = new GameWindow(this);
    }

    public void start() {

        if (!running) {

            running = true;
            onStart();
            gameLoop.start();

        }

    }

    public void stop() {

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

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width*scale, height*scale);
    }

    public abstract void onStart();

    public abstract void update();

    public abstract void render();

    public abstract void onEnd();

    public void setScreen(Screen screen) {

        if (!screens.contains(screen)) {
            System.err.println("This screen was not added!");
            System.exit(1);
        }

        currentScreen = screen;
        currentScreen.addKeyListener(keyboard);
        currentScreen.addMouseListener(mouse);
        currentScreen.addMouseMotionListener(mouse);
        ((CardLayout)screenContainer.getLayout()).show(screenContainer, currentScreen.getName());
        currentScreen.requestFocusInWindow();

    }

    public void addScreen(Screen screen) {
        screens.add(screen);
        screenContainer.add(screen, screen.getName());
    }

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
