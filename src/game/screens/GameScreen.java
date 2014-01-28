package game.screens;

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
public class GameScreen extends JPanel {

    private final SnakeGame game;

    private final Canvas drawingCanvas;
    private final PauseMenu pauseMenu;

    private final InputHandler ih = new InputHandler();
    private BufferedImage buffer;

    public GameScreen(SnakeGame game) {

        super(new BorderLayout());
        this.game = game;
        setName("GameScreen");

        drawingCanvas = new Canvas();
        drawingCanvas.setFocusable(true);

        pauseMenu = new PauseMenu();

        addKeyListener(ih);
        addKeyListener(game.getInputHandler());
        setFocusable(true);
        setBackground(Color.BLACK);

        add(drawingCanvas, BorderLayout.CENTER);
    }

    public void clear() {

        if (buffer == null) {
            GraphicsConfiguration gf = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
            buffer = gf.createCompatibleImage(drawingCanvas.getWidth(), drawingCanvas.getHeight());
        }
        Graphics g = buffer.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.dispose();
    }

    public void render(Renderable obj) {

        Graphics g = buffer.getGraphics();
        obj.draw(g);
        g.dispose();
    }

    public void update() {

        if (game.isPaused()) {

            Graphics g = buffer.getGraphics();
            pauseMenu.draw(g);
            g.dispose();

        }

        BufferStrategy bs = drawingCanvas.getBufferStrategy();
        if (bs == null) {
            drawingCanvas.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        if (g != null) {
            g.drawImage(buffer, 0, 0, null);
            g.dispose();
        }
        bs.show();

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(game.getWidth(), game.getHeight());
    }

    private class InputHandler extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_ESCAPE || keyCode == KeyEvent.VK_P) {

                if (game.isPaused()) {
                    game.resume();
                } else {
                    game.pause();
                }

            } else if (keyCode == KeyEvent.VK_E && game.isPaused()) {
                System.exit(0);
            }

        }

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
            x = (drawingCanvas.getWidth() - mainFm.stringWidth(strings[0]))/2 + drawingCanvas.getX();
            y = (drawingCanvas.getHeight())/2 + drawingCanvas.getX() - mainFm.getHeight();
            g.drawString(strings[0], x, y);

            g.setFont(innerFont);
            x = (drawingCanvas.getWidth() - innerFm.stringWidth(strings[1]))/2 + drawingCanvas.getX();
            y = (drawingCanvas.getHeight())/2 + drawingCanvas.getX();
            g.drawString(strings[1], x, y);

            x = (drawingCanvas.getWidth() - innerFm.stringWidth(strings[2]))/2 + drawingCanvas.getX();
            y = (drawingCanvas.getHeight())/2 + drawingCanvas.getX() + innerFm.getHeight();
            g.drawString(strings[2], x, y);

        }

    }

}
