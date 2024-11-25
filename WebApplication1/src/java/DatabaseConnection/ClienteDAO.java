package DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO implements AutoCloseable {

    private final Connection conexao;

    public ClienteDAO() throws SQLException {
        this.conexao = DatabaseConnection.getConnection();
    }

    public void adicionarCliente(String nome, String email, String telefone, String endereco, String marca, String modelo, String placa, String observacao) throws SQLException {
        String sql = "INSERT INTO Clientes (nome, email, telefone, endereco, marca, modelo, placa, observacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, telefone);
            stmt.setString(4, endereco);
            stmt.setString(5, marca);
            stmt.setString(6, modelo);
            stmt.setString(7, placa);
            stmt.setString(8, observacao);
            stmt.executeUpdate();
        }
    }

    public void adicionarCarro(int clienteId, String marca, String modelo, String placa) throws SQLException {
        String sql = "INSERT INTO Carros (cliente_id, marca, modelo, placa) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            stmt.setString(2, marca);
            stmt.setString(3, modelo);
            stmt.setString(4, placa);
            stmt.executeUpdate();
        }
    }

    @Override
    public void close() throws SQLException {
        if (conexao != null && !conexao.isClosed()) {
            conexao.close();
        }
    }
}
