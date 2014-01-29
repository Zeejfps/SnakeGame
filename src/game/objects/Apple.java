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

    private final Grid grid;

    public Apple(Grid grid) {

        this.grid = grid;

        width = grid.getSquareSize();
        height = grid.getSquareSize();

        respawn();
    }

    public void respawn() {

        int x = RAND.nextInt(grid.getPoints().length);
        int y = RAND.nextInt(grid.getPoints()[x].length);

        setLocation(grid.getPoints()[x][y]);
    }

    @Override
    public void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(APPLE_IMAGE, x, y, width, height, null);
        g2d.draw(this);

    }

}
