package blogjavafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegisterView extends Application {

    private UserRepository userRepository = UserRepository.getInstance();

    @Override
    public void start(Stage primaryStage) {
        // Champs d'entrée
        TextField nameField = new TextField();
        TextField emailField = new TextField();
        PasswordField passwordField = new PasswordField();
        PasswordField confirmPasswordField = new PasswordField();
        Button btnRegister = new Button("Register");
        Button backButton = new Button("Back to Login");

        // Label pour erreurs
        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");

        // Layout
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);

        // Placement des éléments
        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Email:"), 0, 1);
        grid.add(emailField, 1, 1);
        grid.add(new Label("Password:"), 0, 2);
        grid.add(passwordField, 1, 2);
        grid.add(new Label("Retaper Password:"), 0, 3);
        grid.add(confirmPasswordField, 1, 3);
        grid.add(btnRegister, 1, 4);
        grid.add(errorLabel, 1, 5);
        grid.add(backButton, 1, 6);

        // Action Register
        btnRegister.setOnAction(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                errorLabel.setText("Veuillez remplir tous les champs !");
            } else if (!password.equals(confirmPassword)) {
                errorLabel.setText("Les mots de passe ne correspondent pas !");
            } else {
                boolean success = userRepository.registerUser(name, email, password);
                if (success) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Succès");
                    alert.setHeaderText(null);
                    alert.setContentText("Inscription réussie pour " + name + " !");
                    alert.showAndWait();

                    // Retour à la page de login
                    primaryStage.close();
                    LoginView loginView = new LoginView();
                    Stage loginStage = new Stage();
                    try {
                        loginView.start(loginStage);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    errorLabel.setText("Erreur lors de l'inscription !");
                }
            }
        });

        // Action "Back to Login"
        backButton.setOnAction(e -> {
            primaryStage.close();
            LoginView loginView = new LoginView();
            Stage loginStage = new Stage();
            try {
                loginView.start(loginStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Affichage
        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setTitle("Formulaire d'inscription");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
