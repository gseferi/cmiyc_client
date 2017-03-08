package sample;

import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Created by Gerta on 24/02/2017.
 */
public class GameScreen extends AnchorPane{

    private BorderPane gameScreen;
    private ToolBar gameControls;

    public GameScreen() throws IOException {
        this.gameScreen = new BorderPane();
        this.gameControls = new ToolBar();
        this.drawScene();

    }

    public void drawScene() {

        this.getStylesheets().add("styles/welcomeLayer.css");

        gameControls.setPrefHeight(40);
        this.setPrefWidth(Constants.ScreenWidth);
        this.setPrefHeight(Constants.ScreenHeight);
        this.getChildren().addAll(gameScreen, gameControls);

        gameScreen.setPrefWidth(500);
        gameScreen.setPrefHeight(300);

        AnchorPane.setBottomAnchor(gameControls, 0.0);
        AnchorPane.setRightAnchor(gameControls, 0.0);
        AnchorPane.setLeftAnchor(gameControls, 0.0);
        AnchorPane.setTopAnchor(gameScreen, 40.0);
        AnchorPane.setLeftAnchor(gameScreen, 40.0);
        AnchorPane.setRightAnchor(gameScreen, 40.0);

        this.getStylesheets().add("styles/gameLayer.css");
        this.setId("gameLayer");
        gameScreen.setId("gameScreen");
        gameControls.setId("gameControls");

    }

}
