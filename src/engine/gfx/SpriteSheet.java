package engine.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Zeejfps on 2/3/14.
 */
public class SpriteSheet extends Bitmap {
    public SpriteSheet(BufferedImage image) {
        super(image.getWidth(), image.getHeight());
        pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
    }
}
