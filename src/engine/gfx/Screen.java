package engine.gfx;

import engine.core.Game;

import javax.swing.*;

/**
 * Created by Zeejfps on 2/3/14.
 */
public abstract class Screen extends JPanel{

    public Screen(Game game, String name) {

        game.addScreen(this);
        setName(name);
        setFocusable(true);

    }

}
