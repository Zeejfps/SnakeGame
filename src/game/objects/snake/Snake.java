package game.objects.snake;

import game.objects.Grid;
import game.objects.Renderable;

import java.awt.*;

/**
 * User: Zeejfps
 * Date: 1/25/14
 * Time: 11:44 PM
 */
public class Snake implements Renderable {

    private final Grid grid;

    public Snake(Grid grid) {

        this.grid = grid;

    }

    @Override
    public void draw(Graphics g) {

    }

}
