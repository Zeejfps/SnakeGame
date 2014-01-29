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

    public Block(Point pos, int size) {
        this.pos = pos;
        this.size = size;
        bounds = new Rectangle(pos.x, pos.y, size, size);
    }

    @Override
    public void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.GREEN);
        g2d.fill(bounds);
        g2d.setColor(Color.BLACK);
        g2d.draw(bounds);

    }

}

