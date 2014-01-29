package game.objects;

import java.awt.*;

/**
 * Created by Zeejfps on 1/28/14.
 */
public class Grid implements Renderable {

    private final int x, y;
    private final int width, height;
    private final int size;

    private final int[][] gridPoints;

    public Grid(int x, int y, int width, int height, int size) {

        this.x = x;         this.y = y;
        this.width = width; this.height = height;
        this.size = size;

        gridPoints = new int[height/size][width/10];

    }

    @Override
    public void draw(Graphics g) {

        g.setColor(Color.DARK_GRAY);
        for (int i = 0; i < gridPoints.length; i++) {

            for (int j = 0; j < gridPoints[i].length; j++) {

                g.drawRect(j*size+x, i*size+y, size, size);

            }

        }

    }

}
