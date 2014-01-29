package game.objects;

import sun.org.mozilla.javascript.internal.ast.Block;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * User: Zeejfps
 * Date: 1/25/14
 * Time: 11:44 PM
 */
public class Snake implements Renderable {

    private enum Direction {

        NORTH(0, -1), SOUTH(0, 1), EAST(1, 0), WEST(-1, 0);

        public int x, y;

        Direction(int x, int y) {
            this.x = x; this.y = y;
        }

    }

    private final ArrayList<BodyBlock> bodyBlocks;
    private final BodyBlock head;

    public Snake() {

        bodyBlocks = new ArrayList<BodyBlock>();
        head = new BodyBlock(30, 30, 15, null);

        bodyBlocks.add(head);
        bodyBlocks.add(new BodyBlock(30, 30-15, 15, head));

    }

    public void move() {

        for (BodyBlock b : bodyBlocks) {
            b.move(1);
        }

    }

    public void grow() {

        BodyBlock prev = bodyBlocks.get(bodyBlocks.size()-2);
        BodyBlock tail = bodyBlocks.get(bodyBlocks.size()-1);
        int dx = (prev.x - tail.x);
        int dy = (prev.y - tail.y);

        bodyBlocks.add(new BodyBlock(tail.x - dx, tail.y - dy, 15, tail));
    }

    @Override
    public void draw(Graphics g) {

        for (BodyBlock b : bodyBlocks) {
            b.draw(g);
        }

    }

    public void turn(int keyCode) {

        switch (keyCode) {

            case KeyEvent.VK_LEFT:case KeyEvent.VK_A:
                if (head.getDirection() != Direction.EAST){
                    head.setDirection(Direction.WEST);
                }
                break;

            case KeyEvent.VK_RIGHT:case KeyEvent.VK_D:
                if (head.getDirection() != Direction.WEST){
                    head.setDirection(Direction.EAST);
                }
                break;

            case KeyEvent.VK_UP:case KeyEvent.VK_W:
                if (head.getDirection() != Direction.SOUTH){
                    head.setDirection(Direction.NORTH);
                }
                break;

            case KeyEvent.VK_DOWN:case KeyEvent.VK_S:
                if (head.getDirection() != Direction.NORTH){
                    head.setDirection(Direction.SOUTH);
                }
                break;

            default:
                break;

        }

    }

    private class BodyBlock implements Renderable{

        private Direction direction;
        private final BodyBlock parent;

        private int x, y, size;
        private final Rectangle bounds;

        public BodyBlock(int x, int y, int size, BodyBlock parent) {

            this.x = x; this.y =y;
            this.size = size;

            this.parent = parent;
            bounds = new Rectangle(x, y, size, size);
            direction = Direction.SOUTH;

        }

        public void move(int amount) {

            x += amount*direction.x;
            y += amount*direction.y;

            bounds.setLocation(x, y);

            if (parent != null) {

                int dx = parent.getX() - getX();
                int dy = parent.getY() - getY();
                if (dx == 0) {

                    if (dy < 0) {
                        direction = Direction.NORTH;
                    } else {
                        direction = Direction.SOUTH;
                    }

                } else if (dy == 0) {

                    if (dx < 0) {
                        direction = Direction.WEST;
                    } else {
                        direction = Direction.EAST;
                    }

                }

            }

        }

        @Override
        public void draw(Graphics g) {

            Graphics2D g2d = (Graphics2D)g;
            g2d.setColor(Color.GREEN);
            g2d.fill(bounds);
            g2d.setColor(Color.BLACK);
            g2d.draw(bounds);

        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setDirection(Direction dir) {
            this.direction = dir;
        }

        public Direction getDirection() {
            return direction;
        }

    }

}
