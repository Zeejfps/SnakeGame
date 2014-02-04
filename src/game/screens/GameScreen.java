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

    private BufferStrategy bufferStart;
    private BufferedImage buffer;
    private int[] pixelData;

    private Canvas canvas = new Canvas();

    public GameScreen(SnakeGame game) {

        super(game, "GameScreen");

        this.game = game;
        setLayout(new BorderLayout());
        setName("gameScreen");

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

                if ((bitmap.pixels[i*bitmap.getWidth() + j] == 0xffff00ff)){
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

}
