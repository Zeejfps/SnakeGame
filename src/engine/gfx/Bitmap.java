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

    public void render(Bitmap bitmap, int xOffset, int yOffset) {

        int yPixPos, xPixPos;
        int src;

        for (int y = 0; y < bitmap.getHeight(); y ++) {

            yPixPos = y + yOffset;
            if (yPixPos < 0 || yPixPos > width) continue;

            for (int x = 0; x < bitmap.getWidth(); x++) {

                xPixPos = x + xOffset;
                if (xPixPos < 0 || xPixPos > width) continue;

                src = bitmap.pixels[y*bitmap.getWidth() + x];
                if (src > 0) {
                    pixels[yPixPos * width + xPixPos] = src;
                }

            }

        }

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
