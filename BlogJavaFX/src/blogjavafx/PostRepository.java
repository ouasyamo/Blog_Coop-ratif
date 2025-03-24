package blogjavafx;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import blogjavafx.Post; 

public class PostRepository {
    private Connection conn;

    public PostRepository() {
        this.conn = DatabaseConnection.getConnection();
    }

    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        String query = "SELECT id, title, body, slug, published_at FROM posts";

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setTitle(rs.getString("title"));
                post.setContent(rs.getString("body"));
                post.setSlug(rs.getString("slug"));
                post.setPublishedAt(rs.getString("published_at"));

                posts.add(post);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Nombre de posts récupérés: " + posts.size());
        return posts;
    }
}
