package blogjavafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginView extends Application {

    private UserRepository userRepository = UserRepository.getInstance(); 
    private Label lblMessage = new Label(); // Ajouter le label globalement

    @Override
    public void start(Stage primaryStage) {
        // Titre de la fenêtre
        primaryStage.setTitle("Login");

        // Création des champs
        Label emailLabel = new Label("Email:"); // J'ai mis Username pour être cohérent avec le repo
        TextField emailField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");

        // Action du bouton login
        loginButton.setOnAction(e -> {
            String username = emailField.getText();
            String password = passwordField.getText();

            boolean isAuthenticated = userRepository.authenticate(username, password);
            if (isAuthenticated) {
                lblMessage.setText("Connexion réussie !");
                // Ferme la fenêtre actuelle
                primaryStage.close();
                // Ouvre HomeView ou Dashboard
                DashboardView dashboardView = new DashboardView(username);
                Stage dashboardStage = new Stage();
                try {
                    dashboardView.start(dashboardStage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                lblMessage.setText("Identifiants invalides.");
            }
        });

        // Layout
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.add(emailLabel, 0, 0);
        grid.add(emailField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 0, 2);
        grid.add(registerButton, 1, 2);
        grid.add(lblMessage, 0, 3, 2, 1); // Affiche le message sous les boutons

        // Scene
        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Action du bouton register (pour basculer vers RegisterView)
        registerButton.setOnAction(e -> {
            primaryStage.close();
            RegisterView registerView = new RegisterView();
            Stage registerStage = new Stage();
            try {
                registerView.start(registerStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
