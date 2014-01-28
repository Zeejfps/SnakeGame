package game.objects;

import game.SnakeGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Zeejfps on 1/26/14.
 */
public class Apple extends Rectangle implements Renderable {

    private static final Random RAND = new Random(System.nanoTime());
    private static BufferedImage APPLE_IMAGE;
    static {

        try {

            APPLE_IMAGE = ImageIO.read(SnakeGame.class.getResource("images/apple.png"));

        } catch (IOException e) {
            System.err.println("Could not load apple.png!");
            System.exit(1);
        }

    }

    private final int size;
    private Rectangle playArea;

    public Apple() {

        size = 15;

        width = size;
        height = size;

    }

    public void init(Rectangle playArea, Snake snake) {

        this.playArea = playArea;
        respawn(snake);

    }

    public void respawn(Snake snake) {

        int x = RAND.nextInt(playArea.width) + playArea.x;
        int y = RAND.nextInt(playArea.height) + playArea.y;

        setLocation(x, y);

        for (Rectangle r : snake.getRectangles()) {

            if (r.intersects(this)) {
                respawn(snake);
            }

        }

    }

    @Override
    public void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(APPLE_IMAGE, x, y, size, size, null);
        g2d.draw(this);

    }

}
