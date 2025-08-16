import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// (Requisito 11) Acesso 'default' para a classe
class FilmeDAO {

    private ConexaoMySql conexao = new ConexaoMySql();

    // (Requisito 17) Método para "Create" (Salvar)
    public void salvar(Filme filme) {
        // A query usa os getters do objeto Filme
        String query = "INSERT INTO filme (titulo, genero, ano_lancamento, status) VALUES ('" +
                filme.getTitulo() + "', '" +
                filme.getGenero() + "', " +
                filme.getAnoLancamento() + ", '" +
                filme.getStatus().toString() + "')";

        System.out.println("Executando query: " + query);
        Connection con = conexao.getConnection();

        try {
            if (con != null) {
                Statement st = con.createStatement();
                st.executeUpdate(query);
                System.out.println("Filme salvo com sucesso!");
                con.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar filme!");
            e.printStackTrace();
        }
    }

    // (Requisito 17) Método para "Read" (Exibir todos)
    public void exibirTodos() {
        Connection con = conexao.getConnection();
        if (con == null) return;

        try {
            String query = "SELECT * FROM filme";
            Statement st = con.createStatement();
            ResultSet result = st.executeQuery(query);

            System.out.println("\n--- LISTA DE FILMES NA LOCADORA ---");
            while (result.next()) {
                System.out.println(
                    "Título: " + result.getString("titulo") +
                    ", Gênero: " + result.getString("genero") +
                    ", Ano: " + result.getInt("ano_lancamento") +
                    ", Status: " + result.getString("status")
                );
            }
            System.out.println("-------------------------------------\n");
            con.close();
        } catch (SQLException e) {
            System.err.println("Erro ao exibir filmes!");
            e.printStackTrace();
        }
    }
    
    // (Requisito 17) Método para "Delete" (Excluir)
    public void excluir(String titulo) {
        String query = "DELETE FROM filme WHERE titulo = '" + titulo + "'";
        System.out.println("Executando query: " + query);
        Connection con = conexao.getConnection();
        
        try {
            if (con != null) {
                Statement st = con.createStatement();
                int rowsAffected = st.executeUpdate(query);
                if (rowsAffected > 0) {
                    System.out.println("Filme '" + titulo + "' excluído com sucesso!");
                } else {
                    System.out.println("Nenhum filme com o título '" + titulo + "' foi encontrado.");
                }
                con.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao excluir filme!");
            e.printStackTrace();
        }
    }

    // (Requisito 17) Método para "Update" (Editar Status)
    public void editarStatus(String titulo, StatusFilme novoStatus) {
        String query = "UPDATE filme SET status = '" + novoStatus.toString() + "' WHERE titulo = '" + titulo + "'";
        System.out.println("Executando query: " + query);
        Connection con = conexao.getConnection();
        
        try {
            if (con != null) {
                Statement st = con.createStatement();
                int rowsAffected = st.executeUpdate(query);
                 if (rowsAffected > 0) {
                    System.out.println("Status do filme '" + titulo + "' atualizado com sucesso!");
                } else {
                    System.out.println("Nenhum filme com o título '" + titulo + "' foi encontrado para atualização.");
                }
                con.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao editar status do filme!");
            e.printStackTrace();
        }
    }
}
