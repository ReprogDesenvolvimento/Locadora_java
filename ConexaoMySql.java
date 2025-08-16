import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySql {

    // (Requisito 11 e 15) Uso de 'private' para encapsulamento
    private final String USER = "root";
    private final String URL = "jdbc:mysql://localhost/";
    private final String PASSWORD = "admin"; 
    private final String DATABASE = "locadora_av"; 
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private Connection con;

    // (Requisito 12) Uso de 'public' para o método de conexão
    public Connection getConnection() {

        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL + DATABASE, USER, PASSWORD);

        } catch (ClassNotFoundException e) {
            // (Requisito 4) Tratamento de Exceções
            System.err.println("Driver MySQL não encontrado!");
            e.printStackTrace();
        } catch (SQLException e) {
            // (Requisito 4) Tratamento de Exceções
            System.err.println("Erro ao conectar ao banco de dados!");
            e.printStackTrace();
        }

        return con;
    }
}