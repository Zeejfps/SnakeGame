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

        width = Grid.SQUARE_SIZE;
        height = Grid.SQUARE_SIZE;

        respawn();
    }

    public void respawn() {

        int y = RAND.nextInt(grid.getPoints().length);
        int x = RAND.nextInt(grid.getPoints()[y].length);

        setLocation(x*Grid.SQUARE_SIZE + grid.getX(), y*Grid.SQUARE_SIZE+grid.getY());
    }

    @Override
    public void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(APPLE_IMAGE, x, y, Grid.SQUARE_SIZE, Grid.SQUARE_SIZE, null);
        g2d.draw(this);

    }

}
