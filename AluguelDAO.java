// AluguelDAO.java
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


class AluguelDAO {

    private ConexaoMySql conexao = new ConexaoMySql();

    // Método para registrar um novo aluguel
    public void registrarAluguel(Aluguel aluguel) {
        // 1. Inserir o registro na tabela 'aluguel'
        String queryAluguel = "INSERT INTO aluguel (id_filme, id_cliente, data_aluguel) VALUES (?, ?, ?)";
        
        // 2. Atualizar o status do filme para 'ALUGADO' na tabela 'filme'
        String queryUpdateFilme = "UPDATE filme SET status = 'ALUGADO' WHERE id = ?";

        Connection con = conexao.getConnection();

        try {
            if (con != null) {
                // Desativa o auto-commit para garantir que as duas operações ocorram juntas
                con.setAutoCommit(false);

                // Executa a inserção do aluguel
                PreparedStatement psAluguel = con.prepareStatement(queryAluguel);
                psAluguel.setInt(1, aluguel.getIdFilme());
                psAluguel.setInt(2, aluguel.getIdCliente());
                psAluguel.setDate(3, Date.valueOf(aluguel.getDataAluguel()));
                psAluguel.executeUpdate();
                
                // Executa a atualização do status do filme
                PreparedStatement psUpdateFilme = con.prepareStatement(queryUpdateFilme);
                psUpdateFilme.setInt(1, aluguel.getIdFilme());
                psUpdateFilme.executeUpdate();
                
                // Se tudo deu certo, confirma as alterações
                con.commit();
                System.out.println("Aluguel registrado e status do filme atualizado com sucesso!");

            }
        } catch (SQLException e) {
            System.err.println("Erro ao registrar aluguel!");
            e.printStackTrace();
            try {
                // Se deu erro, desfaz as alterações
                if (con != null) con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


