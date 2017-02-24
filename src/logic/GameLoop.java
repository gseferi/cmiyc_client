package logic;

import graphics.GameDrawer;

import javafx.application.Platform;

/**
 * A Runnable that updates the client in a loop.
 */
public class GameLoop implements Runnable {

    private GameDrawer drawer;
    private GameLogic logic;

    public GameLoop(GameDrawer drawer, GameLogic logic) {
        this.drawer = drawer;
        this.logic = logic;
    }

    public void run() {
        while (true) {

            logic.update();

            Platform.runLater(() -> {
                drawer.draw();
            });

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
