package org.hina.main;

import lombok.Getter;

import java.awt.*;

@Getter
public class Game implements Runnable {
    public final static int TILES_DEFAULT_SIZE = 32; // default size of block
    public final static float SCALE = 1.5f;
    public final static int TILES_IN_WIDTH = 26; // number of horizontal block
    public final static int TILES_IN_HEIGHT = 14; // number of Vertical block
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE); // real block size
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH; // game width size
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT; // game height size

    private final GamePanel gamePanel;
    private final GameWindow gameWindow;
    static int FPS_SET = 120;
    static int UPS_SET = 200;

    public Game() {
        initClasses();
        this.gamePanel = new GamePanel(this);
        this.gameWindow = new GameWindow(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();

        startGameLoop();
    }

    private void initClasses() {
    }

    private void startGameLoop(){
        Thread thread = new Thread(this);
        thread.start();
    }

    public void render(Graphics g) {
    }


    public void update() {

    }


    @Override
    public void run() {
        double timePerFrame = 1_000_000_000.0 / FPS_SET;
        double timePerUpdate = 1_000_000_000.0 / UPS_SET;
        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;

            }
        }
    }

    public void winFocusLost (){
    }
}
