package engine.game;

import engine.util.Clock;

/**
 * User: Zeejfps
 * Date: 1/25/14
 * Time: 9:17 PM
 */
public class GameLoop implements Runnable {

    private final Game game;

    private final Clock ticksClock = new Clock();
    private final Clock framesClock = new Clock();

    private final Thread loopThread;

    private final int maxSkippedFrames = 10;

    private int ticks = 0;
    private int frames = 0;

    public GameLoop(Game game) {

        this.game = game;

        loopThread = new Thread(this);

    }

    public void start() {

        loopThread.start();
    }

    public int getTicks() {
        return ticks;
    }

    public int getFrames() {
        return frames;
    }

    @Override
    public void run() {

        int skippedFrames;
        long runTime = 0;

        ticksClock.start();
        framesClock.start();

        while (game.isRunning()) {

            long nsPerTick = Clock.NS_PER_SEC / game.ups;
            long nsPerFrame = Clock.NS_PER_SEC / game.fps;

            ticksClock.tick();
            runTime += ticksClock.getTimePerTick();

            skippedFrames = 0;
            while (runTime >= nsPerTick && skippedFrames <= maxSkippedFrames) {

                game.keyboard.poll();
                game.update();
                runTime -= nsPerTick;
                skippedFrames ++;

                ticks++;
            }

            framesClock.tick();
            if (framesClock.getRunTimeNs() >= nsPerFrame) {

                game.render();

                framesClock.reset();
                frames++;
            }

            if (true) {

                if (ticksClock.getRunTimeMs() >= 1000) {
                    System.out.println("Ticks: " + ticks + " Frames: " + frames);

                    ticks = 0;
                    frames = 0;

                    ticksClock.reset();

                }

            }

        }

    }

    public void stop() {

        try {
            loopThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
