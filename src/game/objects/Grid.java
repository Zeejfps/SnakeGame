package game.objects;

import java.awt.*;

/**
 * Created by Zeejfps on 1/28/14.
 */
public class Grid implements Renderable {

    public static final int SQUARE_SIZE = 20;

    private final int x, y;
    private final int width, height;

    private final int[][] gridPoints;

    public Grid(Canvas drawingCanvas) {

        gridPoints = new int[drawingCanvas.getHeight()/SQUARE_SIZE][drawingCanvas.getWidth()/SQUARE_SIZE];
        height = gridPoints.length * SQUARE_SIZE;
        width = gridPoints[0].length * SQUARE_SIZE;

        x = (drawingCanvas.getWidth() - width)/2;
        y = (drawingCanvas.getHeight() - height)/2;

    }

    @Override
    public void draw(Graphics g) {

        g.setColor(Color.DARK_GRAY);
        for (int i = 0; i < gridPoints.length; i++) {

            for (int j = 0; j < gridPoints[i].length; j++) {

                g.drawRect(j*SQUARE_SIZE+x, i*SQUARE_SIZE+y, SQUARE_SIZE, SQUARE_SIZE);

            }

        }

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[][] getPoints() {
        return gridPoints;
    }

}
