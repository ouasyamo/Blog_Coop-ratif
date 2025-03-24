package blogjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class DashboardView extends Application {

    private String userEmail;

    public DashboardView(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public void start(Stage primaryStage) {
        // Header
        HBox header = new HBox(20);
        Label logo = new Label("<YELO>");
        logo.setFont(Font.font("Arial", 24));
        logo.setTextFill(Color.web("#F5B800"));

        Button homeBtn = new Button("Home");
        Button blogBtn = new Button("Blog");
        Label adminLabel = new Label("Admin");
        ImageView userIcon = new ImageView("https://cdn-icons-png.flaticon.com/512/149/149071.png");
        userIcon.setFitHeight(20);
        userIcon.setFitWidth(20);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        header.getChildren().addAll(logo, homeBtn, blogBtn, spacer, adminLabel, userIcon);
        header.setPadding(new Insets(15));
        header.setAlignment(Pos.CENTER_LEFT);
        header.setStyle("-fx-background-color: #ffffff; -fx-border-color: #e0e0e0;");

        // Welcome Section
        VBox welcomeSection = new VBox(10);
        Label welcomeText = new Label("Welcome to <YELO> News");
        welcomeText.setFont(Font.font("Arial", 28));
        welcomeText.setTextFill(Color.web("#212121"));

        Label subtitle = new Label("Best Blog in the universe");
        subtitle.setFont(Font.font("Arial", 16));
        subtitle.setTextFill(Color.web("#757575"));

        Button startReadingBtn = new Button("Start Reading");
        welcomeSection.getChildren().addAll(welcomeText, subtitle, startReadingBtn);
        welcomeSection.setAlignment(Pos.CENTER);
        welcomeSection.setPadding(new Insets(50, 0, 50, 0));

        // Featured Posts Section dynamique
        VBox featuredSection = new VBox(20);
        Label featuredTitle = new Label("Featured Posts");
        featuredTitle.setFont(Font.font("Arial", 20));
        featuredTitle.setTextFill(Color.web("#F5B800"));

        VBox postsVBox = new VBox(10);
        postsVBox.setPadding(new Insets(10));
        postsVBox.setStyle("-fx-background-color: #f9f9f9; -fx-border-radius: 10; -fx-background-radius: 10;");

        // Chargement des posts depuis PostRepository
        PostRepository postRepo = new PostRepository();
        List<Post> posts = postRepo.getAllPosts();

        if (posts.isEmpty()) {
            Label noPostLabel = new Label("Aucun article trouvé.");
            postsVBox.getChildren().add(noPostLabel);
        } else {
            for (Post post : posts) {
                VBox postBox = new VBox(5);
                postBox.setPadding(new Insets(10));
                postBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #e0e0e0; -fx-border-radius: 5; -fx-background-radius: 5;");
                postBox.setCursor(javafx.scene.Cursor.HAND); // Curseur main

                Label titleLabel = new Label(post.getTitle());
                titleLabel.setFont(Font.font("Arial", 16));
                titleLabel.setTextFill(Color.web("#212121"));

                Label contentLabel = new Label(post.getContent());
                contentLabel.setWrapText(true);
                contentLabel.setTextFill(Color.web("#424242"));

                Label dateLabel = new Label("Publié le : " + post.getPublishedAt());
                dateLabel.setFont(Font.font("Arial", 10));
                dateLabel.setTextFill(Color.web("#757575"));

                // Effet hover
                postBox.setOnMouseEntered(e -> postBox.setStyle(
                        "-fx-background-color: #f0f0f0; -fx-border-color: #e0e0e0; -fx-border-radius: 5; -fx-background-radius: 5;"));
                postBox.setOnMouseExited(e -> postBox.setStyle(
                        "-fx-background-color: #ffffff; -fx-border-color: #e0e0e0; -fx-border-radius: 5; -fx-background-radius: 5;"));

                // Clique pour ouvrir les détails
                postBox.setOnMouseClicked(event -> openArticleDetail(post));

                postBox.getChildren().addAll(titleLabel, contentLabel, dateLabel);
                postsVBox.getChildren().add(postBox);
            }
        }

        featuredSection.getChildren().addAll(featuredTitle, postsVBox);
        featuredSection.setPadding(new Insets(20));

        // Root Layout
        VBox root = new VBox();
        root.getChildren().addAll(header, welcomeSection, featuredSection);
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openArticleDetail(Post post) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("article_detail.fxml"));
            Parent root = loader.load();

            ArticleDetailController controller = loader.getController();
            controller.setPost(post);

            Stage stage = new Stage();
            stage.setTitle(post.getTitle());
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
