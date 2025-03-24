package blogjavafx;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestDatabase {
    public static void main(String[] args) {
        Connection conn = DatabaseConnection.getConnection();
        
        try {
            Statement stmt = conn.createStatement();
            // Ici on teste une requête simple, comme récupérer les utilisateurs
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            while (rs.next()) {
                System.out.println("User ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("-----------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
