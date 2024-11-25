package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // URL de conexão ao banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_troca_oleo?useSSL=false";
    // Usuário do banco de dados
    private static final String USER = "root"; // Substitua pelo seu usuário do banco
    // Senha do banco de dados
    private static final String PASSWORD = "123456"; // Substitua pela sua senha do banco

    // Bloco estático para carregar o driver MySQL
    static {
        try {
            // Carrega explicitamente o driver do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver MySQL carregado com sucesso!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Erro ao carregar o driver JDBC MySQL", e);
        }
    }

    /**
     * Método para obter a conexão com o banco de dados
     *
     * @return Connection - conexão ao banco de dados
     * @throws SQLException - caso ocorra um erro ao se conectar ao banco
     */
    public static Connection getConnection() throws SQLException {
        System.out.println("Tentando conectar ao banco de dados...");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
