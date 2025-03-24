package blogjavafx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/laravel_blog_application"; // Mets ton nom de base ici
    private static final String USER = "root"; // Ton utilisateur MySQL (ex: root)
    private static final String PASSWORD = ""; // Ton mot de passe (ex: "" si vide)

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connexion réussie à la base de données !");
            } catch (SQLException e) {
                System.out.println("Erreur de connexion : " + e.getMessage());
            }
        }
        return connection;
    }
}
