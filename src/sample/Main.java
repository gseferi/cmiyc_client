package sample;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import javafx.application.Application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        SlideScreen slideScreen = new SlideScreen();
       // primaryStage.initStyle(StageStyle.UNDECORATED);
        WelcomeScreen welcomeScreen = new WelcomeScreen();

        GameScreen gameScreen = new GameScreen();

        primaryStage.setScene(slideScreen.drawScene());
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
