package game.objects;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * User: Zeejfps
 * Date: 1/25/14
 * Time: 11:44 PM
 */
public class Snake implements Renderable {

    private final ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
    private final int size;
    private int speed;

    private enum Direction {

        SOUTH(0, 1), NORTH(0, -1), EAST(1, 0), WEST(-1, 0);

        public int x, y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    private Direction moveDir = Direction.SOUTH;
    private boolean moved = false;

    public Snake() {

        size = 15;
        speed = 3;

    }

    public void init(Rectangle playArea) {

        int x = (playArea.width - size) / 2;
        int y = (playArea.height - size) / 2;

        rectangles.clear();
        rectangles.add(new Rectangle(x, y, size, size));
        rectangles.add(new Rectangle(x, y-size, size, size));

    }

    @Override
    public void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.GREEN);
        for (Rectangle r : rectangles) {
            g2d.fill(r);
        }
        g2d.setColor(Color.BLACK);
        for (Rectangle r : rectangles) {
            g2d.draw(r);
        }

    }

    public void grow() {

        Rectangle prev = rectangles.get(rectangles.size()-2);
        Rectangle tail = rectangles.get(rectangles.size()-1);
        int dx = (prev.x - tail.x);
        int dy = (prev.y - tail.y);

        rectangles.add(new Rectangle(tail.x - dx, tail.y - dy, size, size));

    }

    public void turn(int keyCode) {

        if (moved) {

            switch (keyCode) {

                case KeyEvent.VK_W:case KeyEvent.VK_UP:
                    if (moveDir != Direction.SOUTH) {
                        moveDir = Direction.NORTH;
                    }
                    break;

                case KeyEvent.VK_S:case KeyEvent.VK_DOWN:
                    if (moveDir != Direction.NORTH) {
                        moveDir = Direction.SOUTH;
                    }
                    break;

                case KeyEvent.VK_D:case KeyEvent.VK_RIGHT:
                    if (moveDir != Direction.WEST) {
                        moveDir = Direction.EAST;
                    }
                    break;

                case KeyEvent.VK_A:case KeyEvent.VK_LEFT:
                    if (moveDir != Direction.EAST) {
                        moveDir = Direction.WEST;
                    }
                    break;

                default:
                    break;

            }
            moved = false;

        }

    }

    public void move(float dt) {

        for (int i = rectangles.size()-1; i > 0; i--) {

            int dx = rectangles.get(i).x - rectangles.get(i-1).x;
            int dy = rectangles.get(i).y - rectangles.get(i-1).y;

            int x;
            int y;
            if (dx < 0) {
                x = rectangles.get(i).x +speed;
            } else if (dx > 0){
                x = rectangles.get(i).x -speed;
            } else {
                x = rectangles.get(i).x;
            }

            if (dy < 0) {
                y = rectangles.get(i).y +speed;
            } else if (dy > 0){
                y = rectangles.get(i).y -speed;
            } else {
                y = rectangles.get(i).y;
            }

            rectangles.get(i).setLocation(x, y);
        }

        int x = rectangles.get(0).x + moveDir.x*speed;
        int y = rectangles.get(0).y + moveDir.y*speed;

        rectangles.get(0).setLocation(x, y);

        moved = true;

    }

    public Rectangle getHead() {
        return rectangles.get(0);
    }

    public int getLength() {
        return rectangles.size();
    }

    public ArrayList<Rectangle> getRectangles() {
        return rectangles;
    }

    public boolean collided(Rectangle rect) {

        if (rectangles.get(0).intersects(rect)) {
            return true;
        }

        return false;
    }

    public void increaseSpeed(int amount) {
        speed += amount;
    }

}
