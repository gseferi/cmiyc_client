package graphics;

import javafx.application.Platform;

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
