package engine.core;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * User: Zeejfps
 * Date: 1/25/14
 * Time: 9:07 PM
 */
public class GameWindow extends WindowAdapter {

    private final Game game;
    private JFrame window;

    public GameWindow(Game game) {

        this.game = game;

        createAndShowWindow();
    }

    private void createAndShowWindow() {

        window = new JFrame(game.getTitle());
        window.addWindowListener(this);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setContentPane(game);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }

    @Override
    public void windowClosing(WindowEvent e) {
        game.stop();
    }

}
