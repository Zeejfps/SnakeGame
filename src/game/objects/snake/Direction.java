package game.objects.snake;

/**
 * User: Zeejfps
 * Date: 1/29/14
 * Time: 3:08 PM
 */
enum Direction {

    NORTH(0, -1),
    SOUTH(0, 1),
    EAST(1, 0),
    WEST(-1, 0);

    public int x, y;

    Direction(int x, int y) {
        this.x = x; this.y = y;
    }

}
