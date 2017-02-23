package graphics;

import javafx.application.Platform;

public class GameLoop implements Runnable {

    private GameDrawer drawer;

    public GameLoop(GameDrawer drawer) {
        this.drawer = drawer;
    }

    public void run() {
        while (true) {

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
