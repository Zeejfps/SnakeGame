package game.objects;

import java.awt.*;

/**
 * Created by Zeejfps on 1/28/14.
 */
public class Grid implements Renderable {

    private final int squareSize;
    private final int x, y;
    private final int width, height;

    private final Point[][] gridPoints;

    public Grid(Canvas drawingCanvas, int squareSize) {

        this.squareSize = squareSize;
        gridPoints = new Point[drawingCanvas.getWidth()/ squareSize][drawingCanvas.getHeight()/ squareSize];
        for (int i = 0; i < gridPoints.length; i++) {

            for (int j = 0; j < gridPoints[i].length; j++) {

                gridPoints[i][j] = new Point(i* squareSize, j* squareSize);

            }

        }

        width = gridPoints.length * squareSize;
        System.out.println(width);
        height = gridPoints[0].length * squareSize;
        System.out.println(height);

        x = (drawingCanvas.getWidth() - width)/2;
        y = (drawingCanvas.getHeight() - height)/2;

    }

    @Override
    public void draw(Graphics g) {

        g.setColor(Color.DARK_GRAY);
        for (int i = 0; i < gridPoints.length; i++) {

            for (int j = 0; j < gridPoints[i].length; j++) {

                g.drawRect(gridPoints[i][j].x, gridPoints[i][j].y, squareSize, squareSize);

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

    public Point[][] getPoints() {
        return gridPoints;
    }

    public int getSquareSize() {
        return squareSize;
    }

}
