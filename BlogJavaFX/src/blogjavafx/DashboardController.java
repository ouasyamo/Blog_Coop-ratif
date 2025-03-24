package blogjavafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private TableView<Post> tablePosts;

    @FXML
    private TableColumn<Post, Integer> idCol;

    @FXML
    private TableColumn<Post, String> titleCol;

    @FXML
    private TableColumn<Post, String> slugCol;

    @FXML
    private TableColumn<Post, String> publishedAtCol;

    @Override
    
public void initialize(URL url, ResourceBundle resourceBundle) {
    // Configuration des colonnes
    idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
    slugCol.setCellValueFactory(new PropertyValueFactory<>("slug"));
    publishedAtCol.setCellValueFactory(new PropertyValueFactory<>("publishedAt"));

    // Instancier le repository
    PostRepository repository = new PostRepository();
    List<Post> posts = repository.getAllPosts();

    // Remplir la TableView
    tablePosts.getItems().addAll(posts);

    // GESTION DU CLIC SUR UNE LIGNE
    tablePosts.setOnMouseClicked(event -> {
        if (event.getClickCount() == 2) { // double clic
            Post selectedPost = tablePosts.getSelectionModel().getSelectedItem();
            if (selectedPost != null) {
                openArticleDetail(selectedPost);
            }
        }
    });
}


    private void openArticleDetail(Post post) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("article_detail.fxml"));
        Parent root = loader.load();

        // Passe le post au controller
        ArticleDetailController controller = loader.getController();
        controller.setPost(post);

        Stage stage = new Stage();
        stage.setTitle("Détails de l'article");
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
