package blogjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ArticleDetailController {

    @FXML private Label titleLabel;
    @FXML private Label publishedAtLabel;
    @FXML private TextArea contentText;

    // Méthode pour injecter les données dans la vue
    public void setPost(Post post) {
        titleLabel.setText(post.getTitle());
        contentText.setText(post.getContent());
        publishedAtLabel.setText("Published at: " + post.getPublishedAt().toString());
    }

    // Fermer la fenêtre
    @FXML
    private void handleClose() {
        Stage stage = (Stage) titleLabel.getScene().getWindow();
        stage.close();
    }
}
