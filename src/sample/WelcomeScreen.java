package sample;

import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * Created by Gerta on 04/03/2017.
 */
public class WelcomeScreen {

    private AnchorPane welcomeLayer;
    private BorderPane welcomeScreen;
    private ToolBar welcomeControls;

    public WelcomeScreen() throws IOException {
        this.welcomeLayer = new AnchorPane();
        this.welcomeScreen = new BorderPane();
        this.welcomeControls = new ToolBar();

    }

    public Scene drawScene() {

        welcomeLayer.getStylesheets().add("styles/welcomeLayer.css");

        welcomeControls.setPrefHeight(40);
        welcomeLayer.setPrefWidth(Constants.ScreenWidth);
        welcomeLayer.setPrefHeight(Constants.ScreenHeight);
        welcomeLayer.getChildren().addAll(welcomeScreen, welcomeControls);

        welcomeScreen.setPrefWidth(500);
        welcomeScreen.setPrefHeight(300);


        AnchorPane.setBottomAnchor(welcomeControls, 0.0);
        AnchorPane.setRightAnchor(welcomeControls, 0.0);
        AnchorPane.setLeftAnchor(welcomeControls, 0.0);
        AnchorPane.setTopAnchor(welcomeScreen,  40.0);
        AnchorPane.setLeftAnchor(welcomeScreen, 40.0);
        AnchorPane.setRightAnchor(welcomeScreen, 40.0);

        Arc arc1 = new Arc();
        arc1.setFill(Paint.valueOf("#606060"));
        arc1.setCenterX(65.0f);
        arc1.setCenterY(65.0f);
        arc1.setRadiusX(40.0f);
        arc1.setRadiusY(50.0f);
        arc1.setStartAngle(-25.0f);
        arc1.setLength(50.0f);
        arc1.setType(ArcType.ROUND);

        Ellipse ellipse1 = new Ellipse();
        ellipse1.setFill(Paint.valueOf("#3CB371"));
        ellipse1.setCenterX(70.0f);
        ellipse1.setCenterY(65.0f);
        ellipse1.setRadiusX(9.0f);
        ellipse1.setRadiusY(9.0f);

        Arc arc2 = new Arc();
        arc2.setFill(Paint.valueOf("#606060"));
        arc2.setCenterX(140.0f);
        arc2.setCenterY(45.0f);
        arc2.setRadiusX(40.0f);
        arc2.setRadiusY(40.0f);
        arc2.setStartAngle(-115.0f);
        arc2.setLength(50.0f);
        arc2.setType(ArcType.ROUND);

        Ellipse ellipse2 = new Ellipse();
        ellipse2.setFill(Paint.valueOf("#3CB371"));
        ellipse2.setCenterX(140.0f);
        ellipse2.setCenterY(53.0f);
        ellipse2.setRadiusX(9.0f);
        ellipse2.setRadiusY(9.0f);


        Arc arc3 = new Arc();
        arc3.setFill(Paint.valueOf("#606060"));
        arc3.setCenterX(165.0f);
        arc3.setCenterY(45.0f);
        arc3.setRadiusX(40.0f);
        arc3.setRadiusY(40.0f);
        arc3.setStartAngle(-115.0f);
        arc3.setLength(50.0f);
        arc3.setType(ArcType.ROUND);

        Ellipse ellipse3 = new Ellipse();
        ellipse3.setFill(Paint.valueOf("#3CB371"));
        ellipse3.setCenterX(165.0f);
        ellipse3.setCenterY(53.0f);
        ellipse3.setRadiusX(9.0f);
        ellipse3.setRadiusY(9.0f);

        Arc arc4 = new Arc();
        arc4.setFill(Paint.valueOf("#606060"));
        arc4.setCenterX(210.0f);
        arc4.setCenterY(78.0f);
        arc4.setRadiusX(25.0f);
        arc4.setRadiusY(32.0f);
        arc4.setStartAngle(65.0f);
        arc4.setLength(50.0f);
        arc4.setType(ArcType.ROUND);

        Ellipse ellipse4 = new Ellipse();
        ellipse4.setFill(Paint.valueOf("#3CB371"));
        ellipse4.setCenterX(210.0f);
        ellipse4.setCenterY(78.0f);
        ellipse4.setRadiusX(9.0f);
        ellipse4.setRadiusY(9.0f);

        Ellipse ellipse4b = new Ellipse();
        ellipse4b.setFill(Paint.valueOf("#FF0000"));
        ellipse4b.setCenterX(210.0f);
        ellipse4b.setCenterY(30.0f);
        ellipse4b.setRadiusX(9.0f);
        ellipse4b.setRadiusY(9.0f);

        Arc arc5 = new Arc();
        arc5.setFill(Paint.valueOf("#606060"));
        arc5.setCenterX(270.0f);
        arc5.setCenterY(85.0f);
        arc5.setRadiusX(50.0f);
        arc5.setRadiusY(40.0f);
        arc5.setStartAngle(62.0f);
        arc5.setLength(60.0f);
        arc5.setType(ArcType.ROUND);

        Ellipse ellipse5 = new Ellipse();
        ellipse5.setFill(Paint.valueOf("#3CB371"));
        ellipse5.setCenterX(270.0f);
        ellipse5.setCenterY(80.0f);
        ellipse5.setRadiusX(9.0f);
        ellipse5.setRadiusY(9.0f);

        Arc arc6 = new Arc();
        arc6.setFill(Paint.valueOf("#606060"));
        arc6.setCenterX(272.0f);
        arc6.setCenterY(80.0f);
        arc6.setRadiusX(35.0f);
        arc6.setRadiusY(20.0f);
        arc6.setStartAngle(190.0f);
        arc6.setLength(40.0f);
        arc6.setType(ArcType.ROUND);

        Arc arc7 = new Arc();
        arc7.setFill(Paint.valueOf("#606060"));
        arc7.setCenterX(310.0f);
        arc7.setCenterY(65.0f);
        arc7.setRadiusX(40.0f);
        arc7.setRadiusY(50.0f);
        arc7.setStartAngle(-25.0f);
        arc7.setLength(50.0f);
        arc7.setType(ArcType.ROUND);

        Ellipse ellipse7 = new Ellipse();
        ellipse7.setFill(Paint.valueOf("#3CB371"));
        ellipse7.setCenterX(315.0f);
        ellipse7.setCenterY(65.0f);
        ellipse7.setRadiusX(9.0f);
        ellipse7.setRadiusY(9.0f);

        Text text = new Text();
        text.setText("Catch Me If You Can");
        text.setFont(Font.font ("Verdana", 25));
        text.setFill(Color.valueOf("#606060"));

        welcomeScreen.getChildren().addAll(arc1, arc2, arc3, arc4, arc5, arc6, arc7);
        welcomeScreen.getChildren().addAll(ellipse1, ellipse2, ellipse3, ellipse4, ellipse4b, ellipse5, ellipse7);

        welcomeScreen.setCenter(text);


        welcomeLayer.setId("welcomeLayer");
        welcomeScreen.setId("welcomeScreen");
        welcomeControls.setId("welcomeControls");


        return new Scene(welcomeLayer);
    }

}
