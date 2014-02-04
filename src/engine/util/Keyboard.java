package engine.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by Zeejfps on 2/3/14.
 */
public class Keyboard implements KeyListener {

    private boolean[] keysDown = new boolean[256];
    private boolean[] keysReleased = new boolean[256];
    private boolean[] keysTyped = new boolean[256];

    private volatile ArrayList<KeyEvent> events = new ArrayList<KeyEvent>();

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        events.add(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        events.add(e);
    }

    public boolean keyDown(int keyCode) {
        return keysDown[keyCode];
    }

    public boolean keyReleased(int keyCode) {
        return keysReleased[keyCode];
    }

    public boolean keyTyped(int keyCode) {
        return keysTyped[keyCode];
    }

    public void poll() {

        for (int i = 0; i < 256; i++) {
            keysReleased[i] = false;
            if (keysDown[i]) {
                keysTyped[i] = false;
            }
        }

        for (KeyEvent e : events) {

            processEvent(e);

        }

        events.clear();

    }

    private void processEvent(KeyEvent e) {

        int key = e.getKeyCode();

        switch (e.getID()){

            case KeyEvent.KEY_PRESSED:
                if (keysDown[key]) {
                    keysTyped[key] = false;
                } else {
                    keysTyped[key] = true;
                }
                keysDown[key] = true;
                break;

            case KeyEvent.KEY_RELEASED:
                keysDown[key] = false;
                keysReleased[key] = true;
                break;

            default:
                break;

        }
        e.consume();

    }

}
