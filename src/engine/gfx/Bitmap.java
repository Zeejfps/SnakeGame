package engine.gfx;

/**
 * Created by Zeejfps on 2/3/14.
 */
public class Bitmap {

    private int width;
    private int height;

    public int[] pixels;

    public Bitmap(int width, int height) {

        this.width = width;
        this.height = height;

        this.pixels = new int[width*height];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
