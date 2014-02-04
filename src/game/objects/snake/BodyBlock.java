package game.objects.snake;

import java.awt.*;

/**
 * User: Zeejfps
 * Date: 1/29/14
 * Time: 3:10 PM
 */
class BodyBlock extends Block {

    private final Block parent;

    public BodyBlock(Point pos, int size, Block parent) {
        super(pos, size);
        this.parent = parent;
    }

}
