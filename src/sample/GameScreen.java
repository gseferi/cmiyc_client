package sample;

import com.sun.istack.internal.Nullable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Created by Gerta on 24/02/2017.
 */
public class GameScreen {

    private AnchorPane gameLayer;
    private BorderPane gameScreen;
    private ToolBar gameControls;

    public GameScreen() throws IOException {
        this.gameLayer = new AnchorPane();
        this.gameScreen = new BorderPane();
        this.gameControls = new ToolBar();

    }

    public Scene drawScene() {

        gameLayer.getStylesheets().add("styles/welcomeLayer.css");

        gameControls.setPrefHeight(40);
        gameLayer.setPrefWidth(Constants.ScreenWidth);
        gameLayer.setPrefHeight(Constants.ScreenHeight);
        gameLayer.getChildren().addAll(gameScreen, gameControls);

        gameScreen.setPrefWidth(500);
        gameScreen.setPrefHeight(300);

        AnchorPane.setBottomAnchor(gameControls, 0.0);
        AnchorPane.setRightAnchor(gameControls, 0.0);
        AnchorPane.setLeftAnchor(gameControls, 0.0);
        AnchorPane.setTopAnchor(gameScreen, 40.0);
        AnchorPane.setLeftAnchor(gameScreen, 40.0);
        AnchorPane.setRightAnchor(gameScreen, 40.0);

        gameLayer.getStylesheets().add("styles/welcomeLayer.css");
        gameLayer.setId("welcomeLayer");
        gameScreen.setId("welcomeScreen");
        gameControls.setId("welcomeControls");

        return new Scene(gameLayer);

    }

}
