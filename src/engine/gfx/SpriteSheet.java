package engine.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Zeejfps on 2/3/14.
 */
public class SpriteSheet extends Bitmap {
    public SpriteSheet(int width, int height, File image) {
        super(width, height);
        try {
            BufferedImage sheet = ImageIO.read(image);
            pixels = sheet.getRGB(0, 0, width, height, null, 0, width);
        } catch (IOException e) {
            System.err.println("Could not load SpriteSheet!");
            System.exit(1);
        }
    }
}
