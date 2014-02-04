package game.objects.snake;

import game.objects.Grid;
import game.objects.Renderable;

import java.awt.*;
import java.util.ArrayList;

/**
 * User: Zeejfps
 * Date: 1/25/14
 * Time: 11:44 PM
 */
public class Snake implements Renderable {

    private final Grid grid;
    private final Block head;
    private final ArrayList<BodyBlock> bodyBlocks;

    private int speed = 1;

    public Snake(Grid grid) {

        this.grid = grid;

        head = new Block(new Point(0,0), grid.getSquareSize());
        bodyBlocks = new ArrayList<BodyBlock>();

        grow();
    }

    public void grow() {

    }

    public void move(float dt) {

        head.move(dt, speed);

    }

    @Override
    public void draw(Graphics g) {

        head.draw(g);

    }

}
