package DatabaseConnection;

import java.sql.SQLException;

public class TesteClienteDAO {

    public static void main(String[] args) {
        try (ClienteDAO dao = new ClienteDAO()) {
            dao.adicionarCliente("Teste Nome", "teste@email.com", "1234567890", "rua basilio 135", "Toyota", "Corolla", "ABC1234", "teste");
            System.out.println("Cliente adicionado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
