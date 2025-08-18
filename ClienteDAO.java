// ClienteDAO.java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class ClienteDAO {

    private ConexaoMySql conexao = new ConexaoMySql();

    // Método para "Create" (Salvar um novo cliente)
    public void salvar(Cliente cliente) {
        String query = "INSERT INTO cliente (nome, email, telefone) VALUES (?, ?, ?)";
        Connection con = conexao.getConnection();

        try {
            if (con != null) {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setString(1, cliente.getNome());
                ps.setString(2, cliente.getEmail());
                ps.setString(3, cliente.getTelefone());
                ps.executeUpdate();
                System.out.println("Cliente salvo com sucesso!");
                con.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar cliente!");
            e.printStackTrace();
        }
    }

    // Método para "Read" (Exibir todos os clientes)
    public void exibirTodos() {
        Connection con = conexao.getConnection();
        if (con == null) return;

        try {
            String query = "SELECT * FROM cliente";
            Statement st = con.createStatement();
            ResultSet result = st.executeQuery(query);

            System.out.println("\n--- LISTA DE CLIENTES ---");
            while (result.next()) {
                System.out.println(
                    "ID: " + result.getInt("id") +
                    ", Nome: " + result.getString("nome") +
                    ", Email: " + result.getString("email") +
                    ", Telefone: " + result.getString("telefone")
                );
            }
            System.out.println("---------------------------\n");
            con.close();
        } catch (SQLException e) {
            System.err.println("Erro ao exibir clientes!");
            e.printStackTrace();
        }
    }
}