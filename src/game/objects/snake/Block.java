package game.objects.snake;

import game.objects.Renderable;

import java.awt.*;

/**
 * User: Zeejfps
 * Date: 1/29/14
 * Time: 3:06 PM
 */
class Block implements Renderable {

    private Point pos;
    private final int size;
    private final Rectangle bounds;

    private boolean moved;
    private Direction direction;

    public Block(Point pos, int size) {
        this.pos = pos;
        this.size = size;
        bounds = new Rectangle(pos.x, pos.y, size, size);
        direction = Direction.SOUTH;
        moved = false;
    }

    @Override
    public void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.GREEN);
        g2d.fill(bounds);
        g2d.setColor(Color.BLACK);
        g2d.draw(bounds);

    }

    float c = 0;
    public void move(float dt, int amount) {


        pos.x += 1 * direction.x;
        //System.out.println(pos.x);
        pos.y += 1 * direction.y;

        bounds.setLocation(pos.x, pos.y);

    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

}

