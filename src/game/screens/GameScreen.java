package game.screens;

import engine.gfx.Bitmap;
import engine.gfx.Screen;
import engine.gfx.Sprite;
import game.SnakeGame;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * User: Zeejfps
 * Date: 1/25/14
 * Time: 10:05 PM
 */
public class GameScreen extends Screen {

    private final SnakeGame game;
    private final PauseMenu pauseMenu;

    private BufferStrategy bufferStart;
    private BufferedImage buffer;
    private int[] pixelData;

    private Canvas canvas = new Canvas();

    public GameScreen(SnakeGame game) {

        super(game, "GameScreen");

        this.game = game;
        setLayout(new BorderLayout());
        setName("gameScreen");

        pauseMenu = new PauseMenu();

        setFocusable(true);
        setBackground(Color.BLACK);

        add(canvas, BorderLayout.CENTER);

        game.addScreen(this);

        GraphicsDevice ge = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        buffer = ge.getDefaultConfiguration().createCompatibleImage(game.getWidth(), game.getHeight());
        pixelData = ((DataBufferInt)buffer.getRaster().getDataBuffer()).getData();
    }

    public void clear() {

        Graphics g = buffer.getGraphics();
        if (g!=null) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());
            g.dispose();
        }

    }

    public void render(Bitmap bitmap, int xOffset, int yOffset) {

        for (int i = 0; i < bitmap.getHeight(); i++) {

            int yPix = (i+yOffset)*buffer.getWidth();

            for (int j = 0; j < bitmap.getHeight(); j++) {

                int xPix = j+xOffset;

                if (bitmap.pixels[i*bitmap.getWidth() + j] == 0xffff00ff) {
                    continue;
                }

                pixelData[yPix + xPix] = bitmap.pixels[i*bitmap.getWidth() + j];

            }

        }

    }

    public void render(Sprite sprite) {

        render(sprite, sprite.getX(), sprite.getY());

    }

    public void update() {

        bufferStart = canvas.getBufferStrategy();
        if (bufferStart != null) {
            Graphics g = bufferStart.getDrawGraphics();
            g.drawImage(buffer, 0, 0, game.getWidth()*game.getScale(), game.getHeight()*game.getScale(), null);
            g.dispose();

            bufferStart.show();
        } else {
            canvas.createBufferStrategy(3);
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
