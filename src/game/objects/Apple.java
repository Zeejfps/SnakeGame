package game.objects;

import engine.gfx.Sprite;
import engine.gfx.SpriteSheet;
import game.SnakeGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;

/**
 * Created by Zeejfps on 1/26/14.
 */
public class Apple extends Sprite{

    private static final Random RAND = new Random(System.nanoTime());
    private static File APPLE_IMAGE;
    static {

        try {

            APPLE_IMAGE = new File(SnakeGame.class.getResource("images/spritesheet.png").toURI());

        } catch (URISyntaxException e) {
            System.err.println("Could not load apple.png!");
            System.exit(1);
        }

    }

    public Apple() {

        super(10, 10, 0, 0, 25, 25, new SpriteSheet(128, 128, APPLE_IMAGE));

    }

}
