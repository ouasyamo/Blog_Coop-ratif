package blogjavafx;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Ouvrir directement LoginView au lancement
        LoginView loginView = new LoginView();
        loginView.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
