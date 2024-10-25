import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            System.out.println("Conexiunea la baza de date a fost realizată cu succes!");
            DatabaseConnection.closeConnection();
        } else {
            System.out.println("Nu s-a putut realiza conexiunea la baza de date.");
        }
    }
}
