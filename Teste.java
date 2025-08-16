//Requisito 7 CRUD


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Teste {

    public static void main(String[] args) {

        ConexaoMySql conexao = new ConexaoMySql();
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Visualização Banco de dados Locadora AV ---");
        System.out.println("Digite seus comandos SQL ou 'sair' para terminar.");

        while (true) {
            System.out.print("\nSQL> ");
            String sqlCommand = scanner.nextLine();

            if (sqlCommand.equalsIgnoreCase("sair")) {
                break;
            }

            if (sqlCommand.trim().isEmpty()) {
                continue;
            }

            Connection con = null;
            try {
                con = conexao.getConnection();
                Statement statement = con.createStatement();

                boolean hasResultSet = statement.execute(sqlCommand);

                if (hasResultSet) {
                    ResultSet rs = statement.getResultSet();
                    ResultSetMetaData metaData = rs.getMetaData();
                    int columnCount = metaData.getColumnCount();

                    int columnWidth = 25;

                    for (int i = 1; i <= columnCount; i++) {
                        System.out.printf("%-" + columnWidth + "s | ", metaData.getColumnName(i));
                    }
                    System.out.println();

                    for (int i = 1; i <= columnCount; i++) {
                        for (int j = 0; j < columnWidth; j++) {
                            System.out.print("-");
                        }
                        System.out.print("---");
                    }
                    System.out.println();

                    while (rs.next()) {
                        for (int i = 1; i <= columnCount; i++) {
                            String value = rs.getString(i);
                            if (value == null) {
                                value = "NULL";
                            }
                            System.out.printf("%-" + columnWidth + "s | ", value);
                        }
                        System.out.println();
                    }
                    
                    rs.close();

                } else {
                    int rowsAffected = statement.getUpdateCount();
                    System.out.println("Comando executado com sucesso. " + rowsAffected + " linhas afetadas.");
                }

                statement.close();

            } catch (SQLException e) {
                System.err.println("Erro ao executar o comando SQL: " + e.getMessage());
            } finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        System.out.println("Sistema encerrado.");
        scanner.close();
    }
}