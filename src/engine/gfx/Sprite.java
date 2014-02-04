package engine.gfx;

/**
 * Created by Zeejfps on 2/3/14.
 */
public class Sprite extends Bitmap {

    private int x, y;

    public Sprite(int x, int y, int xOffset, int yOffset, int width, int height, SpriteSheet spriteSheet) {
        super(width, height);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pixels[i*width + j] = spriteSheet.pixels[i*spriteSheet.getWidth() + j];
            }
        }

        this.x = x;
        this.y = y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
